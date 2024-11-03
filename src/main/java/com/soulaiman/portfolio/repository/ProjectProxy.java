package com.soulaiman.portfolio.repository;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.soulaiman.portfolio.model.Project;

import reactor.core.publisher.Flux;

@Component
public class ProjectProxy {

    // public List<Project> getProjects(){

    //     WebClient client = WebClient.create("http://localhost:9001");
    //     return client.get()
    //         .uri(URI.create("/project"))
    //         .retrieve()
    //         .bodyToFlux(Project.class).collectList().block();
            
    // }

    public Iterable<Project> getProjects() {
        String getProjectsUrl = "http://localhost:9001/project";

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
        String postProjectUrl = "http://localhost:9001/project";

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
        String deleteProjectUrl = "http://localhost:9001/project/" + Long.toString(id);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteProjectUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        System.out.println("Delete Project call " + response.getStatusCode().toString());

    }

}
