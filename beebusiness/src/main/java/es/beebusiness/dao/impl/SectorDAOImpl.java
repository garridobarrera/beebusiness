package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.ISectorDAO;
import es.beebusiness.domain.Sector;

@Repository
public class SectorDAOImpl extends AbstractBaseGenericDAOImpl<Sector, Long> implements ISectorDAO{

	private static final long serialVersionUID = -2258430640831149302L;

	@Override
	public Sector findByCodigo(String codigo) {
		TypedQuery<Sector> query=em.createNamedQuery(Sector.QUERY_GETBYNOMBRE, Sector.class);
		query.setParameter(1, codigo);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Sector.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Sector> getAll(Integer inicio, Integer total) {
		TypedQuery<Sector> query=em.createNamedQuery(Sector.QUERY_GETALL, Sector.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Sector s) {
		TypedQuery<Long> query=em.createNamedQuery(Sector.QUERY_GETTOTALBYNOMBRE, Long.class);
		query.setParameter(1, s.getNombre());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Sector> getAllFilter(Sector s, Integer inicio, Integer total) {
		TypedQuery<Sector> query=em.createNamedQuery(Sector.QUERY_GETBYNOMBRE, Sector.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, s.getNombre());
		return query.getResultList();
	}
}
