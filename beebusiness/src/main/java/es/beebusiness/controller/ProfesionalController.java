package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.authenticator.Encrypter;
import es.beebusiness.domain.Profesional;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IProfesionalService;

@Controller
public class ProfesionalController implements Serializable{




	/**
	 * 
	 */
	private static final long serialVersionUID = 3495276798718330402L;
	@Autowired
	private IProfesionalService profesionalService;
	@Autowired
	private Encrypter encode;
	public List<Profesional> obtenerProfesionales(){
		return profesionalService.getAll();
	}
	
	public void altaProfesional(Profesional profesional){
		try{
			profesional.setPassword(encode.encode(profesional.getPassword()));
			if(profesional.getId()!=null && !"".equals(profesional.getId())){
				profesionalService.actualizar(profesional);	
				FacesMessage msg = new FacesMessage("Profesional actualizado correctamente");  
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{	
				profesionalService.crear(profesional);
			FacesMessage msg = new FacesMessage("Profesional actualizado correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		
		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el profesional, puede que el profesional ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	
	public void eliminarProfesional(String id){
		Profesional profesional=new Profesional();
		profesional.setId(new Long(id));
		profesionalService.borrar(profesional);
		FacesMessage msg = new FacesMessage("Profesional eliminado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	

	public Profesional obtenerProfesional(String id){
		return profesionalService.get(new Long(id));
	}

}
