package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IPonenteDAO;
import es.beebusiness.domain.Ponente;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IPonenteService;

@Service
@Transactional
public class PonenteServiceImpl implements IPonenteService {

	private static final long serialVersionUID = -6580258167920702997L;
	@Autowired
	private IPonenteDAO dao;

	public void setDao(IPonenteDAO dao) {
		this.dao = dao;
	}

	@Override
	public Ponente crear(Ponente p) throws BusinessException{
		try{
		return dao.create(p);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public Ponente actualizar(Ponente p) {
		return dao.update(p);
	}

	@Override
	public void borrar(Ponente p) {
		try{
		dao.delete(p);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public Ponente get(Long id) {
		return dao.findById(id);
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<Ponente> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<Ponente> getAll() {
		return dao.getAll(null,null);
	}

	@Override
	public int getTotalFilter(Ponente p) {
		return dao.getTotalFilter(p);
	}

	@Override
	public List<Ponente> getAllFilter(Ponente p, Integer inicio, Integer total) {
		return dao.getAllFilter(p, inicio, total);
	}
	
	@Override
	public List<Ponente> getAll(Integer inicio, Integer total, String busqueda) {
		if(busqueda==null || "".equals(busqueda))
			return dao.getAll(inicio, total);
		else
			return dao.getAll(inicio, total, busqueda);
	}

	@Override
	public int getSizeAll(String filtro) {
		if(filtro==null || "".equals(filtro))
			return dao.getTotal();
		else
			return dao.getTotal(filtro);
	}

	@Override
	public List<Ponente> getAllSoloListado(Integer inicio, Integer total,
			String busqueda) {
		return dao.getAllSoloListado(inicio, total, busqueda);
	}

}
