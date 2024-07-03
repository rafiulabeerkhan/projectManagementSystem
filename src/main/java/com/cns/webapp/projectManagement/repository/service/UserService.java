package com.cns.webapp.projectManagement.repository.service;

import com.cns.webapp.projectManagement.model.ProjectDetails;
import com.cns.webapp.projectManagement.model.UserDetails;

import java.util.List;

public interface UserService {
    public UserDetails createUser(UserDetails user);

    public boolean checkEmail(String email);

    public UserDetails userLogin(String username, String password);

    public UserDetails findUser(String username);

}
