package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Ravi Nain on 3/11/2018.
 */
@SpringBootApplication(scanBasePackages = "app")
@EnableScheduling
public class EmailApp {

    public static void main(String[] args) {
        SpringApplication.run(EmailApp.class, args);
    }
}
