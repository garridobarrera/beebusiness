package es.beebusiness.dao.impl;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.ISectorDAO;
import es.beebusiness.domain.Sector;

@Repository
public class SectorDAOImpl extends AbstractBaseGenericDAOImpl<Sector, Long> implements ISectorDAO{

}
