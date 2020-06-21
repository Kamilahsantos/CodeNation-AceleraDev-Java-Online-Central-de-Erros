package br.com.aceleradev.java.centraldeerros.swagger;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("br.com.aceleradev.java.centraldeerros.controller"))
      .paths(PathSelectors.ant("api/event/*"))
      .build()
      .useDefaultResponseMessages(false)
      .apiInfo(apiInfo());

  }

  private ApiInfo apiInfo(){

    return new ApiInfoBuilder()
      .title("Central de Erros API")
      .description("Aplicação Spring Boot do projeto de conclusão do aceleraDev Java")
      .version("1.0.0")
      .license("Apache License Version 2.0")
      .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
      .contact(new Contact("Squad1", "https://github.com/Kamilahsantos/CodeNation-AceleraDev-Java-Online-Central-de-Erros",null))
      .build();

  }
}