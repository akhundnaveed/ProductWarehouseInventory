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
 * The Spring Service class to perform Country related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
@Service
public class CountryService {
	
	@Autowired
	private CountryDao countryDao;
	
	/**
	 * Get a list of all Countries from the database
	 * 
	 * @return list of Countries
	 */
	public List<Country> listAllCountries() {
		return countryDao.findAll();
	}
	
	/**
	 * Store given Country object record in the database
	 * 
	 * @param country
	 *            the Country object to be stored in the database
	 */
	public void addCountry(Country country) {
		countryDao.save(country);
	}
	
	/**
	 * Update given Country object record in the database
	 * 
	 * @param country
	 *            the Country object to be updated in the database
	 */
	public void updateCountry(Country country) {
		countryDao.update(country);
	}
	
	/**
	 * Find Country object from the database against given country id
	 * 
	 * @param id
	 *            the id of Country to find
	 * @return Country object found from the database
	 */
	public Country findCountryById(Integer id) {
		return countryDao.findOne(id);
	}
	
	/**
	 * Delete the given Country object from the database
	 * 
	 * @param country
	 *            the Country object to be deleted from the database
	 */
	public void deleteCountry(Country country) {
		countryDao.delete(country);
	}
}
