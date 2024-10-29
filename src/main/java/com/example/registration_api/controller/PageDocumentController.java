package com.example.registration_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.registration_api.entity.PageContent;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageDocumentController {

    // Generate 20 pages dynamically
    private List<PageContent> generatePages() {
        List<PageContent> pages = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            pages.add(new PageContent("Page " + i, "This is the content of page " + i));
        }
        return pages;
    }

    // Home page that shows a list of links to 20 pages
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pages", generatePages());
        return "index";
    }

    // Display a specific page
    @GetMapping("/page")
    public String page(@RequestParam("number") int number, Model model) {
        List<PageContent> pages = generatePages();

        if (number < 1 || number > pages.size()) {
            return "error"; // Redirect to an error page if the number is invalid
        }

        model.addAttribute("page", pages.get(number - 1));
        return "page";
    }
}
