package pjatk.tpo.tpo6_sm_s30679;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pjatk.tpo.tpo6_sm_s30679.Controllers",
        "pjatk.tpo.tpo6_sm_s30679.Services",
        "pjatk.tpo.tpo6_sm_s30679.Repositories",
        "pjatk.tpo.tpo6_sm_s30679.Models"})
public class Tpo6SmS30679Application {

    public static void main(String[] args) {
        SpringApplication.run(Tpo6SmS30679Application.class, args);
    }

}
