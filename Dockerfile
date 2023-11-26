FROM openjdk:17-oracle
VOLUME /tmp
ADD target/kanban-task-management-app*.jar /app.jar
CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]
EXPOSE 8085