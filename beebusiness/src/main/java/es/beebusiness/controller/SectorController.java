package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.ISectorService;

@Controller
public class SectorController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3533568629041908260L;
	@Autowired
	private ISectorService sectorService;
	
	public List<Sector> obtenerSectores(){
		return sectorService.getAll();
	}
	
	public void altaSector(Sector s){
		try{
			sectorService.crear(s);
		FacesMessage msg = new FacesMessage("Sector creado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el Sector, puede que el sector ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	public void onEdit(RowEditEvent event){
		Sector s=(Sector)event.getObject();
		sectorService.actualizar(s);
		FacesMessage msg = new FacesMessage("Sector actualizado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public void eliminarSector(String id){
		Sector s=new Sector();
		s.setId(new Long(id));
		sectorService.borrar(s);
		FacesMessage msg = new FacesMessage("Sector eliminado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}

}
