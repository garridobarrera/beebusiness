package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import es.beebusiness.domain.TipoEvento;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.ITipoEventoService;

@Controller
public class TipoEventoController implements Serializable{

	@Autowired
	private ITipoEventoService tipoEventoService;
	private static final long serialVersionUID = 841789646715895793L;
	public List<TipoEvento> obtenerTipoEventos(){
		return tipoEventoService.getAll();
	}
	
	public void altaTipoEvento(TipoEvento tipoEvento){
		try{
		if(tipoEvento.getId()!=null && !"".equals(tipoEvento.getId())){
			tipoEventoService.actualizar(tipoEvento);
			FacesMessage msg = new FacesMessage(
					"Tipo de evento actualizado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			tipoEventoService.crear(tipoEvento);
			FacesMessage msg = new FacesMessage("Tipo de evento creado correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el tipo de evento, puede que ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}

	public TipoEvento obtenerTipoEvento(String id) {
		return tipoEventoService.get(new Long(id));
	}

	public void eliminarTipoEvento(String id) {
		TipoEvento tipo = new TipoEvento();
		tipo.setId(new Long(id));
		try{
			tipoEventoService.borrar(tipo);
		FacesMessage msg = new FacesMessage("Tipo de evento eliminado correctamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch(DataIntegrityViolationException e){
			FacesMessage msg = new FacesMessage("No se ha podido eliminar el tipo de evento, compruebe que no se está usando en otras entidades.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}

}
