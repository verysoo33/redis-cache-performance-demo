package soo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = "soo.demo.repository")
public class SooDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooDemoApplication.class, args);
    }

}
