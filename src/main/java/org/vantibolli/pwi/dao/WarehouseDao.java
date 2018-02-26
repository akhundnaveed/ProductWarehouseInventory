/**
 * 
 */
package org.vantibolli.pwi.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.vantibolli.pwi.model.Warehouse;

/**
 * @author naveed
 *
 */
@Repository
public class WarehouseDao extends AbstractDao<Warehouse> {

	@Transactional
	public List<Warehouse> findWarehousesByCountryId(Integer countryId) {
		Query query = getCurrentSession().createQuery("from " + Warehouse.class.getName() + " where country.id = " + countryId);
		return query.list();
	}
}