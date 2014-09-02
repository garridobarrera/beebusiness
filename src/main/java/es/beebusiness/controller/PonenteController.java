package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.domain.Ponente;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IPonenteService;

@Controller
public class PonenteController implements Serializable{


	private static final long serialVersionUID = -4234317591892014439L;
	@Autowired
	private IPonenteService ponenteService;
	public List<Ponente> obtenerPonentes(){
		return ponenteService.getAll();
	}
	
	public void altaPonente(Ponente ponente){
		try{
			
			if(ponente.getId()!=null && !"".equals(ponente.getId())){
				ponenteService.actualizar(ponente);	
				FacesMessage msg = new FacesMessage("Ponente actualizado correctamente");  
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				ponenteService.crear(ponente);
			FacesMessage msg = new FacesMessage("Ponente actualizado correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		
		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el ponente, puede que el usuario ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	
	public void eliminarPonente(String id){
		Ponente ponente=new Ponente();
		ponente.setId(new Long(id));
		ponenteService.borrar(ponente);
		FacesMessage msg = new FacesMessage("Ponente eliminado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public Ponente obtenerPonente(String id){
		return ponenteService.get(new Long(id));
	}

}
