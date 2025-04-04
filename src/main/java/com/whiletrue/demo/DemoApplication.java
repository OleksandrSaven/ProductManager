package com.whiletrue.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "WHILETRUE API",
                version = "1.0",
                description = "API manege products",
                contact = @Contact(
                        name = "Support Team",
                        email = "savenvoin@gmail.com",
                        url = "https://savenvoin.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
