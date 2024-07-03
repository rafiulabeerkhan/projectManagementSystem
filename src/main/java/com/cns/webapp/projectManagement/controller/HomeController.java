package com.cns.webapp.projectManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/home")
    public String home(HttpSession session){
        if(session.getAttribute("user")!=null){
            return "home";
        }
        else{
            return "redirect:/login";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        if(session.getAttribute("user")!=null){
            session.invalidate();
            return "redirect:/";
        }
        else{
            return "redirect:/login";
        }
    }
}
