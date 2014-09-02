package es.beebusiness.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IEventoDAO;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IEventoService;

@Service
@Transactional
public class EventoServiceImpl implements IEventoService {



	private static final long serialVersionUID = 6860687923734597493L;
	@Autowired
	private IEventoDAO dao;


	public void setDao(IEventoDAO dao) {
		this.dao = dao;
	}

	@Override
	public Evento crear(Evento evento) throws BusinessException {
		try {
			return dao.create(evento);
		} catch (PersistenceException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	public Evento actualizar(Evento evento) {
		return dao.update(evento);
	}

	@Override
	public void borrar(Evento evento) {
		dao.delete(evento);
	}

	@Override
	public Evento get(Long id) {
		return dao.findById(id);
	}

	@Override
	public Evento get(String titulo) throws BusinessException {
		try {
			return null;
//			return dao.findByCodigo(titulo);
		} catch (NoResultException e) {
			throw new BusinessException("No se encuentra el Profesional indicado",
					e);
		}
	}

	@Override
	public int getSizeAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSizeAll(String filtro) {
		if(filtro==null || "".equals(filtro))
			return dao.getTotal();
		else
			return dao.getTotal(filtro);
	}

	@Override
	public List<Evento> getAll(Integer inicio, Integer total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evento> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalFilter(Evento evento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Evento> getAllFilter(Evento evento, Integer inicio,
			Integer total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> getPerfiles(Long id) {
		return dao.getPerfiles(id);
	}

	@Override
	public List<Sector> getSectores(Long id) {
		return dao.getSectores(id);
	}

	@Override
	public List<Sector> getTematicas(Long id) {
		return dao.getTematicas(id);
	}

	@Override
	public List<Empresa> getEmpresas(Long id) {
		return dao.getEmpresas(id);
	}

	@Override
	public List<Evento> getAll(Integer inicio, Integer total, String busqueda) {
		if(busqueda==null || "".equals(busqueda))
			return dao.getAll(inicio, total);
		else
			return dao.getAll(inicio, total, busqueda);
	}

	@Override
	public List<Evento> getAll(Integer inicio, Integer total, Date fechaInicio) {
		return dao.getAll(inicio, total, fechaInicio);
	}

	

	


}
