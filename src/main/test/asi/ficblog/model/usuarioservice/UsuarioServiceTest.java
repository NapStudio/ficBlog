package asi.ficblog.model.usuarioservice;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-module.xml")
@TransactionConfiguration
@Transactional
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	


	@Test
	public void testFindbyLogin() throws InstanceNotFoundException, InputValidationException {
		
		Usuario usuario = new Usuario("Alejandro", "Vazquez","avazquez","123456", "alej");
		
		try{// Creates a new product
		usuarioService.registrarUsuario(usuario);
		
		// Retrieves the product
		Usuario aux = usuarioService.findUsuarioByLogin(usuario.getLogin_usuario());
		Assert.assertEquals(usuario,aux);
		
		// Deletes the product
		}finally{
			usuarioService.eliminarUsuario(usuario.getLogin_usuario());
		}
	}
	
	@Test
	public void testModificarUsuario() throws InstanceNotFoundException, InputValidationException {
		
		Usuario usuario = new Usuario("Alejandro", "Vazquez","avazquez","123456", "alej");
		
		try{// Creates a new product
		usuarioService.registrarUsuario(usuario);
		

		Usuario usuario2 = new Usuario("Alejandro", "Lopez","avazquez","123456", "alej");
		usuarioService.modificarUsuario(usuario2);
		Usuario aux=usuarioService.findUsuarioByLogin(usuario.getLogin_usuario());
		
		Assert.assertNotEquals(aux.getApellidos_usuario(), usuario.getApellidos_usuario());;
		
		
		// Deletes the product
		}finally{
			usuarioService.eliminarUsuario(usuario.getLogin_usuario());
		}
	}
	
	@Test(expected=InstanceNotFoundException.class)
	public void testEliminarUsuario() throws InstanceNotFoundException, InputValidationException {
		
		Usuario usuario = new Usuario("Alejandro", "Vazquez","avazquez","123456", "alej");	
		usuarioService.registrarUsuario(usuario);
		usuarioService.eliminarUsuario(usuario.getLogin_usuario());
		Assert.assertNull(usuarioService.findUsuarioByLogin(usuario.getLogin_usuario()));
		
	
		
	}
}