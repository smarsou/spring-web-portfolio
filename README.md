# Spring Boot Web Portfolio

A Java web application with Spring Boot to manage and render my portfolio.
It includes :
- retrieve projects' data from a custom microservice API (https://github.com/smarsou/spring-api-portfolio).
- display a home page, with my CV and my projects
- manage projects data through an admin panel

It communicates with the API to manage the data which I want to display on my portfolio.

**Tech stack** : Java, Spring Boot, Maven, Azure VM, Linux, Github CI/CD, REST API, Nginx

## The Web App
Accessible at http://smarsou.fr
### Home page (/)
<img src=".github/static/home.png" width="800"/>

### Admin panel (/admin)
<img src=".github/static/admin.png" width="800"/>

### News

- Succesfully implemented a micro-service REST API with CRUD operations to manage the data about all my experience and project which I want to display in my portfolio. 
- Succesfully implemented an admin panel which allows to dynamically add new experience or delete older ones (by using the previous microservice API).
- Simple authentication system implemented for the admin panel (could be more secure by giving the hashed password throught an external source).
- Added Let's Encrypt certificate on the linux server for HTTPS

### Roadmap

- deploy using Docker.
- Implement tests
- Add the modify feature in the admin panel (only add and delete is implemented).

## Hosting the web app

For hosting the web app, I setup a Ubuntu virtual machine using Microsoft Azure Portal.

In this virtual machine, I managed :
- to open the port 80/tcp and 443/tcp
- to install a java jre
- to configure NGINX as a reverse proxy (still need to configure ssl for https)
- to install a github runner to deploy the Spring Boot web app continuously after any push on the main branch of this repository.
- the configuration of a systemd service to start and stop the SPRING BOOT web app
- the configuration of a systemd service for the github runner which run the jobs of the continous deployment.
- to start the Spring Boot API by cloning and starting the app from the repo 'spring-api-portfolio'.

## CI/CD

For the Continuous Deployment, I installed a github runner on the ubuntu server.
You can see the configuration of the pipeline in .github/workflows/maven-publish.yml.

A job is triggered every time a push is made on the main branch.

The job does the following:
- stop the old SPRING BOOT web app and clean the repository in which we have the Jar file.
- import the current repository
- execute the packaging of the Spring app in a jar file.
- start the new jar (by starting the systemd service)

Read the configuration file for more details.

