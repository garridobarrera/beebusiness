package es.beebusiness.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.authenticator.Encrypter;
import es.beebusiness.beans.ProfesionalBean;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Profesional;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IProfesionalService;

@Controller
public class ProfesionalController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3495276798718330402L;
	@Autowired
	private IProfesionalService profesionalService;
	@Autowired
	private Encrypter encode;

	public List<Profesional> obtenerProfesionales() {
		return profesionalService.getAll();
	}

	public boolean altaProfesional(Profesional profesional) {
		try {
			profesional.setPassword(encode.encode(profesional.getPassword()));
			if (profesional.getId() != null && !"".equals(profesional.getId())) {
				profesionalService.actualizar(profesional);
				FacesMessage msg = new FacesMessage(
						"Profesional actualizado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				profesionalService.crear(profesional);
				FacesMessage msg = new FacesMessage(
						"Profesional creado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			return true;

		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(
					"Ha sucedido un error al crear el profesional, puede que el profesional ya exista");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}

	}

	public ProfesionalBean componerProfesional(ProfesionalBean profesionalBean){
		profesionalBean.setProvinciaSeleccionada(profesionalBean.getProfesional().getProvincia());
		if(profesionalBean.getProfesional().getEmpresa()!=null){
			profesionalBean.setEmpresasSeleccionadas(new ArrayList<Empresa>());
			profesionalBean.getEmpresasSeleccionadas().add(profesionalBean.getProfesional().getEmpresa());
		}
		profesionalBean.setPerfilesSeleccionados(profesionalService.getPerfiles(profesionalBean.getProfesional().getUsername()));
		profesionalBean.setSectoresSeleccionados(profesionalService.getSectores(profesionalBean.getProfesional().getUsername()));
		profesionalBean.setTematicasSeleccionadas(profesionalService.getTematicas(profesionalBean.getProfesional().getUsername()));
		return profesionalBean;
	}
	
	public boolean actualizarProfesional(Profesional profesional) {
		try {
			if (profesional.getId() != null && !"".equals(profesional.getId())) {
				profesionalService.actualizar(profesional);
				FacesMessage msg = new FacesMessage(
						"Profesional actualizado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				profesionalService.crear(profesional);
				FacesMessage msg = new FacesMessage(
						"Profesional creado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			return true;

		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(
					"Ha sucedido un error al crear el profesional, puede que el profesional ya exista");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}

	}
	
	public boolean actualizarCredenciales(Profesional profesional) {
		try {
			profesional.setPassword(encode.encode(profesional.getPassword()));
			if (profesional.getId() != null && !"".equals(profesional.getId())) {
				profesionalService.actualizar(profesional);
				FacesMessage msg = new FacesMessage(
						"Profesional actualizado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				profesionalService.crear(profesional);
				FacesMessage msg = new FacesMessage(
						"Profesional creado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			return true;

		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(
					"Ha sucedido un error al crear el profesional, puede que el profesional ya exista");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}

	}

	public void eliminarProfesional(String id) {
		Profesional profesional = new Profesional();
		profesional.setId(new Long(id));
		profesionalService.borrar(profesional);
		FacesMessage msg = new FacesMessage(
				"Profesional eliminado correctamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Profesional obtenerProfesional(String id) {
		return profesionalService.get(new Long(id));
	}


	public boolean validacionPaso1(Profesional profesional) {
		if (profesional.getNombre() == null
				|| "".equals(profesional.getNombre())) {
			FacesMessage msg = new FacesMessage(
					"Debe indicar un nombre de profesional");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance()
					.addMessage("registro:nombre", msg);
			FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("registro:nombre").getAttributes()
					.get("class");
			UIComponent comp = FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("registro:nombre");
			Map<String, Object> attrMap = comp.getAttributes();
			String className = (String) attrMap.get("styleClass");
			if (!className.contains("ui-state-error"))
				attrMap.put("styleClass", className + " ui-state-error");
			return false;
		} else {
			return true;
		}
	}
	
	

}
