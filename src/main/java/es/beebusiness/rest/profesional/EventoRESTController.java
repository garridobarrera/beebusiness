package es.beebusiness.rest.profesional;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Ponente;
import es.beebusiness.domain.Sector;
import es.beebusiness.service.IEventoService;

@Controller
@RequestMapping("/eventos")
public class EventoRESTController {

	@Autowired
	private IEventoService eventoService;
	
	@RequestMapping(value = "/proximos",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Evento>> get(@RequestParam("inicio") int inicio,@RequestParam("total") int total,@RequestParam(value="fecha-inicio",required=false) Date fechaInicio){
		List<Evento> eventos=eventoService.getAll(inicio, total, fechaInicio);
		return new ResponseEntity<List<Evento>>(eventos,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Evento> get(@PathVariable("id") long id){
		Evento evento=eventoService.get(id);
		return new ResponseEntity<Evento>(evento,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/ponentes",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Ponente>> getPonentes(@PathVariable("id") long id){
		List<Ponente> ponentes=eventoService.getPonentes(id);
		return new ResponseEntity<List<Ponente>>(ponentes,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/sectores",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sector>> getSectores(@PathVariable("id") long id){
		List<Sector> sectores=eventoService.getSectores(id);
		return new ResponseEntity<List<Sector>>(sectores,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/tematicas",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sector>> getTematicas(@PathVariable("id") long id){
		List<Sector> sectores=eventoService.getTematicas(id);
		return new ResponseEntity<List<Sector>>(sectores,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/perfiles",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Perfil>> getPerfiles(@PathVariable("id") long id){
		List<Perfil> perfiles=eventoService.getPerfiles(id);
		return new ResponseEntity<List<Perfil>>(perfiles,HttpStatus.OK);
	}
	
}
