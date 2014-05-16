package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Provincia;
import es.beebusiness.service.IProvinciaService;


public class ProvinciaLazyDataModel extends LazyDataModel<Provincia>{

	private static final long serialVersionUID = -8117874649477106123L;
	
	public ProvinciaLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private transient IProvinciaService service;
	
	
	private int currentPage=1;
	
	
	private List<Provincia> provincias;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IProvinciaService getService() {
		return service;
	}

	@Autowired
	public void setService(IProvinciaService service) {
		this.service = service;
	}
	
	public List<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	
	@Override
	public List<Provincia> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		provincias= service.getAll(first, pageSize);
		    return provincias;
	}
	
	@Override
	public Object getRowKey(Provincia Provincia) {
		return Provincia.getId();
	}
	
	@Override
	public int getRowCount() {
		return service.getSizeAll();
	}
	

	

}
