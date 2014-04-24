package es.beebusiness.beans;

import java.io.Serializable;

import es.beebusiness.controller.lazy.UsuarioLazyDataModel;
import es.beebusiness.domain.Usuario;

public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2425686197129350247L;
	private Usuario usuario;
	private UsuarioLazyDataModel lazy;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public UsuarioLazyDataModel getLazy() {
		return lazy;
	}
	public void setLazy(UsuarioLazyDataModel lazy) {
		this.lazy = lazy;
	}


}
