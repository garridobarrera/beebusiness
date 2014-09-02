package es.beebusiness.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.beebusiness.dao.IEventoDAO;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;

public interface IEventoService extends Serializable{

	public void setDao(IEventoDAO dao);
	public Evento crear(Evento evento) throws BusinessException;
	public Evento actualizar(Evento evento);
	public void borrar(Evento evento);
	public Evento get(Long id);
	public Evento get(String titulo) throws BusinessException;
	public int getSizeAll();
	public int getSizeAll(String filtro);
	public List<Evento> getAll(Integer inicio,Integer total);
	public List<Evento> getAll();
	public int getTotalFilter(Evento evento);
	public List<Evento> getAllFilter(Evento evento, Integer inicio, Integer total);
	public List<Perfil> getPerfiles(Long id);
	public List<Sector> getSectores(Long id);
	public List<Sector> getTematicas(Long id);
	public List<Empresa> getEmpresas(Long id);
	public List<Evento> getAll(Integer inicio, Integer total, String busqueda);
	
	public List<Evento> getAll(Integer inicio,Integer total,Date fechaInicio);
}
