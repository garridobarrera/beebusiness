package es.beebusiness.dao.impl;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IProgramaDAO;
import es.beebusiness.domain.Programa;

@Repository
public class ProgramaDAOImpl extends AbstractBaseGenericDAOImpl<Programa, Long> implements IProgramaDAO{

}
