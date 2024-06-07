package com.bci.login.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Login",
                description = "Servicio encargado registrar y consultar usuarios",
                contact = @Contact(name = "BCI",
                        email = "Prueba@gl.com"))
)
public class SwaggerConfig {

}
