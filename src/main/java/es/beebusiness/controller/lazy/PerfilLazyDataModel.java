package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Perfil;
import es.beebusiness.service.IPerfilService;


public class PerfilLazyDataModel extends LazyDataModel<Perfil>{

	private static final long serialVersionUID = -8117874649477106123L;
	
	private int total=Integer.MIN_VALUE;
	
	private String busqueda;
	
	public PerfilLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private transient IPerfilService service;
	
	
	private int currentPage=1;
	
	
	private List<Perfil> perfiles;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IPerfilService getService() {
		return service;
	}

	@Autowired
	public void setService(IPerfilService service) {
		this.service = service;
	}
	
	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	
	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	@Override
	public List<Perfil> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
			total=Integer.MIN_VALUE;
		    perfiles= service.getAll(first, pageSize,busqueda);
		    return perfiles;
	}
	
	@Override
	public Object getRowKey(Perfil perfil) {
		return perfil.getId();
	}
	
	@Override
	public int getRowCount() {
		if(total==Integer.MIN_VALUE)
			total=service.getSizeAll(busqueda);
		return total;
	}
	

	

}
