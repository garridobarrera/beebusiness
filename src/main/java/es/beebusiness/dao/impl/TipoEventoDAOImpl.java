package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TipoEvento> getAll(Integer inicio, Integer total, String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<TipoEvento> criteriaQuery = criteriaBuilder.createQuery(TipoEvento.class);
		 Root<TipoEvento> root=criteriaQuery.from(TipoEvento.class);
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(predicadoNombre);
		 TypedQuery<TipoEvento> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int getTotal(String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);		 
		 Root<TipoEvento> root=criteriaQuery.from(TipoEvento.class);
		 criteriaQuery.select(criteriaBuilder.count((Expression)root.get("id")));
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(predicadoNombre);
		 TypedQuery<Long> query = em.createQuery(criteriaQuery);
		 Long tam=query.getSingleResult();
		 return tam.intValue();
	}
}
