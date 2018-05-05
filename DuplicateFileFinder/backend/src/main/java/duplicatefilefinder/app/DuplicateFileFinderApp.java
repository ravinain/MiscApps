package duplicatefilefinder.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Ravi Nain on 4/14/2018.
 */
@SpringBootApplication(scanBasePackages = "duplicatefilefinder.app")
public class DuplicateFileFinderApp {

    public static void main(String[] args) {
        SpringApplication.run(DuplicateFileFinderApp.class, args);
    }
}
