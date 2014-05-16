package es.beebusiness.beans;

import java.io.Serializable;

import es.beebusiness.controller.lazy.ProvinciaLazyDataModel;
import es.beebusiness.domain.Provincia;

public class ProvinciaBean implements Serializable{

	private static final long serialVersionUID = 1319202809959941435L;
	private Provincia provincia;
	private ProvinciaLazyDataModel lazy;
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public ProvinciaLazyDataModel getLazy() {
		return lazy;
	}
	public void setLazy(ProvinciaLazyDataModel lazy) {
		this.lazy = lazy;
	}
	

}
