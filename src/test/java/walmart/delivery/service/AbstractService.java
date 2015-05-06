package walmart.delivery.service;

public abstract class AbstractService {

	public static final String JSON = "{\"route\": \"A, B, D\", \"totalCost\":6.25}";
	public static final String INSERT_JSON = "{\"name\":\"SP\", \"routes\": [ {\"origin\":\"A\", \"destination\":\"B\", \"distance\":\"10\"}, {\"origin\":\"B\", \"destination\":\"D\", \"distance\":\"15\"},  {\"origin\":\"A\", \"destination\":\"C\", \"distance\":\"2.10\"},  {\"origin\":\"C\", \"destination\":\"D\", \"distance\":\"30\"},  {\"origin\":\"B\", \"destination\":\"E\", \"distance\":\"50\"},  {\"origin\":\"D\", \"destination\":\"E\", \"distance\":\"30\"}]}";

}
