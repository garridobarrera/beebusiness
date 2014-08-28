package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Sector;

public interface IEventoDAO extends IBaseGenericDAO<Evento, Long> {
	public Evento findByNombre(String nombre);
	public int getTotal();
	public int getTotal(String filtro);
	public List<Evento> getAll(Integer inicio,Integer total);
	public List<Evento> getAll(Integer inicio,Integer total,String filtro);
	public int getTotalFilter(Evento evento);
	public List<Evento> getAllFilter(Evento evento,Integer inicio,Integer total);
	public List<Perfil> getPerfiles(Long id);
	public List<Sector> getSectores(Long id);
	public List<Sector> getTematicas(Long id);
	public List<Empresa> getEmpresas(Long id);
}
