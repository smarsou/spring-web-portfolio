# Spring Boot Web Portfolio

A Java web application with Spring Boot to manage and render my portfolio.
It includes :
- retrieve projects' data from a custom microservice API (https://github.com/smarsou/spring-api-portfolio).
- display a home page, with my CV and my projects
- manage projects data through an admin panel

It communicates with the API to manage the data which I want to display on my portfolio.

**Tech stack** : Java, Spring Boot, Maven, JUnit, Mockito, Linux, Github CI/CD, REST API, Nginx, Docker, SonarCloud

**Summary**: 

## The Web App
Accessible at http://smarsou.fr
### Home page (/)
<img src=".github\static\portfolio-2.png" width="800"/>

### Admin panel (/admin)
<img src=".github/static/admin.png" width="800"/>

### News

- New app hosted for demonstration, at http://smarsou.fr/lab/hackaton.
- New interface.
- Succesfully implemented a micro-service REST API with CRUD operations to manage the data about all my experience and project which I want to display in my portfolio. 
- Succesfully implemented an admin panel which allows to dynamically add new experience or delete older ones (by using the previous microservice API).
- Simple authentication system implemented for the admin panel (could be more secure by giving the hashed password throught an external source).
- Added Let's Encrypt certificate on the linux server for HTTPS

## Hosting the web app

For hosting the web app, I setup a Ubuntu virtual machine on OVHCloud.

In this virtual machine:
- Firewalls rules to open port 80/tcp and 443/tcp are setup
- Java 17 jre, Docker, and Nginx are installed
- Nginx is configured as a reverse proxy with an ssl certificate from Let's Encrypt.
- A github runner is setup to deploy the microservice REST API continuously after any push on the main branch of the corresponding repository.
- Using docker compose, the app is deployed with :
  - A container for the web app
  - A container for the api
  - A watchtower container

### Docker configuration

Here is the *docker-compose.yml* file which is present in the VM.

    services:
      portfolio-web:
        image: smarsou/web
        container_name: portfolio-web
        ports:
          - 9000:9000
        environment:
          - API_DOMAIN=http://portfolio-api:9001/
        depends_on:
          portfolio-api:
            condition: service_started
        restart: always
      portfolio-api:
        image: smarsou/api
        container_name: portfolio-api
        ports:
          - 9001:9001
        restart: always
      watchtower:
        image: containrrr/watchtower
        container_name: watchtower
        volumes:
          - /var/run/docker.sock:/var/run/docker.sock
        restart: always


## CI/CD

For the Continuous Deployment, the github runner on the ubuntu server does the job.
You can see the configuration of the pipeline in .github/workflows/maven-publish.yml.

A job is triggered every time a push is made on the main branch.

The github workflow job does the following for this repository :
- pull the current repository
- execute the packaging of the Spring app in a jar file.
- build the new image

Then, the image is in the local registry. 


