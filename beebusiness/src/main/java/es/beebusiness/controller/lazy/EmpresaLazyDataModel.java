package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Empresa;
import es.beebusiness.service.IEmpresaService;


public class EmpresaLazyDataModel extends LazyDataModel<Empresa>{

	private static final long serialVersionUID = -8117874649477106123L;
	
	public EmpresaLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private transient IEmpresaService service;
	
	
	private int currentPage=1;
	
	
	private List<Empresa> empresas;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IEmpresaService getService() {
		return service;
	}

	@Autowired
	public void setService(IEmpresaService service) {
		this.service = service;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	
	@Override
	public List<Empresa> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		    empresas= service.getAll(first, pageSize);
		    return empresas;
	}
	
	@Override
	public Object getRowKey(Empresa empresa) {
		return empresa.getId();
	}
	
	@Override
	public int getRowCount() {
		return service.getSizeAll();
	}
	

	

}
