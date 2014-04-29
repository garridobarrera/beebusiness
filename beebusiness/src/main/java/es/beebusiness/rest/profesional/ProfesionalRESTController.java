package es.beebusiness.rest.profesional;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	
	@RequestMapping(value="/detalle",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
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
	
	@RequestMapping(value="/detalle/sectores",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Set<Sector>> getSectores(Principal principal){
		Profesional profesional =null;
		Set<Sector> sectores=null;
		try{
			profesional = profesionalService.get(principal.getName());
			sectores=profesional.getSectores();
			sectores.size();
			}catch (BusinessException e) {
				return new ResponseEntity<Set<Sector>>(HttpStatus.UNAUTHORIZED);
			}
		return new ResponseEntity<Set<Sector>>(sectores,HttpStatus.OK);
	}
	
	@RequestMapping(value="/detalle/perfiles",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Set<Perfil>> getPerfiles(Principal principal){
		Profesional profesional =null;
		Set<Perfil> perfiles=null;
		try{
			profesional = profesionalService.get(principal.getName());
			perfiles=profesional.getPerfiles();
			perfiles.size();
			}catch (BusinessException e) {
				return new ResponseEntity<Set<Perfil>>(HttpStatus.UNAUTHORIZED);
			}
		return new ResponseEntity<Set<Perfil>>(perfiles,HttpStatus.OK);
	}
}
