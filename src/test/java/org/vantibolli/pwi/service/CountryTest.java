/**
 * 
 */
package org.vantibolli.pwi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.vantibolli.pwi.dao.CountryDao;
import org.vantibolli.pwi.model.Country;
import org.vantibolli.pwi.service.CountryService;

/**
 * The class to test Country related operations
 * 
 * @author Naveed Ahmed
 * @version 1.0
 * @since 23-Feb-2018
 */
public class CountryTest {
	
	@Mock
	private CountryDao countryDao;
	
	@InjectMocks
	private CountryService countryService;
	
	@Spy
	private List<Country> countryList = new ArrayList<>();
	
	/**
	 * Setup mockito mock objects and initialize test Country objects list
	 */
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		initCountryList();
	}
	
	/**
	 * Test listAllCountries service method
	 */
	@Test
	public void listAllCountries() {
		when(countryDao.findAll()).thenReturn(countryList);
		List<Country> prodList = countryService.listAllCountries();
		assertThat(prodList).isEqualTo(countryList);
	}
	
	/**
	 * Test saveCountry service method
	 */
	@Test
	public void saveCountry() {
		doNothing().when(countryDao).save(any(Country.class));
		countryService.addCountry(any(Country.class));
		verify(countryDao, atLeastOnce()).save(any(Country.class));
	}
	
	/**
	 * Test findCountryById service method
	 */
	@Test
	public void findCountryById() {
		Country country = countryList.get(0);
		when(countryDao.findOne(anyInt())).thenReturn(country);
		assertThat(country).isEqualTo(countryService.findCountryById(country.getId()));
	}
	
	/**
	 * Test updateCountry service method
	 */
	@Test
	public void updateCountry() {
		Country country = countryList.get(0);
		when(countryDao.findOne(anyInt())).thenReturn(country);
		countryService.updateCountry(country);
		verify(countryDao, atLeastOnce()).findOne(anyInt());
	}
	
	/**
	 * Test deleteCountry service method
	 */
	@Test
	public void deleteCountry() {
		doNothing().when(countryDao).delete(any(Country.class));
		countryService.deleteCountry(any(Country.class));
		verify(countryDao, atLeastOnce()).delete(any(Country.class));
	}
	
	/**
	 * Initialize a test Country list
	 */
	private void initCountryList() {
		Country country = new Country();
		country.setId(7);
		country.setName("USA");
		countryList.add(country);
		
		country = new Country();
		country.setId(8);
		country.setName("Turkey");
		countryList.add(country);
		
		country = new Country();
		country.setId(9);
		country.setName("Japan");
		countryList.add(country);
	}
}
