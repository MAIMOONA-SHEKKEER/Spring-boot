package com.example.registration_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.registration_api.entity.Application;

@Controller
public class ApplicationController {

    @GetMapping("/leaseApplication")
    public String showApplicationForm(Model model) {
        model.addAttribute("application", new Application());
        return "leaseApplicationForm";
    }

    @PostMapping("/submitApplication")
    public String submitApplication(@ModelAttribute Application application) {
        // Save application data to the database or process it as needed
        return "applicationSuccess";
    }
}
