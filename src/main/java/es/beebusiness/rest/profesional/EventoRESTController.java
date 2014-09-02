package es.beebusiness.rest.profesional;

import java.util.Date;
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

import es.beebusiness.domain.Evento;
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
	
}
