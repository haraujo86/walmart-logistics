package walmart.delivery.controller;

import static org.powermock.api.mockito.PowerMockito.when;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import walmart.delivery.config.Application;
import walmart.delivery.exception.ServiceException;
import walmart.delivery.model.Map;
import walmart.delivery.model.Route;
import walmart.delivery.service.MapService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class MapControllerTests extends AbstractController {
	
	@Autowired
    private WebApplicationContext wac;
	
	@InjectMocks
	private MapController mapController = new MapController();
	
	@Mock
	private MapService mapService = new MapService();

	private MockMvc mockMvc;
	
    @Before
    public void setUp() {
    	this.mockMvc = MockMvcBuilders.standaloneSetup(mapController).build();
   		MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testCreate() throws Exception {
    	
    	mockInsertMap();
    	mockJsonMap();
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/map/create")
                .accept(MediaType.APPLICATION_JSON)
                .param("map", INSERT_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED_201));
    }
    
    @Test
    public void testCalculate() throws Exception {
    	
    	mockCalculateShotestRoute();
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/map/calculate")
                .accept(MediaType.APPLICATION_JSON)
                .param("map", "MapTest1")
                .param("origin", "A")
                .param("destination", "D")
                .param("oilAverageConsumption", "10.5")
                .param("oilPrice", "2"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.ACCEPTED_202));
    }
   
    private void mockJsonMap() throws ServiceException {
		when(mapService.convertMapToJson(Mockito.any(Map.class))).thenReturn(INSERT_JSON);
	}
    
    private void mockInsertMap() throws ServiceException {
		Map map1 = new Map();
		map1.setId(1L);
		map1.setName("MapTest1");

		Route route1 = new Route();
		route1.setId(1L);
		route1.setDestination("B");
		route1.setOrigin("A");
		route1.setDistance(100.50D);
		route1.setMap(map1);
		
		when(mapService.insertMap(Mockito.any(String.class))).thenReturn(map1);

	}
    
    private void mockCalculateShotestRoute() throws ServiceException {
    	when(mapService.calculateShortestRoute(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class),Mockito.any(Double.class),Mockito.any(Double.class)))
    	.thenReturn(SHORTEST_ROUTE_JSON);
    }
	
}
