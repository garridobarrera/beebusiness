package es.beebusiness.rest.profesional;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.domain.Sector;
import es.beebusiness.service.ISectorService;

@Controller
@RequestMapping("/sectores")
public class SectorRESTController {

	@Autowired
	private ISectorService sectorService;
	
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sector>> get(){
		return new ResponseEntity<List<Sector>>(sectorService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{nombre}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Sector> getSector(@PathVariable String nombre){
		try{
		return new ResponseEntity<Sector>(sectorService.get(nombre),HttpStatus.OK);
		}catch(NoResultException e){
			return new ResponseEntity<Sector>(HttpStatus.NOT_FOUND);
		}
	}
}
