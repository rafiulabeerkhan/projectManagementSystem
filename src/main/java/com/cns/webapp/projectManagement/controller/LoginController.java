package com.cns.webapp.projectManagement.controller;

import com.cns.webapp.projectManagement.model.UserDetails;
import com.cns.webapp.projectManagement.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute UserDetails user, HttpSession session){
        System.out.println(user.getUsername() + "Logged in");

        UserDetails loginUser =  userService.userLogin(user.getUsername(), user.getPassword());

//        System.out.println(loginUser);-
        if(loginUser!=null){
            session.setAttribute("user", loginUser.getUsername());
            session.setAttribute("user_id", loginUser.getId());
            return "redirect:/user/projectList";
        } else {
            System.out.println("Login Failed");
            return "redirect:/login";
        }

    }
}
