package com.cns.webapp.projectManagement.controller;


import com.cns.webapp.projectManagement.model.ProjectDetails;
import com.cns.webapp.projectManagement.model.UserDetails;
import com.cns.webapp.projectManagement.repository.service.ProjectService;
import com.cns.webapp.projectManagement.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @GetMapping("/user/createProject")
    public String createProject(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "user/createProject";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user/projectList")
    public String projectList(HttpSession session) {
        if (session.getAttribute("user") != null) {
            showCurrentMonthProjects(session);
            return "user/projectList";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/user/createProject")
    public String createProject(@ModelAttribute ProjectDetails project, @RequestParam(value = "member", required = false) List<String> memberList, HttpSession session) {

        List<UserDetails> usersList = new ArrayList<UserDetails>();
        if (memberList != null) {
            for (String name : memberList) {
                UserDetails userDetails = userService.findUser(name);
                usersList.add(userDetails);
            }
        }

        project.setMembers(usersList);
        project.setOwner((String) session.getAttribute("user"));

        if (project != null) {
            projectService.createProject(project);
            session.setAttribute("message", "Project added successfully");
            showCurrentMonthProjects(session);

            return "redirect:/user/projectList";
        } else {
            return "/user/createProject";
        }
    }

    @GetMapping("/user/showProjectInRange")
    public String showProjectInRange(HttpSession session, @RequestParam Date startRange, @RequestParam Date endRange) {
//        System.out.println(startRange.getClass());
//        System.out.println(endRange);
        getProjectList(session, startRange, endRange);

        System.out.println(session.getAttribute("allProjects"));
        return "user/projectList";
    }

    public void getProjectList(HttpSession session, Date startRange, Date endRange) {

        List<ProjectDetails> allProjects = projectService.showCurrentMonthProject(startRange, endRange, startRange, endRange);
        List<ProjectDetails> runningProjects = projectService.showRunningProject(startRange, endRange);

        allProjects.addAll(runningProjects);

        session.setAttribute("allProjects", allProjects);

    }

//  Default Show project for the current month

    public void showCurrentMonthProjects(HttpSession session) {
        LocalDate currentDate = LocalDate.now();
        LocalDate fdate = currentDate.withDayOfMonth(1);
        LocalDate ldate = currentDate.withDayOfMonth(currentDate.getMonth().length(currentDate.isLeapYear()));

        Date sdate = Date.valueOf(fdate);
        Date edate = Date.valueOf(ldate);

        getProjectList(session, sdate, edate);
    }

}
