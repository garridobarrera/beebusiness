package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Evento;
import es.beebusiness.service.IEventoService;


public class EventoLazyDataModel extends LazyDataModel<Evento>{

	private String busqueda;
	
	private int total=Integer.MIN_VALUE;
	
	private static final long serialVersionUID = -8117874649477106123L;
	
	public EventoLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private transient IEventoService service;
	
	
	private int currentPage=1;
	
	
	private List<Evento> eventos;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IEventoService getService() {
		return service;
	}

	@Autowired
	public void setService(IEventoService service) {
		this.service = service;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	
	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	@Override
	public List<Evento> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
			total=Integer.MIN_VALUE;
		    eventos= service.getAll(first, pageSize,busqueda);
		    return eventos;
	}
	
	@Override
	public Object getRowKey(Evento evento) {
		return evento.getId();
	}
	
	@Override
	public int getRowCount() {
		if(total==Integer.MIN_VALUE)
			total=service.getSizeAll(busqueda);
		return total;
	}
	

	

}
