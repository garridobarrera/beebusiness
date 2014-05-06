package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IProfesionalDAO;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IProfesionalService;

@Service
@Transactional
public class ProfesionalServiceImpl implements IProfesionalService {



	private static final long serialVersionUID = 6860687923734597493L;
	@Autowired
	private IProfesionalDAO dao;


	public void setDao(IProfesionalDAO dao) {
		this.dao = dao;
	}

	@Override
	public Profesional crear(Profesional profesional) throws BusinessException {
		try {
			return dao.create(profesional);
		} catch (PersistenceException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	public Profesional actualizar(Profesional profesional) {
		return dao.update(profesional);
	}

	@Override
	public void borrar(Profesional profesional) {
		dao.delete(profesional);
	}

	@Override
	public Profesional get(Long id) {
		return dao.findById(id);
	}

	@Override
	public Profesional get(String username) throws BusinessException {
		try {
			return dao.findByCodigo(username);
		} catch (NoResultException e) {
			throw new BusinessException("No se encuentra el Profesional indicado",
					e);
		}
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<Profesional> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<Profesional> getAll() {
		return dao.getAll(null, null);
	}

	@Override
	public int getTotalFilter(Profesional profesional) {
		return dao.getTotalFilter(profesional);
	}

	@Override
	public List<Profesional> getAllFilter(Profesional profesional, Integer inicio, Integer total) {
		return dao.getAllFilter(profesional, inicio, total);
	}

	@Override
	public List<Perfil> getPerfiles(String username) {
		return dao.getPerfiles(username);
	}

	@Override
	public List<Sector> getSectores(String username) {
		return dao.getSectores(username);
	}
	
	@Override
	public List<Sector> getTematicas(String username) {
		return dao.getTematicas(username);
	}

	


}
