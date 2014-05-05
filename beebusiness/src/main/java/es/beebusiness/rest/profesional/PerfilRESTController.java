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

import es.beebusiness.domain.Perfil;
import es.beebusiness.service.IPerfilService;

@Controller
@RequestMapping("/perfiles")
public class PerfilRESTController {

	@Autowired
	private IPerfilService perfilService;
	
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Perfil>> get(){
		return new ResponseEntity<List<Perfil>>(perfilService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/{nombre}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Perfil> getPerfil(@PathVariable String nombre){
		try{
		return new ResponseEntity<Perfil>(perfilService.get(nombre),HttpStatus.OK);
		}catch(NoResultException e){
			return new ResponseEntity<Perfil>(HttpStatus.NOT_FOUND);
		}
	}
}
