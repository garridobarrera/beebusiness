package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Sector;
import es.beebusiness.service.ISectorService;
import es.beebusiness.util.Constantes;


public class TopicSectorLazyDataModel extends LazyDataModel<Sector>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6093577599793669469L;

	public TopicSectorLazyDataModel(){
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

	
	@Override
	public List<Sector> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		    sectores= service.getAll(first, pageSize,Constantes.TOPIC_SECTOR);
		    return sectores;
	}
	
	@Override
	public Object getRowKey(Sector sector) {
		return sector.getId();
	}
	
	@Override
	public int getRowCount() {
		return service.getSizeAll(Constantes.TOPIC_SECTOR);
	}
	

	

}
