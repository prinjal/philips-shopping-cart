package com.philips.shoppingcart.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    String schemeName = "bearerAuth";
    String bearerFormat = "JWT";
    String scheme = "bearer";

//    @Bean
//    public OpenAPI defineOpenApi() {
//        Server server = new Server();
//        server.setUrl("http://localhost:8080");
//        server.setDescription("Development");
//
//        Contact myContact = new Contact();
//        myContact.setName("Prinjal dave");
//        myContact.setEmail("prinjal.dave@gmail.com");
//
//        Info information = new Info()
//                .title("Shopping Cart API")
//                .version("1.0")
//                .description("This API exposes endpoints to manage shopping cart.")
//                .contact(myContact);
//        return new OpenAPI().addSecurityItem(new SecurityRequirement().
//                addList("Bearer Authentication")).components(new Components().addSecuritySchemes
//                ("Bearer Authentication", createAPIKeyScheme())).info(information).servers(List.of(server));
//    }
//
//    private SecurityScheme createAPIKeyScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }

    @Bean
    public OpenAPI caseOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(schemeName)).components(new Components()
                        .addSecuritySchemes(
                                schemeName, new SecurityScheme()
                                        .name(schemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat(bearerFormat)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme(scheme)
                        )
                )
                .info(new Info()
                        .title("Case Management Service")
                        .description("Claim Event Information")
                        .version("1.0")
                );
    }
}
