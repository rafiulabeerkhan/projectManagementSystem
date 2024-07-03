package com.cns.webapp.projectManagement.repository.service;

import com.cns.webapp.projectManagement.model.ProjectDetails;
import com.cns.webapp.projectManagement.model.UserDetails;
import com.cns.webapp.projectManagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    public ProjectRepository projectRepo;


    public ProjectDetails createProject(ProjectDetails project){
        return projectRepo.save(project);
    }

    public List<ProjectDetails> showCurrentMonthProject(Date startDate, Date endDate, Date startDateAfter, Date endDateAfter){
        return projectRepo.findByStartDateBetweenOrEndDateBetween(startDate, endDate, startDateAfter, endDateAfter);
    }

    public List<ProjectDetails> showRunningProject(Date startDate, Date endDate){
        return projectRepo.findByStartDateBeforeAndEndDateAfter(startDate, endDate);
    }

    public ProjectDetails getProjectDetailsFromId(Integer projectId){
        return projectRepo.findByProjectId(projectId);
    }

}
