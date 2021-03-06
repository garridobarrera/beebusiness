package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Sector;
import es.beebusiness.service.ISectorService;


public class SectorLazyDataModel extends LazyDataModel<Sector>{

	
	
	private static final long serialVersionUID = -6093577599793669469L;
	private int total=Integer.MIN_VALUE;
	private String busqueda;

	public SectorLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private transient ISectorService service;
	
	
	private int currentPage=1;
	
	
	private List<Sector> sectores;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public ISectorService getService() {
		return service;
	}

	@Autowired
	public void setService(ISectorService service) {
		this.service = service;
	}
	
	public List<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}

	
	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	@Override
	public List<Sector> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
			total=Integer.MIN_VALUE;
		    sectores= service.getAllFilter(first, pageSize,busqueda);
		    return sectores;
	}
	
	@Override
	public Object getRowKey(Sector sector) {
		return sector.getId();
	}
	
	@Override
	public int getRowCount() {
		if(total==Integer.MIN_VALUE)
			total=service.getsize(busqueda);
		return total;
	}
	

	

}
