/**
 * 
 */
package org.vantibolli.pwi.service.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The root web controller to forward root to Swagger-UI path to display application APIs
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Controller
@RequestMapping("/")
public class RootWebController {
	/**
	 * This method forwards root path request to Swagger-UI
	 * 
	 * @return Swagger-UI path to be forward from root path
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}
}
