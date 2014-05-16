package es.beebusiness.beans;

import java.io.Serializable;

import es.beebusiness.controller.lazy.TipoEventoLazyDataModel;
import es.beebusiness.domain.TipoEvento;

public class TipoEventoBean implements Serializable{

	private static final long serialVersionUID = 7754409335130644864L;
	private TipoEvento tipoEvento;
	private TipoEventoLazyDataModel lazy;
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public TipoEventoLazyDataModel getLazy() {
		return lazy;
	}
	public void setLazy(TipoEventoLazyDataModel lazy) {
		this.lazy = lazy;
	}
	

}
