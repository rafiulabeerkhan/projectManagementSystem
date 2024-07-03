package com.cns.webapp.projectManagement.controller;

import com.cns.webapp.projectManagement.model.ProjectDetails;
import com.cns.webapp.projectManagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class RESTController {

    @Autowired
    private ProjectRepository projectRepo;

    @GetMapping
    public List<ProjectDetails> getAllProjects(){
        return projectRepo.findAll();
}

}
