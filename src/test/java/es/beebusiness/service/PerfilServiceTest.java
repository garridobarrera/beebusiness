package es.beebusiness.service;



import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
	
	private List<Perfil> perfiles=new ArrayList<Perfil>();
	
	@Before
	public void setup(){
		for(int i=0;i<100;i++){
		Perfil perfil=new Perfil();
		perfil.setNombre("CONSULTOR "+i);
		perfil=service.crear(perfil);
		perfiles.add(perfil);
		}
	}
	
	@Test
	public void actualizarPerfil(){
		Perfil perfil=perfiles.get(0);
		perfil.setNombre("PRESENTADOR");
		perfil=service.actualizar(perfil);
		Assert.assertEquals("PRESENTADOR", perfil.getNombre());
		
	}
	
	@Test
	public void consultarPerfilPorCodigo(){
		Perfil perfil=service.get("CONSULTOR 2");
		Assert.assertEquals("CONSULTOR 2", perfil.getNombre());
		
	}
	
	@Test
	public void consultarTotal(){
		Assert.assertEquals(100, service.getSizeAll());
	}
	
	@Test
	public void getAll(){
		List<Perfil> perfiles=service.getAll();
		Assert.assertEquals(100, perfiles.size());
	}
	
	@Test
	public void getAllPaginado(){
		int total=service.getSizeAll();
		int pagina=13;
		int inicio=0;
		while(inicio<total){
			List<Perfil> perfiles=service.getAll(inicio, pagina);
			Assert.assertTrue(perfiles.size()>0 && perfiles.size()<=pagina);
			System.out.println("Mostrando desde: "+perfiles.get(0).getNombre() +" a "+perfiles.get(perfiles.size()-1).getNombre());
			inicio+=pagina;
		}
	}
	
	@Test
	public void getAllFilter(){
		Perfil p=new Perfil();
		p.setNombre("CONSULTOR 1");
		Assert.assertEquals(1, service.getTotalFilter(p));
	}
	
	@Test
	public void getAllPaginadoFilter(){
		Perfil p=new Perfil();
		p.setNombre("CONSULTOR 1");
		int total=service.getTotalFilter(p);
		int pagina=13;
		int inicio=0;
		while(inicio<total){
			List<Perfil> perfiles=service.getAllFilter(p,inicio, pagina);
			Assert.assertTrue(perfiles.size()>0 && perfiles.size()<=pagina);
			System.out.println("Mostrando desde: "+perfiles.get(0).getNombre() +" a "+perfiles.get(perfiles.size()-1).getNombre());
			inicio+=pagina;
		}
	}
	
	@After
	public void tearDown(){
		for(int i=0;i<100;i++){
		service.borrar(perfiles.get(i));
		}
	}
}
