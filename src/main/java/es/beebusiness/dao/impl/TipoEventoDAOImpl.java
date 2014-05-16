package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.ITipoEventoDAO;
import es.beebusiness.domain.TipoEvento;

@Repository
public class TipoEventoDAOImpl extends AbstractBaseGenericDAOImpl<TipoEvento, Long> implements ITipoEventoDAO{


	private static final long serialVersionUID = 5450171554350233685L;

	@Override
	public TipoEvento findByCodigo(String codigo) {
		TypedQuery<TipoEvento> query=em.createNamedQuery(TipoEvento.QUERY_GETBYNOMBRE, TipoEvento.class);
		query.setParameter(1, codigo);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(TipoEvento.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<TipoEvento> getAll(Integer inicio, Integer total) {
		TypedQuery<TipoEvento> query=em.createNamedQuery(TipoEvento.QUERY_GETALL, TipoEvento.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(TipoEvento tev) {
		TypedQuery<Long> query=em.createNamedQuery(TipoEvento.QUERY_GETTOTALBYNOMBRE, Long.class);
		query.setParameter(1, tev.getNombre());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<TipoEvento> getAllFilter(TipoEvento tev, Integer inicio, Integer total) {
		TypedQuery<TipoEvento> query=em.createNamedQuery(TipoEvento.QUERY_GETBYNOMBRE, TipoEvento.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, tev.getNombre());
		return query.getResultList();
	}
}
