package walmart.delivery.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Form used in map controller
 * @author haraujo
 *
 */
public class MapForm implements Serializable {

	private static final long serialVersionUID = 7997727641596916306L;

	@NotEmpty
	private String map;
	
	@NotEmpty
	private String origin;
	
	@NotEmpty
	private String destination;
	
	@Min(0)
	@NotNull
	private Double oilAverageConsumption;
	
	@Min(0)
	@NotNull
	private Double oilPrice;
	
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getOilPrice() {
		return oilPrice;
	}
	public void setOilPrice(Double oilPrice) {
		this.oilPrice = oilPrice;
	}
	public Double getOilAverageConsumption() {
		return oilAverageConsumption;
	}
	public void setOilAverageConsumption(Double oilAverageConsumption) {
		this.oilAverageConsumption = oilAverageConsumption;
	}
	
}
