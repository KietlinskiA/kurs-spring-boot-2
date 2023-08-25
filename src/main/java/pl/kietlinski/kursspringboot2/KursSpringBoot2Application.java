package pl.kietlinski.kursspringboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KursSpringBoot2Application {

    public static void main(String[] args) {
        SpringApplication.run(KursSpringBoot2Application.class, args);
    }

}
