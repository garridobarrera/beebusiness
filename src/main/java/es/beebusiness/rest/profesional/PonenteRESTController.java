package es.beebusiness.rest.profesional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;

import es.beebusiness.domain.Ponente;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IPonenteService;

@Controller
@RequestMapping("/ponente")
public class PonenteRESTController {

	@Autowired
	private IPonenteService ponenteService;

	
	@RequestMapping(value = "/{id}/imagen", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getImagen(@PathVariable("id") long id,HttpServletResponse response) {
		Ponente ponente = ponenteService.get(id);
		if (ponente != null && ponente.getNombreImagen()!=null) {
			InputStream array = new ByteArrayInputStream(ponente.getImageFile());
			try {
				response.setContentType(ponente.getMimeType());
			    response.setContentLength(array.available());
				IOUtils.copy(array, response.getOutputStream());
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			response.setStatus(HttpStatus.NOT_FOUND.value());
		}
	}
	
	
	@RequestMapping(value = "/{id}/imagen", method = RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public void setImagen(@PathVariable("id") long id, @RequestParam("imagen") MultipartFile imagen,HttpServletResponse response) {
		Ponente ponente = ponenteService.get(id);
		ponente.setNombreImagen(imagen.getOriginalFilename());
		ponente.setMimeType(imagen.getContentType());
		try {
			ponente.setImageFile(imagen.getBytes());
		} catch (IOException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		ponenteService.actualizar(ponente);
		response.setStatus(HttpStatus.OK.value());
	}
	
	

	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Ponente> get(@PathVariable("id") long id) {
		Ponente ponente = null;
		try {
			ponente = ponenteService.get(id);
		} catch (BusinessException e) {
			return new ResponseEntity<Ponente>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Ponente>(ponente, HttpStatus.OK);
	}

	
}
