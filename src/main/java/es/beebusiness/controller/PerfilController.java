package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import es.beebusiness.domain.Perfil;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IPerfilService;

@Controller
public class PerfilController implements Serializable {

	@Autowired
	private IPerfilService perfilService;
	private static final long serialVersionUID = 841789646715895793L;

	public List<Perfil> obtenerPerfiles() {
		return perfilService.getAll();
	}

	public void altaPerfil(Perfil p){
		try{
		if(p.getId()!=null && !"".equals(p.getId())){
			perfilService.actualizar(p);
			FacesMessage msg = new FacesMessage(
					"perfil actualizado correctamente");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			perfilService.crear(p);
			FacesMessage msg = new FacesMessage("Perfil creado correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el Perfil, puede que el perfil ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}

	public Perfil obtenerPerfil(String id) {
		return perfilService.get(new Long(id));
	}

	public void eliminarPerfil(String id) {
		Perfil p = new Perfil();
		p.setId(new Long(id));
		try{
		perfilService.borrar(p);
		FacesMessage msg = new FacesMessage("Perfil eliminado correctamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		}catch(DataIntegrityViolationException e){
			FacesMessage msg = new FacesMessage("No se ha podido eliminar el perfil, compruebe que no se está usando en otras entidades.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}

}
