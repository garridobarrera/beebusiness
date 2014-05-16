package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void altaTipoEvento(TipoEvento tev){
		try{
			tipoEventoService.crear(tev);
		FacesMessage msg = new FacesMessage("Tipo de evento creado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el Tipo de evento, puede que el Tipo de evento ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public void onEdit(RowEditEvent event){
		TipoEvento tev=(TipoEvento)event.getObject();
		tipoEventoService.actualizar(tev);
		FacesMessage msg = new FacesMessage("Tipo de evento actualizado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public void eliminarTipoEvento(String id){
		TipoEvento tev=new TipoEvento();
		tev.setId(new Long(id));
		tipoEventoService.borrar(tev);
		FacesMessage msg = new FacesMessage("Tipo de evento eliminado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}

}
