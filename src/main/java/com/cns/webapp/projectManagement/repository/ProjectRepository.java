package com.cns.webapp.projectManagement.repository;

import com.cns.webapp.projectManagement.model.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectDetails, Integer> {

     List<ProjectDetails> findByStartDateBetweenOrEndDateBetween(Date startDate, Date endDate, Date startDateAfter, Date endDateAfter);

     List<ProjectDetails> findByStartDateBeforeAndEndDateAfter(Date startDate, Date endDate);

     ProjectDetails findByProjectId(Integer projectId);
}
