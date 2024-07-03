package com.cns.webapp.projectManagement.repository.service;

import com.cns.webapp.projectManagement.model.UserDetails;
import com.cns.webapp.projectManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    public UserRepository userRepo;

    @Override
    public UserDetails createUser(UserDetails user) {
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDetails userLogin(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public UserDetails findUser(String username) {
        return userRepo.findByUsername(username);
    }
}
