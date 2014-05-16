package es.beebusiness.rest.profesional;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.authenticator.ConstantesAutenticator;
import es.beebusiness.authenticator.Credencial;
import es.beebusiness.authenticator.Encrypter;
import es.beebusiness.authenticator.Token;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IProfesionalService;
import es.beebusiness.util.Constantes;

@Controller
@RequestMapping("/profesional")
public class ProfesionalRESTController {

	@Autowired
	private IProfesionalService profesionalService;
	@Autowired
	private Encrypter encode;
	@RequestMapping(value="/authenticate",method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Token> authenticate(@RequestBody Credencial credenciales){
		
		Profesional profesional =null;
		try{
			profesional = profesionalService.get(credenciales.getUsername());
			}catch (BusinessException e) {
				return new ResponseEntity<Token>(HttpStatus.UNAUTHORIZED);
			}
		if (!profesional.getPassword().equals(encode.encode(credenciales.getPassword()))) {
			return new ResponseEntity<Token>(HttpStatus.UNAUTHORIZED);
		}
		String key = UUID.randomUUID().toString().toUpperCase() +
		        "&&" + profesional.getUsername()+"&&"+System.currentTimeMillis();
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(ConstantesAutenticator.PASS);
		String encrypt=jasypt.encrypt(key);
		Token t=new Token(encrypt,true);
		return new ResponseEntity<Token>(t,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/registro",method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Token> crearProfesional(@RequestBody Profesional profesional){
		profesional.setPassword(encode.encode(profesional.getPassword()));
		try{
		profesionalService.crear(profesional);
		}catch(BusinessException e){
			return new ResponseEntity<Token>(HttpStatus.BAD_GATEWAY);
		}
		String key = UUID.randomUUID().toString().toUpperCase() +
		        "&&" + profesional.getUsername()+"&&"+System.currentTimeMillis();
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(ConstantesAutenticator.PASS);
		String encrypt=jasypt.encrypt(key);
		Token t=new Token(encrypt,true);
		return new ResponseEntity<Token>(t,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Profesional> get(Principal principal){
		Profesional profesional =null;
		try{
			profesional = profesionalService.get(principal.getName());
			}catch (BusinessException e) {
				return new ResponseEntity<Profesional>(HttpStatus.UNAUTHORIZED);
			}
		return new ResponseEntity<Profesional>(profesional,HttpStatus.OK);
	}
	
	@RequestMapping(value="/topics/{tipo}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sector>> getSectores(@PathVariable String tipo,Principal principal){
		try{
			if(Constantes.TOPIC_SECTOR.equalsIgnoreCase(tipo))
				return new ResponseEntity<List<Sector>>(profesionalService.getSectores(principal.getName()),HttpStatus.OK);
			else if(Constantes.TOPIC_TEMATICA.equalsIgnoreCase(tipo))
				return new ResponseEntity<List<Sector>>(profesionalService.getTematicas(principal.getName()),HttpStatus.OK);
			else
				return new ResponseEntity<List<Sector>>(HttpStatus.BAD_GATEWAY);
			}catch (BusinessException e) {
				return new ResponseEntity<List<Sector>>(HttpStatus.UNAUTHORIZED);
			}
	}
	
	
	@RequestMapping(value="/perfiles",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Perfil>> getPerfiles(Principal principal){
		try{
			return new ResponseEntity<List<Perfil>>(profesionalService.getPerfiles(principal.getName()),HttpStatus.OK);
			}catch (BusinessException e) {
				return new ResponseEntity<List<Perfil>>(HttpStatus.UNAUTHORIZED);
			}
	}
}
