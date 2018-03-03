/**
 * 
 */
package org.vantibolli.pwi.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * <h1>PwiAppInitializer</h1> This class extends from AbstractAnnotationConfigDispatcherServletInitializer which is used instead of web.xml in most webapps. This class provides the root Spring
 * configuration classes.
 * <p>
 * This class also will create a dispatcher servlet with / mapping.
 * </p>
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public class PwiAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/**
	 * Specify @Configuration and/or @Component classes for the Servlet application context.
	 * 
	 * @return the configuration for the root application context, or null if creation and registration of a root context is not desired
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, HibernateConfig.class, SwaggerConfig.class };
	}
	
	/**
	 * Specify @Configuration and/or @Component classes for the Servlet application context.
	 * 
	 * @return the configuration for the Servlet application context, or null if all configuration is specified through root config classes. 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
	
	/**
	 * Specify the servlet mapping(s) for the DispatcherServlet — for example "/", "/app", etc.
	 * 
	 * @return String[] Array of servlet mapping(s)
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/api/" };
	}
	
}
