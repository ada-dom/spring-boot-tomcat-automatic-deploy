# Spring Boot Tomcat Automatic Deploy

Okay, let's get our hands dirty and use Spring Boot to create a web application.\
Spring Boot takes creates a webserver (Tomcat) under the hood.

> ℹ️Codelab08 builds the same project. But it contains instructions to manually
> create a war file and deploy it to tomcat.\
> This way of working is still very common in most IT departments: The deployment of
> a war file to production webservers is the duty or responsibility of a system
> administrators team (sysadmin). Development teams usually do not have access to 
> production servers. However, some organisations challenge this idea, and adopt 
> a DevOps culture. 

## Now, create a new project

Create a new Maven project:
- GroupId = com.switchfully.spring
- ArtifactId = spring-boot-tomcat-automatic-deploy

1. Add the Spring Boot dependency, using the dependency management system (parent pom by Spring Boot)
    ```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.0.RELEASE</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
    ```
2. Add the Spring Boot Maven plugin
    ```
    <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>
    ```
    - Spring Boot Maven plugin collects all the jars on the classpath and builds a single, runnable "über-jar", which makes it more convenient to execute and transport your service.
    - Spring Boot Maven plugin searches for the public static void main() method to flag as a runnable class.
2. Create a main method class (e.g. Application):
    ```
    @SpringBootApplication
    public class Application {
    
        public static void main(String[] args) throws Exception {
            SpringApplication.run(Application.class, args);
        }
    
    }
    ```
3. Create a controller class, it will be served to the end user when he navigates to the root url ("/")
    ```
    @Controller
    public class GreetingController {
    
        @RequestMapping("/")
        @ResponseBody
        String getWelcomeMessage() {
            return "Hello World!";
        }
    
    }
    ```     
4. Run `mvn clean package`
    - In your target folder, a JAR should be generated
5. From withing your target folder, execute command `java -jar <YOUR-JAR-NAME>.jar`
    - Make sure you killed the (stand-alone) Tomcat process. When the port 8080 is occupied, 
    your embedded Tomcat won't be able to start. (Only one process per port is allowed...)
    - Navigate to `http://localhost:8080`
          - The message "Hello World!" should be shown
6. There are extra options to run your application:
    - Run Application.java inside your IDE OR execute command `mvn spring-boot:run`
        - Navigate to `http://localhost:8080`
           - The message "Hello World!" should be shown
           
"But hey... we generated a JAR, not a WAR?"
- Correct, it's a so called "Fat jar". It's an executable JAR that contains all of our dependencies, 
including our embedded web container (Tomcat), which we can run with `java -jar <JAR-NAME>.jar`.
- We can generate a WAR with Spring Boot, if we want the traditional manual deployment (see codelab08)
    
Do know, that this is really something what Spring Boot is offering us...
With only a few lines of code, we have a fully operational Java web application.
- No XML configuration (e.g. web.xml)
- We didn't have to deal with configuring any plumbing or infrastructure
- No manual deploy

## Solution

A solution is provided on https://github.com/switchfully/spring-boot-tomcat-automatic-deploy
- Only check it out when you're completely finished.
- Don't if you're stuck. If you're stuck: ask questions!