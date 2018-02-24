/**
 * 
 */
package org.vantibolli.pwi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author naveed
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.vantibolli.pwi.service")
public class AppConfig {
	
}
