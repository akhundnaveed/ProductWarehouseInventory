/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vantibolli.pwi.dao.ProductDao;
import org.vantibolli.pwi.model.Product;

/**
 * @author naveed
 *
 */
@Controller
@RequestMapping("/")
public class PwiWebController {
	
	private static Logger logger = LoggerFactory.getLogger(PwiWebController.class);
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = { "/", "/listProducts" }, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> list(Model model) {
		logger.info("Getting list of products");
		return new ResponseEntity<List<Product>>(productDao.findAll(), HttpStatus.OK);
	}
}
