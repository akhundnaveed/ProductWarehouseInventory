/**
 * 
 */
package org.vantibolli.pwi.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("org.vantibolli.pwi.service.web"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo())
				.tags(new Tag("Country Web Services", "End-points to perform CRUD operations on Country Entity"),
						new Tag("Inventory Web Services", "End-points to perform CRUD operations on Inventory Entity"),
						new Tag("Warehouse Web Services", "End-points to perform CRUD operations on Warehouse Entity"),
						new Tag("Warehouse Web Services", "End-points to perform CRUD operations on Warehouse Entity"),
						new Tag("Product Web Services", "End-points to perform CRUD operations on Product Entity"),
						new Tag("Product Size Web Services", "End-points to perform CRUD operations on Product Size Entity"),
						new Tag("Product Type Web Services", "End-points to perform CRUD operations on Product Type Entity"));
	}
	
	@SuppressWarnings("all")
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Product Warehouse Inventory", "The Product Warehouse Inventory is an assignment by Vantibolli",
				"1.0", "terms", new Contact("Naveed Ahmed", "https://www.linkedin.com/in/naveed-ahmed-31b31926/", "akhundnaveed@gmail.com"),
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
		return apiInfo;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
