package es.beebusiness.dao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.domain.Perfil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/mvc-dispatcher-test-servlet.xml")
public class PerfilDAOTest {

	@Autowired
	private IPerfilDAO perfilDAO;
	private Perfil perfilExistente;



	@Before
	public void setup() {
		perfilExistente=new Perfil();
		perfilExistente.setNombre("CONSULTOR");
		perfilExistente=perfilDAO.create(perfilExistente);
		
	}


	@After
	public void tearDown() {
		perfilDAO.delete(perfilDAO.getReference(perfilExistente.getId()));
	}

	@Test
	public void getPerfilExistente() {
		Perfil perfil=perfilDAO.findById(perfilExistente.getId());
		Assert.assertNotNull(perfil);
		Assert.assertEquals(perfilExistente.getId(), perfil.getId());
		Assert.assertEquals("CONSULTOR", perfil.getNombre());
	}


}
