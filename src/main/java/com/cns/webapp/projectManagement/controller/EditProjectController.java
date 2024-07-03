package com.cns.webapp.projectManagement.controller;

import com.cns.webapp.projectManagement.model.ProjectDetails;
import com.cns.webapp.projectManagement.model.UserDetails;
import com.cns.webapp.projectManagement.repository.ProjectRepository;
import com.cns.webapp.projectManagement.repository.service.ProjectService;
import com.cns.webapp.projectManagement.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EditProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @Autowired
    public ProjectRepository projectRepo;

    @GetMapping("user/editProject/{id}")
    public String editProject(@PathVariable("id") Integer id, HttpSession session) {
        if(session.getAttribute("user")!=null){
            ProjectDetails project = projectService.getProjectDetailsFromId(id);
            session.setAttribute("editProject", project);

            return "user/editProject";
        }
        else{
            return "redirect:/login";
        }

    }

    @PostMapping("user/editProject")
    public String updateProject(@ModelAttribute ProjectDetails project, @RequestParam("member")List<String> memberList){

        List<UserDetails> usersList = new ArrayList<>();
        if (memberList != null) {
            for (String name : memberList) {
                UserDetails userDetails = userService.findUser(name);
                usersList.add(userDetails);
            }
        }
        project.setMembers(usersList);

        ProjectDetails existingProject = projectService.getProjectDetailsFromId(project.getProjectId());

        System.out.println(existingProject);
        if(project.projectName != null)
            existingProject.setProjectName(project.projectName);

        if(project.intro != null)
            existingProject.setIntro(project.intro);
        if(project.status != null)
            existingProject.setStatus(project.status);
        if(project.startDate != null)
            existingProject.setStartDate(project.startDate);
        if(project.endDate != null)
            existingProject.setEndDate(project.endDate);
        if(project.members != null)
            existingProject.setMembers(project.members);

        projectRepo.save(existingProject);

        return "redirect:/user/projectList";
    }

    // DELETE PROJECT FROM REPOSITORY

    @GetMapping("user/deleteProject/{id}")
    public String deleteProject(@PathVariable("id") Integer id) {

        ProjectDetails project = projectService.getProjectDetailsFromId(id);
        projectRepo.delete(project);

        return "redirect:/user/projectList";
    }
}
