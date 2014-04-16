package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.ISectorDAO;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.ISectorService;

@Service
@Transactional
public class SectorServiceImpl implements ISectorService {

	private static final long serialVersionUID = -6580258167920702997L;
	@Autowired
	private ISectorDAO dao;

	public void setDao(ISectorDAO dao) {
		this.dao = dao;
	}

	@Override
	public Sector crear(Sector s) throws BusinessException{
		try{
		return dao.create(s);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public Sector actualizar(Sector s) {
		return dao.update(s);
	}

	@Override
	public void borrar(Sector s) {
		dao.delete(s);
	}

	@Override
	public Sector get(Long id) {
		return dao.findById(id);
	}

	@Override
	public Sector get(String codigo) {
		return dao.findByCodigo(codigo);
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<Sector> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<Sector> getAll() {
		return dao.getAll(null,null);
	}

	@Override
	public int getTotalFilter(Sector s) {
		return dao.getTotalFilter(s);
	}

	@Override
	public List<Sector> getAllFilter(Sector s, Integer inicio, Integer total) {
		return dao.getAllFilter(s, inicio, total);
	}

}
