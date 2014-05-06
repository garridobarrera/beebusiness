package es.beebusiness.rest.profesional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.domain.Provincia;
import es.beebusiness.service.IProvinciaService;

@Controller
@RequestMapping("/provincias")
public class ProvinciaRESTController {

	@Autowired
	private IProvinciaService provinciaService;
	
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Provincia>> get(){
		return new ResponseEntity<List<Provincia>>(provinciaService.getAll(),HttpStatus.OK);
	}
}
