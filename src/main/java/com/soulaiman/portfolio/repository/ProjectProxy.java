package com.soulaiman.portfolio.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.soulaiman.portfolio.model.Project;

@Component
public class ProjectProxy {

    @Value("${api.domain}")
    private String apiDomain;

    public Iterable<Project> getProjects() {
        String getProjectsUrl = this.apiDomain + "project";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Project>> response = restTemplate.exchange(
                getProjectsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Project>>() {}
                );
        
        return response.getBody();
    }

    public Project saveProject(Project project){
        String postProjectUrl = this.apiDomain + "project";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Project> request = new HttpEntity<Project>(project);

        if (project.get_id() == null){
            ResponseEntity<Project> response = restTemplate.exchange(
                postProjectUrl,
                HttpMethod.POST,
                request,
                Project.class);
            
            System.out.println("Create Project call " + response.getStatusCode().toString());
            return response.getBody();
        }else{
            ResponseEntity<Project> response = restTemplate.exchange(
                postProjectUrl + "/" + project.get_id().toString(),
                HttpMethod.PUT,
                request,
                Project.class);

            System.out.println("Update Project call " + response.getStatusCode().toString());
            return response.getBody();
        }
    }

    public void deleteProject(Long id){
        String deleteProjectUrl = this.apiDomain + "project/" + Long.toString(id);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteProjectUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        System.out.println("Delete Project call " + response.getStatusCode().toString());

    }

}
