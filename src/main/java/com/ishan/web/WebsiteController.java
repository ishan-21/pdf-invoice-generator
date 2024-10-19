package com.ishan.web;


import com.ishan.web.forms.LoginForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
public class WebsiteController {

    @GetMapping("/")
    public ModelAndView displayHomepage
            (@RequestParam(value = "userName", required = false, defaultValue = "stranger")
             String userName)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", userName);
        LocalDateTime timeRightNow = LocalDateTime.now();
        modelAndView.addObject("currentTime", timeRightNow);
        modelAndView.setViewName("home.html");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView displayLoginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginForm", new LoginForm());
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @PostMapping("/process-login")
    public String processLoginDetails(@ModelAttribute @Valid LoginForm loginForm,
                                      BindingResult bindingResult,
                                      Model model)
    {

        if( bindingResult.hasErrors() ){
            return "login.html";
        }
        String userName = loginForm.getUserName();
        String password = loginForm.getPassword();

        if(userName.equalsIgnoreCase(password)){
            return "redirect:" + "/";
        }
        model.addAttribute("invalidLoginCredentials",true);
        return "login.html";
    }
}
