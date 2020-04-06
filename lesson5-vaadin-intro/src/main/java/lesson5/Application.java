package lesson5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class Application extends SpringApplicationBuilder {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
