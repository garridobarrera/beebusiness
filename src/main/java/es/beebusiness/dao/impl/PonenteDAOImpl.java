package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import es.beebusiness.dao.IPonenteDAO;
import es.beebusiness.domain.Ponente;

@Repository
public class PonenteDAOImpl extends AbstractBaseGenericDAOImpl<Ponente, Long> implements IPonenteDAO{

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Ponente.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Ponente> getAll(Integer inicio, Integer total) {
		TypedQuery<Ponente> query=em.createNamedQuery(Ponente.QUERY_GETALL, Ponente.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Ponente ponente) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Ponente> getAllFilter(Ponente ponente, Integer inicio,
			Integer total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ponente> getAll(Integer inicio, Integer total, String filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotal(String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);		 
		 Root<Ponente> root=criteriaQuery.from(Ponente.class);
		 criteriaQuery.select(criteriaBuilder.count((Expression)root.get("id")));
		 if(!StringUtils.isEmpty(filtro)){
			 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
			 Predicate predicadoApellidos=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("apellidos")), "%"+filtro.toUpperCase()+"%");
			 Predicate predicadoCargo=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("cargo")), "%"+filtro.toUpperCase()+"%");
			 criteriaQuery.where(criteriaBuilder.or(predicadoNombre,predicadoApellidos,predicadoCargo));
		 }
		 TypedQuery<Long> query = em.createQuery(criteriaQuery);
		 Long tam=query.getSingleResult();
		 return tam.intValue();
	}

	@Override
	public List<Ponente> getAllSoloListado(Integer inicio, Integer total,
			String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Ponente> criteriaQuery = criteriaBuilder.createQuery(Ponente.class);
		 Root<Ponente> root=criteriaQuery.from(Ponente.class);
		 criteriaQuery.multiselect((Expression)root.get("id"),(Expression)root.get("nombre"),(Expression)root.get("apellidos"),(Expression)root.get("cargo"));
		 if(!StringUtils.isEmpty(filtro)){
			 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
			 Predicate predicadoApellidos=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("apellidos")), "%"+filtro.toUpperCase()+"%");
			 Predicate predicadoCargo=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("cargo")), "%"+filtro.toUpperCase()+"%");
			 criteriaQuery.where(criteriaBuilder.or(predicadoNombre,predicadoApellidos,predicadoCargo));
		 }
		 TypedQuery<Ponente> query = em.createQuery(criteriaQuery);
		 return query.getResultList();
	}

}
