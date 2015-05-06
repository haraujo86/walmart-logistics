package walmart.delivery.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import walmart.delivery.exception.ServiceException;
import walmart.delivery.model.Map;
import walmart.delivery.model.MapForm;
import walmart.delivery.service.MapService;

/**
 * Controller for map actions
 * @author haraujo
 *
 */
@RestController
@RequestMapping("/map")
public class MapController extends BaseController {
	
	private static final Logger LOGGER = Logger.getLogger(MapController.class);
	
	@Autowired
    private MapService mapService;


    @RequestMapping(value="/create", produces="application/json")
    public String create(@RequestParam(value="map", required=false, defaultValue="") String jsonMap, HttpServletResponse response) {
		
    	LOGGER.info("Criando novo mapa.");
    	try {
			Map map = mapService.insertMap(jsonMap);
			response.setStatus(HttpStatus.CREATED_201);
			return mapService.convertMapToJson(map);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.FORBIDDEN_403);
			return getErrorMessageAsJson(e.getMessage());
		}
    }
    
    @RequestMapping(value="/calculate", method=RequestMethod.GET, produces="application/json")
    public String calculate(@ModelAttribute @Valid MapForm mapForm, BindingResult bindingResult, HttpServletResponse response) {
		
    	LOGGER.info("Calculando menor rota.");
    	try {
			if (bindingResult.hasErrors()) {
				return getErrorMessageAsJson(bindingResult);
			}
			response.setStatus(HttpStatus.ACCEPTED_202);
			return mapService.calculateShortestRoute(mapForm.getMap(), mapForm.getOrigin(), mapForm.getDestination(),
					mapForm.getOilAverageConsumption(), mapForm.getOilPrice());
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.FORBIDDEN_403);
			return getErrorMessageAsJson(e.getMessage());
		}
    }

}