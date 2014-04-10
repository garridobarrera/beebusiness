package es.beebusiness.dao.impl;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IProfesionalDAO;
import es.beebusiness.domain.Profesional;

@Repository
public class ProfesionalDAOImpl extends AbstractBaseGenericDAOImpl<Profesional, Long> implements IProfesionalDAO{

}
