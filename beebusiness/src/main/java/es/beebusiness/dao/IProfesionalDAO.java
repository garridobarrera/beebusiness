package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;

public interface IProfesionalDAO extends IBaseGenericDAO<Profesional, Long> {
	public Profesional findByCodigo(String username);
	public int getTotal();
	public List<Profesional> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Profesional profesional);
	public List<Profesional> getAllFilter(Profesional profesional,Integer inicio,Integer total);
	public List<Perfil> getPerfiles(String username);
	public List<Sector> getSectores(String username);
	public List<Sector> getTematicas(String username);
}
