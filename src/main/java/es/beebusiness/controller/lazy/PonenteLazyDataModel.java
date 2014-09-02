package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Ponente;
import es.beebusiness.service.IPonenteService;


public class PonenteLazyDataModel extends LazyDataModel<Ponente>{

	private static final long serialVersionUID = -8117874649477106123L;
	
	private String busqueda;
	
	private int total=Integer.MIN_VALUE;
	
	public PonenteLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private transient IPonenteService service;
	
	
	private int currentPage=1;
	
	
	private List<Ponente> ponentes;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IPonenteService getService() {
		return service;
	}

	@Autowired
	public void setService(IPonenteService service) {
		this.service = service;
	}
	


	
	public List<Ponente> getPonentes() {
		return ponentes;
	}

	public void setPonentes(List<Ponente> ponentes) {
		this.ponentes = ponentes;
	}

	@Override
	public List<Ponente> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
			total=Integer.MIN_VALUE;
			ponentes= service.getAllSoloListado(first, pageSize,busqueda);
			return ponentes;
	}
	
	@Override
	public Object getRowKey(Ponente ponente) {
		return ponente.getId();
	}
	
	@Override
	public int getRowCount() {
		if(total==Integer.MIN_VALUE)
			total=service.getSizeAll(busqueda);
		return total;
	}
	
	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	

	

}
