package es.beebusiness.service;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.beebusiness.domain.Perfil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/mvc-dispatcher-test-servlet.xml")
public class PerfilServiceTest {

	@Autowired
	private IPerfilService service;
	
	@Test
	public void crearPerfil(){
		Perfil p=new Perfil();
		p.setNombre("CONSULTOR");
		p=service.crear(p);
		Assert.assertEquals("CONSULTOR", p.getNombre());
		p.setNombre("PRESENTADOR");
		p=service.actualizar(p);
		Assert.assertEquals("PRESENTADOR", p.getNombre());
		
	}
}
