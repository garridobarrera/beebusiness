package es.beebusiness.rest.admin;

import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.authenticator.ConstantesAutenticator;
import es.beebusiness.authenticator.Credencial;
import es.beebusiness.authenticator.Encrypter;
import es.beebusiness.authenticator.Token;
import es.beebusiness.domain.Usuario;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IUsuarioService;

@Controller
@RequestMapping("/admin/authenticate")
public class AdminRESTController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private Encrypter encode;
	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Token> authenticate(@RequestBody Credencial credenciales){
		
		Usuario usuario =null;
		try{
	         usuario = usuarioService.get(credenciales.getUsername());
			}catch (BusinessException e) {
				return new ResponseEntity<Token>(HttpStatus.UNAUTHORIZED);
			}
		if (!usuario.getPassword().equals(encode.encode(credenciales.getPassword()))) {
			return new ResponseEntity<Token>(HttpStatus.UNAUTHORIZED);
		}
		String key = UUID.randomUUID().toString().toUpperCase() +
		        "&&" + usuario.getUsername()+"&&"+System.currentTimeMillis();
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(ConstantesAutenticator.PASS);
		String encrypt=jasypt.encrypt(key);
		Token t=new Token(encrypt,true);
		return new ResponseEntity<Token>(t,HttpStatus.OK);
		
	}
}
