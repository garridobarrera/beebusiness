package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.TipoEvento;
import es.beebusiness.service.ITipoEventoService;


public class TipoEventoLazyDataModel extends LazyDataModel<TipoEvento>{


	private static final long serialVersionUID = -8923565766966575206L;

	public TipoEventoLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private ITipoEventoService service;
	
	
	private int currentPage=1;
	
	
	private List<TipoEvento> tipoEventos;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public ITipoEventoService getService() {
		return service;
	}

	@Autowired
	public void setService(ITipoEventoService service) {
		this.service = service;
	}
	
	public List<TipoEvento> getTipoEventos() {
		return tipoEventos;
	}

	public void setTipoEventos(List<TipoEvento> tipoEventos) {
		this.tipoEventos = tipoEventos;
	}

	
	@Override
	public List<TipoEvento> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		    tipoEventos= service.getAll(first, pageSize);
		    return tipoEventos;
	}
	
	@Override
	public Object getRowKey(TipoEvento tev) {
		return tev.getId();
	}
	
	@Override
	public int getRowCount() {
		return service.getSizeAll();
	}
	

	

}
