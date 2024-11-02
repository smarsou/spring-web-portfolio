package com.soulaiman.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.soulaiman.portfolio.model.Project;
import com.soulaiman.portfolio.service.ProjectService;


@Controller
public class MainController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String index(Model model) {

        Iterable<Project> projects = projectService.getProjects();
        
        Iterable<String> topics = projectService.getTopics();

        model.addAttribute("topics", topics);
        model.addAttribute("projects", projects);
        return "index";
    }
    
}
