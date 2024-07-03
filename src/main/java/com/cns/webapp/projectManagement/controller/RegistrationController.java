package com.cns.webapp.projectManagement.controller;

import com.cns.webapp.projectManagement.model.UserDetails;
import com.cns.webapp.projectManagement.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserDetails user, HttpSession session){

        boolean duplicateEmail = userService.checkEmail(user.getEmail());
        if(duplicateEmail){
            session.setAttribute("message", "Already registered with this email");
            return "redirect:/register";
        }
        else{
//            System.out.println("NEW");
            UserDetails newUser = userService.createUser(user);

            if(newUser!=null){
                session.setAttribute("message", "Registration Successful");
                return "redirect:/login";

            } else {
                System.out.println("REGISTRATION FAILED");
                return "redirect:/register";
            }

        }

    }
}
