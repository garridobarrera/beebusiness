package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IProfesionalDAO;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;

@Repository
public class ProfesionalDAOImpl extends AbstractBaseGenericDAOImpl<Profesional, Long> implements IProfesionalDAO{

	private static final long serialVersionUID = 5472294309480802077L;

	@Override
	public Profesional findByCodigo(String username) {
		TypedQuery<Profesional> query=em.createNamedQuery(Profesional.QUERY_GETBYUSERNAME, Profesional.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Profesional.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Profesional> getAll(Integer inicio, Integer total) {
		TypedQuery<Profesional> query=em.createNamedQuery(Profesional.QUERY_GETALL, Profesional.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}
	
	@Override
	public List<Profesional> getAllSoloListado(Integer inicio, Integer total) {
		TypedQuery<Profesional> query=em.createNamedQuery(Profesional.QUERY_GETALL_LISTADO, Profesional.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Profesional profesional) {
		TypedQuery<Long> query=em.createNamedQuery(Profesional.QUERY_GETTOTALBYUSERNAME, Long.class);
		query.setParameter(1, profesional.getUsername());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Profesional> getAllFilter(Profesional profesional, Integer inicio, Integer total) {
		TypedQuery<Profesional> query=em.createNamedQuery(Profesional.QUERY_GETBYUSERNAME, Profesional.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, profesional.getUsername());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> getPerfiles(String username) {
		Query query=em.createNamedQuery(Profesional.QUERY_GETPERFILESBYUSERNAME);
		query.setParameter(1, username);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sector> getSectores(String username) {
		Query query=em.createNamedQuery(Profesional.QUERY_GETSECTORESBYUSERNAME);
		query.setParameter(1, username);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sector> getTematicas(String username) {
		Query query=em.createNamedQuery(Profesional.QUERY_GETTEMATICASBYUSERNAME);
		query.setParameter(1, username);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Profesional> getAll(Integer inicio, Integer total, String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Profesional> criteriaQuery = criteriaBuilder.createQuery(Profesional.class);
		 Root<Profesional> root=criteriaQuery.from(Profesional.class);
		 Predicate predicadoUsername=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("username")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoApellidos=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("apellidos")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoEmailPersonal=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("emailPersonal")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoEmailProfesional=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("emailProfesional")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(criteriaBuilder.or(predicadoUsername,predicadoNombre,predicadoApellidos,predicadoEmailPersonal,predicadoEmailProfesional));
		 TypedQuery<Profesional> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Profesional> getAllSoloListado(Integer inicio, Integer total, String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Profesional> criteriaQuery = criteriaBuilder.createQuery(Profesional.class);
		 Root<Profesional> root=criteriaQuery.from(Profesional.class);
		 criteriaQuery.multiselect((Expression)root.get("id"),(Expression)root.get("nombre"),(Expression)root.get("apellidos"),(Expression)root.get("username"));
		 Predicate predicadoUsername=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("username")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoApellidos=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("apellidos")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoEmailPersonal=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("emailPersonal")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoEmailProfesional=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("emailProfesional")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(criteriaBuilder.or(predicadoUsername,predicadoNombre,predicadoApellidos,predicadoEmailPersonal,predicadoEmailProfesional));
		 TypedQuery<Profesional> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int getTotal(String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);		 
		 Root<Profesional> root=criteriaQuery.from(Profesional.class);
		 criteriaQuery.select(criteriaBuilder.count((Expression)root.get("id")));
		 Predicate predicadoUsername=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("username")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoApellidos=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("apellidos")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoEmailPersonal=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("emailPersonal")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoEmailProfesional=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("emailProfesional")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(criteriaBuilder.or(predicadoUsername,predicadoNombre,predicadoApellidos,predicadoEmailPersonal,predicadoEmailProfesional));
		 TypedQuery<Long> query = em.createQuery(criteriaQuery);
		 Long tam=query.getSingleResult();
		 return tam.intValue();
	}
}
