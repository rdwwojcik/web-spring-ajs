package tutorial.config;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tutorial.UiApplication;

import java.io.IOException;

/**
 * Created by Radek on 2015-09-04.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages = "tutorial.core.model")
@EnableJpaRepositories(basePackages = "tutorial.core.repositories")
@EnableTransactionManagement
public class RepositoryConfiguration {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(UiApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
    }

}
