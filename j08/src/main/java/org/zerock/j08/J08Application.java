package org.zerock.j08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class J08Application {

    public static void main(String[] args) {
        SpringApplication.run(J08Application.class, args);
    }

}
