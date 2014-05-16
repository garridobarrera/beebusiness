package es.beebusiness.beans;

import java.io.Serializable;

import es.beebusiness.controller.lazy.SectorLazyDataModel;
import es.beebusiness.domain.Sector;

public class SectorBean implements Serializable{


	private static final long serialVersionUID = 4667161965898003448L;
	private Sector sector;
	private SectorLazyDataModel lazy;
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public SectorLazyDataModel getLazy() {
		return lazy;
	}
	public void setLazy(SectorLazyDataModel lazy) {
		this.lazy = lazy;
	}
	

}
