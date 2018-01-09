package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"application.animal", "application.method", "application.project", "application.schedule", "application.repository"})
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
