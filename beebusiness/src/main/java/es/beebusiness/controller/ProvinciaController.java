package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.domain.Provincia;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IProvinciaService;

@Controller
public class ProvinciaController implements Serializable{


	private static final long serialVersionUID = 3833310339862346316L;
	@Autowired
	private IProvinciaService provinciaService;
	public List<Provincia> obtenerProvinciaes(){
		return provinciaService.getAll();
	}
	
	public void altaProvincia(Provincia p){
		try{
		if(p.getId()!=null && !"".equals(p.getId())){
			provinciaService.actualizar(p);
			FacesMessage msg = new FacesMessage("Provincia actualizada correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			provinciaService.crear(p);	
			FacesMessage msg = new FacesMessage("Provincia creado correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el Provincia, puede que el Provincia ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	
	
	public void eliminarProvincia(String id){
		Provincia p=new Provincia();
		p.setId(new Long(id));
		provinciaService.borrar(p);
		FacesMessage msg = new FacesMessage("Provincia eliminado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public Provincia obtenerProvincia(String id){
		Provincia p=new Provincia();
		p.setId(new Long(id));
		return provinciaService.get(new Long(id));
	}
	
	public List<Provincia> obtenerProvincias(){
		return provinciaService.getAll();
	}

}
