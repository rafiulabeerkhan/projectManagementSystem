package com.cns.webapp.projectManagement.model;


import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer projectId;

    public String projectName;

    public String intro;

    public String owner;

    public Integer status;

//    @DateTimeFormat(pattern="YYYY/MM/DD")
    @Column(nullable = true)
    public Date startDate;

//    @DateTimeFormat(pattern="yyyy/MM/dd")
    public Date endDate;

//    public String members;
    @ManyToMany(fetch =FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "project_member", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    @Column(nullable = true)
    public List<UserDetails> members;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<UserDetails> getMembers() {
        return members;
    }

    public void setMembers(List<UserDetails> members) {
        this.members = members;
    }
}
