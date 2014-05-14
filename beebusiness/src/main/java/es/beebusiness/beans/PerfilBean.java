package es.beebusiness.beans;

import java.io.Serializable;

import es.beebusiness.controller.lazy.PerfilLazyDataModel;
import es.beebusiness.domain.Perfil;

public class PerfilBean implements Serializable{

	
	private static final long serialVersionUID = 1088477017475360828L;
	private Perfil perfil;
	private PerfilLazyDataModel lazy;
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public PerfilLazyDataModel getLazy() {
		return lazy;
	}
	public void setLazy(PerfilLazyDataModel lazy) {
		this.lazy = lazy;
	}

}
