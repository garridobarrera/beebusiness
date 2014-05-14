package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IPerfilDAO;
import es.beebusiness.domain.Perfil;

@Repository
public class PerfilDAOImpl extends AbstractBaseGenericDAOImpl<Perfil, Long> implements IPerfilDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5583882049911268889L;

	@Override
	public Perfil findByCodigo(String codigo) {
		TypedQuery<Perfil> query=em.createNamedQuery(Perfil.QUERY_GETBYNOMBRE, Perfil.class);
		query.setParameter(1, codigo);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Perfil.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Perfil> getAll(Integer inicio, Integer total) {
		TypedQuery<Perfil> query=em.createNamedQuery(Perfil.QUERY_GETALL, Perfil.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Perfil p) {
		TypedQuery<Long> query=em.createNamedQuery(Perfil.QUERY_GETTOTALBYNOMBRE, Long.class);
		query.setParameter(1, p.getNombre());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Perfil> getAllFilter(Perfil p, Integer inicio, Integer total) {
		TypedQuery<Perfil> query=em.createNamedQuery(Perfil.QUERY_GETBYNOMBRE, Perfil.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, p.getNombre());
		return query.getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Perfil> getAll(Integer inicio, Integer total, String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Perfil> criteriaQuery = criteriaBuilder.createQuery(Perfil.class);
		 Root<Perfil> root=criteriaQuery.from(Perfil.class);
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(predicadoNombre);
		 TypedQuery<Perfil> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int getTotal(String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);		 
		 Root<Perfil> root=criteriaQuery.from(Perfil.class);
		 criteriaQuery.select(criteriaBuilder.count((Expression)root.get("id")));
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(predicadoNombre);
		 TypedQuery<Long> query = em.createQuery(criteriaQuery);
		 Long tam=query.getSingleResult();
		 return tam.intValue();
	}

}
