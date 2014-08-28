package es.beebusiness.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.beans.EventoBean;
import es.beebusiness.beans.ProfesionalBean;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Evento;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IEventoService;

@Controller
public class EventoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3495276798718330402L;
	@Autowired
	private IEventoService eventoService;


	public List<Evento> obtenerEventos() {
		return eventoService.getAll();
	}

	public boolean altaEvento(Evento evento) {
		try {
			if (evento.getId() != null && !"".equals(evento.getId())) {
				eventoService.actualizar(evento);
				FacesMessage msg = new FacesMessage(
						"Evento actualizado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				eventoService.crear(evento);
				FacesMessage msg = new FacesMessage(
						"Evento creado correctamente");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			return true;

		} catch (BusinessException e) {
			FacesMessage msg = new FacesMessage(
					"Ha sucedido un error al crear el evento, puede que el evento ya exista");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}

	}

	
	
	

	public void eliminarEvento(String id) {
		Evento evento = new Evento();
		evento.setId(new Long(id));
		eventoService.borrar(evento);
		FacesMessage msg = new FacesMessage(
				"Evento eliminado correctamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Evento obtenerEvento(String id) {
		return eventoService.get(new Long(id));
	}
	
	
	public EventoBean componerEvento(EventoBean eventoBean){
		eventoBean.setEmpresasSeleccionadas(eventoService.getEmpresas(eventoBean.getEvento().getId()));
		eventoBean.setPerfilesSeleccionados(eventoService.getPerfiles(eventoBean.getEvento().getId()));
		eventoBean.setSectoresSeleccionados(eventoService.getSectores(eventoBean.getEvento().getId()));
		eventoBean.setTematicasSeleccionadas(eventoService.getTematicas(eventoBean.getEvento().getId()));
		return eventoBean;
	}


}
