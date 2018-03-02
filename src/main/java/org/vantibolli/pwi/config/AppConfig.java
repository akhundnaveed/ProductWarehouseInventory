/**
 * 
 */
package org.vantibolli.pwi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <h1>AppConfig</h1>
 * This class is only a declaration that it contains Spring Configuration,
 * declares that PWI is a Spring MVC App and scans for Spring components
 * in following two packages
 * <ul>
 * <li> org.vantibolli.pwi.service
 * <li> org.vantibolli.pwi.service
 * </ul>
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.vantibolli.pwi.service", "org.vantibolli.pwi.service.web" })
public class AppConfig {
	
}
