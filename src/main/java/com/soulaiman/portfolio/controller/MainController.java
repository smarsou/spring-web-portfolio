package com.soulaiman.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.soulaiman.portfolio.model.Project;
import com.soulaiman.portfolio.service.ProjectService;
import com.soulaiman.portfolio.service.ChatbotService;

@Controller
public class MainController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ChatbotService chatbotService;

    @GetMapping("/")
    public String index(Model model) {

        Iterable<Project> projects = projectService.getProjects();
        Iterable<String> topics = projectService.getTopics();

        model.addAttribute("topics", topics);
        model.addAttribute("projects", projects);
        return "index"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    

    @GetMapping("/admin")
    public String admin(Model model) {

        Iterable<Project> projects = projectService.getProjects(); 

        model.addAttribute("projects", projects);
        model.addAttribute("new_project", new Project());
        model.addAttribute("to_update_project", new Project());
        return "admin";
    }

    @PostMapping("/admin/saveProject")
    public ModelAndView saveProject(@ModelAttribute Project project) {
        projectService.saveProject(project);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/admin/deleteProject/{id}")
    public ModelAndView deleteProject(@PathVariable("id") final Long id) {
        projectService.deleteProject(id);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/chatbot")
    @ResponseBody
    public String generate(@RequestBody JsonNode json) {
        System.out.println("Start request with : " + json );
        String response = chatbotService.sendRequest(json);
        return response;
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}
