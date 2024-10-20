package edu.manjiltamang.music;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Music API Documentation",
                description = "Music Service Microservice API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Manjil Tamang",
                        email = "hello@manjiltamang.com",
                        url = "https://manjiltamang.com"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        )
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
