package com.example.springinaction;

import com.example.springinaction.Repos.AttributeRepo;
import com.example.springinaction.Repos.GeometryRepo;
import com.example.springinaction.Repos.ProjectRepo;
import com.example.springinaction.domain.Attribute;
import com.example.springinaction.domain.Geometry;
import com.example.springinaction.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private AttributeRepo attributeRepo;

    @Autowired
    private GeometryRepo geometryRepo;



    @GetMapping(path="/api/projects")
    public @ResponseBody
    Iterable<Project> getAllProjects() {
        return projectRepo.findAll();
    }
    @PostMapping( path="/api/projects")
    public @ResponseBody
    String addNewProject (@RequestParam String name) {
        Project p = new Project();
        p.setName(name);
        projectRepo.save(p);
        createGeometry(p);
        createAttribute(p);
        return "Success!";
    }

    @GetMapping(path="/api/projects/{id}")
    public @ResponseBody
    Optional<Project> getProjectById(@PathVariable("id") Long num) {
        return projectRepo.findById(num);
    }



    @PutMapping(path="/api/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") Long num) throws IOException {
        Optional<Project> optionalProject = projectRepo.findById(num);
        if (optionalProject.isPresent()) {
            Project currentProject = optionalProject.get();
            currentProject.setName(getTextInFile(num));
            projectRepo.save(currentProject);
            return ResponseEntity.ok(currentProject);

        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/projects/{id}")
    public @ResponseBody
    void deleteProjectById(@PathVariable("id") Long num)
    {
        projectRepo.deleteById(num);
    }

    void createAttribute(Project proj){
        for (int i = 0; i < 100000; i++) {
            Attribute a = new Attribute();
            a.setName("Attr"+i);
            a.setProj(proj);
            attributeRepo.save(a);
        }

    }

    void createGeometry(Project proj){
        for (int i = 0; i < 100000; i++) {
            Geometry g = new Geometry();
            g.setName("Geom"+i);
            g.setProj(proj);
            geometryRepo.save(g);
        }

    }
    public String getTextInFile (Long id) throws IOException {
        String filePath = "K:\\java\\springinaction\\params.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for(String ln:lines)
            if(ln.contains("project")) return "project"+id;
        return "error";
    }


}
