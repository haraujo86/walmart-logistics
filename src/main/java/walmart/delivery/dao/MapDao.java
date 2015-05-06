package walmart.delivery.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import walmart.delivery.exception.DAOException;
import walmart.delivery.model.Map;

/**
 * Dao for map interactions
 * @author haraujo
 *
 */
@Repository
public class MapDao extends GenericDao<Map, Long> {
	
	public Map findByName(String name) throws DAOException {
		Query query = getNamedQuery("Map.findByName").setParameter("name", name);
		return (Map) getSingleResult(query);		
	}
 
}
