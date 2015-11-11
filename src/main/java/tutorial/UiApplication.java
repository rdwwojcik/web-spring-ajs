package tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class UiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UiApplication.class, args);
    }

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/services/bi")
    public String[][] bi(@RequestBody String name) {
        System.out.println(name);
        String[][] out = new String[2][2];

        out[0][0] = "Imie";
        out[0][1] = "Nazwisko";

        out[1][0] = "Kamil";
        out[1][1] = "Kurek";

        return out;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

//    @RequestMapping("/services/radek")
//    public User user() {
//        return new User("Radek","Wojcik");
//    }

//    @EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class })

//    @Configuration
//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.httpBasic()
//            .and()
//                .authorizeRequests()
//                .antMatchers("/*", "/templates/*","/fonts/*","/services/*","/adam")
//                    .permitAll()
//                    .anyRequest()
//                .authenticated();
//        }
//    }

}
