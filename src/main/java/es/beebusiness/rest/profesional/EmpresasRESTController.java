package es.beebusiness.rest.profesional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.domain.Empresa;
import es.beebusiness.service.IEmpresaService;

@Controller
@RequestMapping("/empresas")
public class EmpresasRESTController {

	@Autowired
	private IEmpresaService empresaService;
	
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Empresa>> get(){
		return new ResponseEntity<List<Empresa>>(empresaService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Empresa crearEmpresa(@RequestBody Empresa empresa){
		Empresa e=empresaService.crear(empresa);
		return e;
	}
}
