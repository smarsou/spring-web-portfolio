package com.soulaiman.portfolio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.soulaiman.portfolio.model.Project;
import com.soulaiman.portfolio.repository.ProjectProxy;

@Service
public class ProjectService {

    @Autowired
    private ProjectProxy projectProxy;

    public Iterable<Project> getProjects() {

        Iterable<Project> projects = projectProxy.getProjects();

        for (Project project : projects){
            project.setHtml(HtmlUtils.htmlUnescape(project.getHtml()));
            project.setTopic(HtmlUtils.htmlUnescape(project.getTopic()));
        }
        return projects;
    }

    public Iterable<String> getTopics() {
        
        Iterable<Project> projects = this.getProjects();
        List<String> topics = new ArrayList<String>();

        for (Project project: projects) {
            if (!topics.contains(project.getTopic())){
                topics.add(project.getTopic());
            }
        }

        List<String> topicsUnescaped = new ArrayList<String>();
        for (String topic : topics) {
            topicsUnescaped.add(HtmlUtils.htmlUnescape(topic));
        }
        return topicsUnescaped;
    }

    public Project saveProject(Project project) {

        Project savedProject;
        savedProject = projectProxy.saveProject(project);

        return savedProject;
    }

    public void deleteProject(Long id){
        projectProxy.deleteProject(id);
    }
    

}
