package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.ISectorService;

@Controller
public class SectorController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3533568629041908260L;
	@Autowired
	private ISectorService sectorService;

	public List<Sector> obtenerSectores() {
		return sectorService.getAll();
	}

	public void altaSector(Sector s) {
		try {
			if (s.getId() != null && !"".equals(s.getId())) {
				sectorService.actualizar(s);
				FacesMessage msg = new FacesMessage("Sector actualizado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				sectorService.crear(s);
				FacesMessage msg = new FacesMessage("Sector creado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(
					"Ha sucedido un error al crear el Sector, puede que el sector ya exista");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public Sector obtenerSector(String id) {
		return sectorService.get(new Long(id));
	}

	public void eliminarSector(String id) {
		Sector s = new Sector();
		s.setId(new Long(id));
		try{
			sectorService.borrar(s);
			FacesMessage msg = new FacesMessage("Sector eliminado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			}catch(DataIntegrityViolationException e){
				FacesMessage msg = new FacesMessage("No se ha podido eliminar el sector, compruebe que no se está usando en otras entidades.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
	}

}
