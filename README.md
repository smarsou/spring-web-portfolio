﻿# Spring Boot Web Portfolio

README in progress...

## The Web App

This web app is a Java Spring Boot web application which manages my portfolio. 

It communicates with another Spring Boot application implemented as an API (which you can find in the repo spring-api-portfolio).
This API allows the web application to manage the data which I want to display on my portfolio. It handles CRUD operations.

### News

- Succesfully implemented a micro-service REST API with CRUD operations to manage the data about all my experience and project which I want to display in my portfolio. 

### Roadmap

- Add autentication system
- Implement an admin panel which allows to dynamically add new experience or modify/delete older ones (by using the previous microservice API).

## Hosting the web app

For hosting the web app, I setup a Ubuntu virtual machine using Microsoft Azure Portal.

In this virtual machine, I managed :
- to open the port 80/tcp and 443/tcp
- to install a java jre
- the configuration of a systemd service to start and stop the SPRING BOOT web app
- to install a github runner to deploy the Spring Boot web app continuously after any push on the main branch of this repository.
- the configuration of a systemd service for the github runner which run the jobs of the continous deployment.
- to start the Spring Boot API by cloning and starting the app from the repo 'spring-api-portfolio'.

## CI/CD

For the Continuous Deployment, I installed a github runner on the ubuntu server.
You can see the configuration of the pipeline in .github/workflows/maven-publish.yml.

A job is triggered every time a push is made on the main branch.

The job does the following:
- stop the old SPRING BOOT web app and clean the repository in which we have the Jar file.
- import the current repository
- execute the packaging of the Springg app in a jar file.
- start the new jar (by starting the systemd service)

Read the configuration file for more details.

