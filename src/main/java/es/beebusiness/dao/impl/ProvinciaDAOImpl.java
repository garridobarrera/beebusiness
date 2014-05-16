package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IProvinciaDAO;
import es.beebusiness.domain.Provincia;

@Repository
public class ProvinciaDAOImpl extends AbstractBaseGenericDAOImpl<Provincia, Long> implements IProvinciaDAO{

	private static final long serialVersionUID = -125620347155531553L;

	@Override
	public Provincia findByCodigo(String codigo) {
		TypedQuery<Provincia> query=em.createNamedQuery(Provincia.QUERY_GETBYNOMBRE, Provincia.class);
		query.setParameter(1, codigo);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Provincia.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Provincia> getAll(Integer inicio, Integer total) {
		TypedQuery<Provincia> query=em.createNamedQuery(Provincia.QUERY_GETALL, Provincia.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Provincia p) {
		TypedQuery<Long> query=em.createNamedQuery(Provincia.QUERY_GETTOTALBYNOMBRE, Long.class);
		query.setParameter(1, p.getNombre());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Provincia> getAllFilter(Provincia p, Integer inicio, Integer total) {
		TypedQuery<Provincia> query=em.createNamedQuery(Provincia.QUERY_GETBYNOMBRE, Provincia.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, p.getNombre());
		return query.getResultList();
	}
}
