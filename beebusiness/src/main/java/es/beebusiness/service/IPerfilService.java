package es.beebusiness.service;

import java.io.Serializable;

import es.beebusiness.dao.IPerfilDAO;
import es.beebusiness.domain.Perfil;

public interface IPerfilService extends Serializable{

	public void setDao(IPerfilDAO dao);
	public Perfil crear(Perfil p);
	public Perfil actualizar(Perfil p);
	public void borrar(Perfil p);
}
