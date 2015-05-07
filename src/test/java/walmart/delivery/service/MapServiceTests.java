package walmart.delivery.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import walmart.delivery.dao.MapDao;
import walmart.delivery.exception.DAOException;
import walmart.delivery.exception.ServiceException;
import walmart.delivery.model.Map;
import walmart.delivery.model.Route;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MapService.class })
public class MapServiceTests {
	
	private static final String JSON = "{\"route\": \"A, B, D\", \"totalCost\":6.25}";
	private static final String INSERT_JSON = "{\"name\":\"MapTest1\", \"routes\": [ {\"origin\":\"A\", \"destination\":\"B\", \"distance\":\"10\"}]}";

	@InjectMocks
	private MapService mapService = new MapService();

	@Mock
	private MapDao mapDao = new MapDao();
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInsertMap() throws ServiceException, DAOException {
		mockPersistMap();
		Map map = mapService.insertMap(INSERT_JSON);
		assertNull(map.getId());
	}
	
	@Test
	public void testUpdateMap() throws ServiceException, DAOException {
		mockMap();
		mockMergeMap();
		Map map = mapService.insertMap(INSERT_JSON);
		assertEquals("1", map.getId().toString());
	}
	
	@Test
	public void testCalculateShortestRoute() throws ServiceException {
		mockMap();
		String routeJson = mapService.calculateShortestRoute("MapTest1", "A", "D", 10D, 2.5D);
		assertEquals(1, 2);
	}
	
	private void mockMap() throws ServiceException {
		Map map1 = new Map();
		map1.setId(1L);
		map1.setName("MapTest1");

		Route route1 = new Route();
		route1.setId(1L);
		route1.setOrigin("A");
		route1.setDestination("B");
		route1.setDistance(10D);
		route1.setMap(map1);
		
		Route route2 = new Route();
		route2.setId(2L);
		route2.setOrigin("B");
		route2.setDestination("D");
		route2.setDistance(15D);
		route2.setMap(map1);
		
		Route route3 = new Route();
		route3.setId(3L);
		route3.setOrigin("A");
		route3.setDestination("C");
		route3.setDistance(20D);
		route3.setMap(map1);
		
		Route route4 = new Route();
		route4.setId(4L);
		route4.setOrigin("C");
		route4.setDestination("D");
		route4.setDistance(30D);
		route4.setMap(map1);
		
		Route route5 = new Route();
		route5.setId(5L);
		route5.setOrigin("B");
		route5.setDestination("E");
		route5.setDistance(50D);
		route5.setMap(map1);
		
		Route route6 = new Route();
		route6.setId(6L);
		route6.setOrigin("D");
		route6.setDestination("E");
		route6.setDistance(30D);
		route6.setMap(map1);
		
		map1.setRoutes(new ArrayList<Route>());
		map1.getRoutes().add(route1);
		map1.getRoutes().add(route2);
		map1.getRoutes().add(route3);
		map1.getRoutes().add(route4);
		map1.getRoutes().add(route5);
		map1.getRoutes().add(route6);
		
		when(mapService.findMapByName(Mockito.any(String.class))).thenReturn(map1);
	}

	public void mockPersistMap() throws DAOException {
		when(mapDao.persist(Mockito.any(Map.class))).thenReturn(generateMap());
	}
	
	public void mockMergeMap() throws DAOException {
		when(mapDao.merge(Mockito.any(Map.class))).thenReturn(generateMap());
	}
	
	private Map generateMap() {
		Map map1 = new Map();
		map1.setId(1L);
		map1.setName("MapTest1");

		Route route1 = new Route();
		route1.setId(1L);
		route1.setOrigin("A");
		route1.setDestination("B");
		route1.setDistance(100.50D);
		route1.setMap(map1);
		
		return map1;
	}

}
