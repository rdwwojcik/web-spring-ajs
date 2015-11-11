package tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

/**
 * Created by Radek on 18.09.2015.
 */
@SpringBootApplication
@EntityScan(basePackages = "tutorial.core.model")
@EnableJpaRepositories(basePackages = "tutorial.core.repositories")
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(UiApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
    }
}
