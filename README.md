# 🍃 Kanban Task Management Application : Back-End Module
## Overview 
The Kanban Task Management Application is a full-stack task management solution that allows users to create, update, and delete tasks, as well as move them across different stages of completion. The application is built using Spring Boot and React, and containerized using Docker. The back-end module is deployed on an Oracle Cloud Virtual Instance.

**Explore the Front-End Module repository [here](https://github.com/Slimani-CE/kanban-front-end).**

#
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)   ![Postgres](https://img.shields.io/badge/postgresql-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apache-maven&logoColor=white) ![Lombok](https://img.shields.io/badge/lombok-%23F7B500.svg?style=for-the-badge&logo=lombok&logoColor=white) ![JUnit](https://img.shields.io/badge/junit-%23000.svg?style=for-the-badge&logo=junit&logoColor=white) ![Postman](https://img.shields.io/badge/postman-%23FF6C37.svg?style=for-the-badge&logo=postman&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)  

## Database Schema
![Database Schema](assets/db-schema.png)

## Project Structure
```markdown
C:.
├───main
│   ├───java
│       └───com
│           └───slimanice
│               └───kanbantaskmanagementapp
│                   ├───configuration       => contains configuration classes
│                   ├───dto                 => contains data transfer objects
│                   ├───entity              => contains entity classes
│                   ├───exception           => contains exception classes
│                   ├───mapper              => contains mapper classes
│                   ├───repository          => contains repository classes
│                   ├───service             => contains service classes
│                   └───web                 => contains web classes
└───test
    └───java
        └───com
            └───slimanice
                └───kanbantaskmanagementapp => contains test classes
```     

## Technologies & Dependencies
The project utilizes various dependencies managed through Maven, including:

| Dependency      | Use Case                                                                                               |
|-----------------|--------------------------------------------------------------------------------------------------------|
| Spring Web      | Build RESTful Web Service                                                                              |
| Spring Data JPA | Persist Data in SQL stores with Java Persistence API using Spring Data and Hibernate                   |
| PostgreSQL      | Open-Source Relational Database Management System                                                      |
| Lombok          | Never write another getter or equals method again, with one annotation                                 |
| Actuator        | Monitor and manage your application                                                                    |
| DevTools        | Provides fast application restarts, LiveReload, and configurations for enhanced development experience |
| JUnit           | Unit testing framework for Java                                                                        |
| Postman         | API Development & Test Environment                                                                     |
| Docker          | Containerization Platform                                                                              |



## Application Configuration
```properties
spring.application.name=kanban-app
server.port=8085
spring.datasource.url=jdbc:postgresql://${POSTGRES_HOST:localhost}:${POSTGRES_PORT:5432}/kanban
spring.datasource.username=${POSTGRES_USER:postgres}
spring.datasource.password=${POSTGRES_PASSWORD:root}
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.cloud.config.enabled=false
```

## Application Dockerization
1. Dockerize the postgres database
```bash
sudo docker pull postgres
sudo docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=root -e POSTGRES_USERNAME=postgres postgres
sudo docker logs postgres  # check logs to see if the database is running
# Create the database called kanban
sudo docker exec -it postgres bash
psql -h localhost -U postgres
```

2. Dockerize the Spring Boot application
```dockerfile
FROM openjdk:17-oracle
VOLUME /tmp
ADD target/kanban-task-management-app*.jar /app.jar
CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
EXPOSE 8085
```

The application is then built and run using the following commands:
```bash
sudo docker build -t kanban-app .
# For local containerization (using the local postgres database)
sudo docker run -d --name kanban-backend -p 8090:8085 -e POSTGRES_HOST=172.17.0.2 -e POSTGRES_PORT=5432 -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=root kanban-backend
```

## 🔮 Future Scope :
Future iterations of the application may include:

1. 🤝 Real-time Collaboration: Implementing features for real-time collaboration on tasks among multiple users.
2. 🌟 Enhanced User Experience: Continual refinement of the user interface and user experience based on user feedback.
3. 📊 Advanced Analytics: Introducing analytics and reporting features to track productivity and task progress.

## Conclusion


## Author
- 2023 © [Mustapha SLIMANI](https://slimani-ce.github.io)
- LinkedIn: [Mustapha SLIMANI](https://www.linkedin.com/in/mustaphaslimani/)