package com.cns.webapp.projectManagement.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class Report {
    public Integer id;

    public String name;

    public String intro;

    public String owner;

    public Integer status;

    public Date startDate;

    public Date endDate;

    public List<String> projectMembers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<String> projectMembers) {
        this.projectMembers = projectMembers;
    }
}
