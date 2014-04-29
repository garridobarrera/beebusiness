package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Profesional;

public interface IProfesionalDAO extends IBaseGenericDAO<Profesional, Long> {
	public Profesional findByCodigo(String username);
	public int getTotal();
	public List<Profesional> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Profesional profesional);
	public List<Profesional> getAllFilter(Profesional profesional,Integer inicio,Integer total);
}
