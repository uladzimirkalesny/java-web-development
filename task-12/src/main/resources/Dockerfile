FROM tomcat:9.0.26-jdk8-openjdk
ENV PROJECT_ROOT /usr/local/tomcat/webapps/
WORKDIR $PROJECT_ROOT
RUN rm -r /usr/local/tomcat/webapps/ROOT
ARG warFile
COPY ${warFile} $PROJECT_ROOT
EXPOSE 8080:80
CMD ["catalina.sh", "run"]