package es.beebusiness.rest.profesional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.beebusiness.authenticator.ConstantesAutenticator;
import es.beebusiness.authenticator.Credencial;
import es.beebusiness.authenticator.Encrypter;
import es.beebusiness.authenticator.Token;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IEmailService;
import es.beebusiness.service.IProfesionalService;
import es.beebusiness.util.Constantes;

@Controller
@RequestMapping("/profesional")
public class ProfesionalRESTController {

	@Autowired
	private IProfesionalService profesionalService;
	@Autowired
	private Encrypter encode;
	@Autowired
	private IEmailService emailService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Token> authenticate(
			@RequestBody Credencial credenciales) {

		Profesional profesional = null;
		try {
			profesional = profesionalService.get(credenciales.getUsername());
		} catch (BusinessException e) {
			return new ResponseEntity<Token>(HttpStatus.UNAUTHORIZED);
		}
		if (!profesional.getPassword().equals(
				encode.encode(credenciales.getPassword()))) {
			return new ResponseEntity<Token>(HttpStatus.UNAUTHORIZED);
		}
		String key = UUID.randomUUID().toString().toUpperCase() + "&&"
				+ profesional.getUsername() + "&&" + System.currentTimeMillis();
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(ConstantesAutenticator.PASS);
		String encrypt = jasypt.encrypt(key);
		Token t = new Token(encrypt, true);
		return new ResponseEntity<Token>(t, HttpStatus.OK);

	}

	@RequestMapping(value = "/registro", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Token> crearProfesional(
			@RequestBody Profesional profesional) {
		profesional.setPassword(encode.encode(profesional.getPassword()));
		try {
			profesionalService.crear(profesional);
		} catch (BusinessException e) {
			return new ResponseEntity<Token>(HttpStatus.BAD_GATEWAY);
		}
		String key = UUID.randomUUID().toString().toUpperCase() + "&&"
				+ profesional.getUsername() + "&&" + System.currentTimeMillis();
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(ConstantesAutenticator.PASS);
		String encrypt = jasypt.encrypt(key);
		Token t = new Token(encrypt, true);
		return new ResponseEntity<Token>(t, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/reestablecer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity reestablecerCredenciales(
			@RequestBody Profesional profesional) {
		Profesional prof = null;
		try {
			prof = profesionalService.get(profesional.getUsername());
			String key = UUID.randomUUID().toString().toUpperCase() + "&&"
					+ profesional.getUsername();
			StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
			jasypt.setPassword(ConstantesAutenticator.PASS);
			String encrypt = jasypt.encrypt(key);
			prof.setPassword(encode.encode(encrypt));
			profesionalService.actualizar(prof);
			emailService.enviarCredenciales(profesional.getUsername(), encrypt,
					"");
		} catch (BusinessException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch (AddressException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch (MessagingException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/imagen", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getImagen(Principal principal, HttpServletResponse response) {
		Profesional prof = profesionalService.get(principal.getName());
		if (prof != null && prof.getNombreImagen()!=null) {
			InputStream array = new ByteArrayInputStream(prof.getImageFile());
			try {
				IOUtils.copy(array, response.getOutputStream());
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}
	
	
	@RequestMapping(value = "/imagen", method = RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public void setImagen(Principal principal, @RequestParam("imagen") MultipartFile imagen) {
		Profesional prof = profesionalService.get(principal.getName());
		prof.setNombreImagen(imagen.getName());
		prof.setMimeType(imagen.getContentType());
		try {
			prof.setImageFile(imagen.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		profesionalService.actualizar(prof);
	}
	
	

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Profesional> get(Principal principal) {
		Profesional profesional = null;
		try {
			profesional = profesionalService.get(principal.getName());
		} catch (BusinessException e) {
			return new ResponseEntity<Profesional>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Profesional>(profesional, HttpStatus.OK);
	}

	@RequestMapping(value = "/topics/{tipo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Sector>> getSectores(@PathVariable String tipo,
			Principal principal) {
		try {
			if (Constantes.TOPIC_SECTOR.equalsIgnoreCase(tipo))
				return new ResponseEntity<List<Sector>>(
						profesionalService.getSectores(principal.getName()),
						HttpStatus.OK);
			else if (Constantes.TOPIC_TEMATICA.equalsIgnoreCase(tipo))
				return new ResponseEntity<List<Sector>>(
						profesionalService.getTematicas(principal.getName()),
						HttpStatus.OK);
			else
				return new ResponseEntity<List<Sector>>(HttpStatus.BAD_GATEWAY);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Sector>>(HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/perfiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Perfil>> getPerfiles(Principal principal) {
		try {
			return new ResponseEntity<List<Perfil>>(
					profesionalService.getPerfiles(principal.getName()),
					HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Perfil>>(HttpStatus.UNAUTHORIZED);
		}
	}
}
