package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Profesional;
import es.beebusiness.service.IProfesionalService;


public class ProfesionalLazyDataModel extends LazyDataModel<Profesional>{

	private String busqueda;
	
	private int total=Integer.MIN_VALUE;
	
	private static final long serialVersionUID = -8117874649477106123L;
	
	public ProfesionalLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private transient IProfesionalService service;
	
	
	private int currentPage=1;
	
	
	private List<Profesional> profesionales;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IProfesionalService getService() {
		return service;
	}

	@Autowired
	public void setService(IProfesionalService service) {
		this.service = service;
	}
	
	public List<Profesional> getProfesionales() {
		return profesionales;
	}

	public void setProfesionales(List<Profesional> profesionales) {
		this.profesionales = profesionales;
	}

	
	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	@Override
	public List<Profesional> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
			total=Integer.MIN_VALUE;
		    profesionales= service.getAll(first, pageSize,busqueda);
		    return profesionales;
	}
	
	@Override
	public Object getRowKey(Profesional profesional) {
		return profesional.getId();
	}
	
	@Override
	public int getRowCount() {
		if(total==Integer.MIN_VALUE)
			total=service.getSizeAll(busqueda);
		return total;
	}
	

	

}
