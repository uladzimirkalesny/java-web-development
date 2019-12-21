FROM openjdk:8-alpine
ENV PROJECT_ROOT /task/app/
WORKDIR $PROJECT_ROOT
ARG dockerDeveloper
LABEL maintainer=${dockerDeveloper}
ARG jarFile
COPY ${jarFile} $PROJECT_ROOT
CMD ["java", "-jar", "/task/app/task-9-1.jar"]