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
	
	public PerfilLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private IPerfilService service;
	
	
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

	
	@Override
	public List<Perfil> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		    perfiles= service.getAll(first, pageSize);
		    return perfiles;
	}
	
	@Override
	public Object getRowKey(Perfil perfil) {
		return perfil.getId();
	}
	
	@Override
	public int getRowCount() {
		return service.getSizeAll();
	}
	

	

}
