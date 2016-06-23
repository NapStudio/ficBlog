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
		
		try{// Creates a new user
		usuarioService.registrarUsuario(usuario);
		
		// Retrieves the user
		Usuario aux = usuarioService.findUsuarioByLogin(usuario.getLogin_usuario());
		Assert.assertEquals(usuario,aux);
		
		// Deletes the user
		}finally{
			usuarioService.eliminarUsuario(usuario.getLogin_usuario());
		}
	}
	
	@Test
	public void testModificarUsuario() throws InstanceNotFoundException, InputValidationException {
		
		Usuario usuario = new Usuario("Alejandro", "Vazquez","avazquez","123456", "alej");
		
		try{// Creates a new product
		usuarioService.registrarUsuario(usuario);
		

		Usuario aux=usuarioService.findUsuarioByLogin(usuario.getLogin_usuario());
		Assert.assertNotEquals(aux.getApellidos_usuario(), "zas");;
		aux.setApellidos_usuario("zas");
		usuarioService.modificarUsuario(aux);
		Usuario aux2 = usuarioService.findUsuarioByLogin(usuario.getLogin_usuario());

		Assert.assertEquals(aux2.getApellidos_usuario(), "zas");;
		
		
		// Deletes the product
		}finally{
//			usuarioService.eliminarUsuario(usuario.getLogin_usuario());
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