package ir.hamrahlotus.sso;

import ir.hamrahlotus.sso.model.Role;
import ir.hamrahlotus.sso.repository.RoleRepository;
import ir.hamrahlotus.sso.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

}
