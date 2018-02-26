/**
 * 
 */
package org.vantibolli.pwi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vantibolli.pwi.dao.CountryDao;
import org.vantibolli.pwi.model.Country;

/**
 * @author naveed
 *
 */
@Service
public class CountryService {
	
	@Autowired
	private CountryDao countryDao;
	
	public List<Country> listAllCountries() {
		return countryDao.findAll();
	}
	
	public void addCountry(Country country) {
		countryDao.save(country);
	}
	
	public void updateCountry(Country country) {
		countryDao.update(country);
	}
	
	public Country findCountryById(Integer id) {
		return countryDao.findOne(id);
	}
	
	public void deleteCountry(Country country) {
		countryDao.delete(country);
	}
}
