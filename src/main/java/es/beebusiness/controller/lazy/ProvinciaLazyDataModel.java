package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Provincia;
import es.beebusiness.service.IProvinciaService;

public class ProvinciaLazyDataModel extends LazyDataModel<Provincia> {

	private static final long serialVersionUID = -8117874649477106123L;

	private int total = Integer.MIN_VALUE;

	private String busqueda;

	private transient IProvinciaService service;

	private int currentPage = 1;

	private List<Provincia> provincias;

	public ProvinciaLazyDataModel() {
		super();
		super.setPageSize(5);
	}

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

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	@Override
	public List<Provincia> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		total=Integer.MIN_VALUE;
		provincias = service.getAll(first, pageSize,busqueda);
		return provincias;
	}

	@Override
	public Object getRowKey(Provincia Provincia) {
		return Provincia.getId();
	}

	@Override
	public int getRowCount() {
		if(total==Integer.MIN_VALUE)
			total=service.getSizeAll(busqueda);
		return total;
	}

}
