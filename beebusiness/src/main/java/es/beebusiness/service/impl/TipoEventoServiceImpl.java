package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.ITipoEventoDAO;
import es.beebusiness.domain.TipoEvento;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.ITipoEventoService;

@Service
@Transactional
public class TipoEventoServiceImpl implements ITipoEventoService {

	private static final long serialVersionUID = -6580258167920702997L;
	@Autowired
	private ITipoEventoDAO dao;

	public void setDao(ITipoEventoDAO dao) {
		this.dao = dao;
	}

	@Override
	public TipoEvento crear(TipoEvento tev) throws BusinessException{
		try{
		return dao.create(tev);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public TipoEvento actualizar(TipoEvento tev) {
		return dao.update(tev);
	}

	@Override
	public void borrar(TipoEvento tev) {
		dao.delete(tev);
	}

	@Override
	public TipoEvento get(Long id) {
		return dao.findById(id);
	}

	@Override
	public TipoEvento get(String codigo) {
		return dao.findByCodigo(codigo);
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<TipoEvento> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<TipoEvento> getAll() {
		return dao.getAll(null,null);
	}

	@Override
	public int getTotalFilter(TipoEvento tev) {
		return dao.getTotalFilter(tev);
	}

	@Override
	public List<TipoEvento> getAllFilter(TipoEvento tev, Integer inicio, Integer total) {
		return dao.getAllFilter(tev, inicio, total);
	}

}
