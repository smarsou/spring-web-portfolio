package com.soulaiman.portfolio.repository;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
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
        String getEmployeesUrl = "http://localhost:9001/project";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Project>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Project>>() {}
                );
        
        return response.getBody();
    }

}
