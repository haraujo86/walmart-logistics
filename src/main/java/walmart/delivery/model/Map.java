package walmart.delivery.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Model for represent a map entity
 * @author haraujo
 *
 */
@Entity
@SequenceGenerator(name="seq_map", sequenceName="seq_map")
@NamedQueries({ @NamedQuery(name = "Map.findByName", query = "SELECT m FROM Map m WHERE m.name = :name") })
public class Map implements Serializable {

	private static final long serialVersionUID = 4143660541055984631L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_map")
	private Long id;
	
	@Column
	private String name;

	@OneToMany(mappedBy="map", fetch=FetchType.EAGER)
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Route> routes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void populateRouteParent() {
		if (this.getRoutes() != null && !this.getRoutes().isEmpty()) {
			for (Route route : this.getRoutes()) {
				route.setMap(this);
			};
		}
	}
}
