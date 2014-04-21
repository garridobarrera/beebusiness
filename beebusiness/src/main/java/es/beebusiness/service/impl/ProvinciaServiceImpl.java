package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IProvinciaDAO;
import es.beebusiness.domain.Provincia;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IProvinciaService;

@Service
@Transactional
public class ProvinciaServiceImpl implements IProvinciaService {

	private static final long serialVersionUID = -6580258167920702997L;
	@Autowired
	private IProvinciaDAO dao;

	public void setDao(IProvinciaDAO dao) {
		this.dao = dao;
	}

	@Override
	public Provincia crear(Provincia p) throws BusinessException{
		try{
		return dao.create(p);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public Provincia actualizar(Provincia p) {
		return dao.update(p);
	}

	@Override
	public void borrar(Provincia p) {
		dao.delete(p);
	}

	@Override
	public Provincia get(Long id) {
		return dao.findById(id);
	}

	@Override
	public Provincia get(String codigo) {
		return dao.findByCodigo(codigo);
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<Provincia> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<Provincia> getAll() {
		return dao.getAll(null,null);
	}

	@Override
	public int getTotalFilter(Provincia p) {
		return dao.getTotalFilter(p);
	}

	@Override
	public List<Provincia> getAllFilter(Provincia p, Integer inicio, Integer total) {
		return dao.getAllFilter(p, inicio, total);
	}

}
