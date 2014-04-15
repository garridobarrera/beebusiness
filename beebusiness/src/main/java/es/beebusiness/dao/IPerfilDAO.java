package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Perfil;

public interface IPerfilDAO extends IBaseGenericDAO<Perfil, Long> {
	public Perfil findByCodigo(String codigo);
	public int getTotal();
	public List<Perfil> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Perfil p);
	public List<Perfil> getAllFilter(Perfil p,Integer inicio,Integer total);
}
