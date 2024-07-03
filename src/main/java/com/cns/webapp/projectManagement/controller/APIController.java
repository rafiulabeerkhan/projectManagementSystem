package com.cns.webapp.projectManagement.controller;

import com.cns.webapp.projectManagement.model.ProjectDetails;
import com.cns.webapp.projectManagement.model.Report;
import com.cns.webapp.projectManagement.model.UserDetails;
import com.cns.webapp.projectManagement.repository.service.JasperReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class APIController {

    @Autowired
    private JasperReportService reportService;

    @GetMapping("/userdata/{id}")
    public String getUserPage(@PathVariable("id") Integer userId, HttpSession session) {

        session.setAttribute("userDataId", userId );
        return "userData";
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format, HttpSession session) throws FileNotFoundException, JRException {
        List<ProjectDetails> allProjects = (List<ProjectDetails>) session.getAttribute("allProjects");

        List<Report> reportProjects = new ArrayList<Report>();

        for(ProjectDetails project: allProjects){
            Report signleProject = new Report();
            signleProject.setId(project.projectId);
            signleProject.setName(project.projectName);
            signleProject.setIntro(project.intro);
            signleProject.setStatus(project.status);
            signleProject.setOwner(project.owner);
            signleProject.setStartDate(project.startDate);
            signleProject.setEndDate(project.endDate);


            if (project.members != null){
                List<String> memberList= new ArrayList<String>();
                for (UserDetails member: project.members){
                    memberList.add(member.getUsername());
                }
                signleProject.setProjectMembers(memberList);
            }

            reportProjects.add(signleProject);
            System.out.println(signleProject.projectMembers);
        }

//        if (memberList != null) {
//            for (String name : memberList) {
//                UserDetails userDetails = userService.findUser(name);
//                usersList.add(userDetails);
//            }
//        }
        return reportService.exportReport(format, reportProjects);
}

}
