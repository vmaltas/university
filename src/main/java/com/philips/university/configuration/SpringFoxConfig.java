package com.philips.university.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringFoxConfig {
    Contact contact = new Contact(
            "Veli Mert Altas",
            "https://github.com/vmaltas",
            "mertaltas@gmail.com"
    );
    List<VendorExtension> vendorExtensions = new ArrayList<>();
    ApiInfo apiInfo = new ApiInfo(
            "University Assignment RESTful Web Service documentation",
            "This pages documents University Assignment app RESTful Web Service endpoints", "1.0",
            "https://github.com/vmaltas/university/blob/main/README.md", contact,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}


