package es.beebusiness.rest.profesional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.domain.Sector;
import es.beebusiness.service.ISectorService;

@Controller
@RequestMapping("/topics")
public class SectorRESTController {

	@Autowired
	private ISectorService sectorService;
	
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sector>> get(@RequestParam(value = "tipo", required = false) String tipo){
		return new ResponseEntity<List<Sector>>(sectorService.getAll(tipo),HttpStatus.OK);
	}
	
}
