package asi.ficblog.model.blogservice;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;



import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.enlace.Enlace;
import asi.ficblog.model.entrada.Entrada;
import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.usuarioservice.UsuarioService;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-module.xml")
@TransactionConfiguration
@Transactional
public class BlogServiceTest {

	@Autowired
	private BlogService blogService;
	@Autowired
	private UsuarioService usuarioService;
	
	private final long NON_EXISTENT_BLOG_ID = -1;
	private final long NON_EXISTENT_ENTRADA_ID = -1;
	private final String USER_ID = "ws-user";

	private final String VALID_CREDIT_CARD_NUMBER = "1234567890123456";
	private final String INVALID_CREDIT_CARD_NUMBER = "";
	
	public Usuario getValidUsuario() throws InstanceNotFoundException{
		return usuarioService.findUsuarioByLogin("chrisgomez");
		
	}
	
	public Blog getValidBlog() throws InputValidationException, InstanceNotFoundException {
		return blogService.crearBlog(getValidUsuario(), "Test blog"); 
	}

	@Test
	public void testCrearEncontrarBlog() throws InstanceNotFoundException, InputValidationException {
		
		Blog blog = getValidBlog();
		Blog addedBlog = null;

		addedBlog = blogService.crearBlog(blog);
		Blog foundBlog = blogService.buscarBlog(addedBlog.getId_blog());
//		foundBlog=addedBlog;


		assertEquals(addedBlog, foundBlog);

		// Clear Database
		blogService.eliminarBlog(addedBlog.getId_blog());
	}
	
	@Test
	public void testAddInvalidBlog() throws InputValidationException, InstanceNotFoundException {

		Blog blog = getValidBlog();
		Blog addedBlog = null;
		boolean exceptionCatched = false;

		try {
			// Check blog title not null
			blog.setTitulo_blog(null);
			try {
				addedBlog = blogService.crearBlog(blog);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// Check blog title not empty
			exceptionCatched = false;
			blog = getValidBlog();
			blog.setTitulo_blog("");
			try {
				addedBlog = blogService.crearBlog(blog);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// Check movie usuario not null
			exceptionCatched = false;
			blog = getValidBlog();
			blog.setUsuario_blog(null);
			try {
				addedBlog = blogService.crearBlog(blog);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);

			// Check movie description not null
			exceptionCatched = false;
			blog = getValidBlog();
			blog.setUsuario_blog("");
			try {
				addedBlog = blogService.crearBlog(blog);
			} catch (InputValidationException e) {
				exceptionCatched = true;
			}
			assertTrue(exceptionCatched);


		} finally {
			if (!exceptionCatched) {
				// Clear Database
				blogService.eliminarBlog(addedBlog.getId_blog());
			}
		}

	}

	@Test
	public void testBuscarBlog() throws InstanceNotFoundException, InputValidationException {

		Blog blog = null;
		try {
			blog = getValidBlog();
			Assert.assertEquals(blogService.buscarBlog(blog.getId_blog()).getId_blog(), blog.getId_blog());

			// Deletes the product
		} finally {
			blogService.eliminarBlog(blog.getId_blog());
		}
	}
	
	@Test(expected = InstanceNotFoundException.class)
	public void testFindNonExistentMovie() throws InstanceNotFoundException, InputValidationException {
		blogService.buscarBlog(NON_EXISTENT_BLOG_ID);
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testEliminarBlog() throws InstanceNotFoundException, InputValidationException {
		Blog blog = null;
		Usuario usuario = usuarioService.findUsuarioByLogin("chrisgomez");
		blog = blogService.crearBlog(usuario, "Test blog");
		blogService.eliminarBlog(blog.getId_blog());
		Assert.assertNull(blogService.buscarBlog(blog.getId_blog()));

	}

	@Test
	public void testCambiarTituloBlog() throws InstanceNotFoundException, InputValidationException {
		Blog blog = null;
		Usuario usuario = usuarioService.findUsuarioByLogin("chrisgomez");
		blog = blogService.crearBlog(usuario, "Test blog");
		String nuevo_nombre = "El blog";
		System.out.println(blog.getId_blog());
		blogService.cambiarTituloBlog(blog, nuevo_nombre);
		Blog aux = (blogService.buscarBlog(blog.getId_blog()));
		Assert.assertEquals(blog.getTitulo_blog(), aux.getTitulo_blog());

	}
	

	@Test(expected = InputValidationException.class)
	public void testUpdateInvalidMovie() throws InputValidationException,
			InstanceNotFoundException {

		Blog blog = getValidBlog();
		try {
			// Check movie title not null
			blog = blogService.buscarBlog(blog.getId_blog());
			blogService.cambiarTituloBlog(blog, null);
		} finally {
			// Clear Database
			blogService.eliminarBlog(blog.getId_blog());
		}

	}

	@Test
	public void testCrearArticulo() throws InstanceNotFoundException, InputValidationException {
		Blog blog = null;
		Articulo articulo = null;
		try {
			blog = getValidBlog();
			articulo = blogService.crearArticulo("prueba 1", "esta es la prueba", blog.getId_blog());
			Assert.assertEquals(articulo, blogService.buscarArticulo(articulo.getId_articulo()));
		} finally {
			blogService.eliminarArticulo(articulo.getId_articulo());
		}
	}

	@Test
	public void testCrearEnlace() throws InstanceNotFoundException, InputValidationException {
		Blog blog = null;
		Enlace enlace = null;
		try {
			blog = getValidBlog();
			enlace = blogService.crearEnlace("prueba 1", "www.prueba.com", "video", blog.getId_blog());
			Assert.assertEquals(enlace, blogService.buscarEnlace(enlace.getId_enlace()));
		} finally {
			blogService.eliminarEnlace(enlace.getId_enlace());
		}
	}

	@Test
	public void testModificarArticulo() throws InstanceNotFoundException, InputValidationException {
		Articulo articulo = null;
		try {
			Blog blog = getValidBlog();
			articulo = blogService.crearArticulo("prueba 1", "esta es la prueba", blog.getId_blog());
			blogService.modificarArticulo("cambio prueba 1", "esta es la modificacion de la prueba",
					articulo.getId_articulo());
			Articulo aux = (blogService.buscarArticulo(articulo.getId_articulo()));
			Assert.assertEquals("cambio prueba 1", aux.getTitulo_entrada());
			Assert.assertEquals("esta es la modificacion de la prueba", aux.getTexto_articulo());
		} finally {
			blogService.eliminarArticulo(articulo.getId_articulo());
		}
	}

	@Test
	public void testModificarEnlace() throws InstanceNotFoundException, InputValidationException {
		Enlace enlace = null;
		try {
			Blog blog = getValidBlog();
			enlace = blogService.crearEnlace("prueba 1", "www.prueba.com", "video", blog.getId_blog());
			blogService.modificarEnlace("cambio prueba 1", "www.modificacion.com", "foto", enlace.getId_enlace());
			Enlace aux = (blogService.buscarEnlace(enlace.getId_enlace()));
			Assert.assertEquals("cambio prueba 1", aux.getTitulo_entrada());
			Assert.assertEquals("www.modificacion.com", aux.getUrl_enlace());
			Assert.assertEquals("foto", aux.getTipo_contenido_enlace());
		} finally {
			blogService.eliminarEnlace(enlace.getId_enlace());
		}

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testEliminarArticulo() throws InstanceNotFoundException, InputValidationException {
		Blog blog = getValidBlog();
		Articulo articulo = blogService.crearArticulo("prueba 1", "esta es la prueba", blog.getId_blog());
		blogService.eliminarArticulo(articulo.getId_articulo());
		Assert.assertNull(blogService.buscarArticulo(articulo.getId_articulo()));
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testEliminarEnlace() throws InstanceNotFoundException, InputValidationException {
		Blog blog = getValidBlog();
		Enlace enlace = blogService.crearEnlace("prueba 1", "www.prueba.com", "video", blog.getId_blog());
		blogService.eliminarEnlace(enlace.getId_enlace());
		Assert.assertNull(blogService.buscarEnlace(enlace.getId_enlace()));
	}
////TODO: problema con fechas
//	@Test
//	public void testGetEntradas() throws InstanceNotFoundException, InputValidationException {
//		Blog blog1 = getValidBlog();
//		try {
//
//			List<Entrada> lista_entradas = new ArrayList<Entrada>();
//
//			System.out.println(lista_entradas.toString() + "131231");
//
//			Entrada entrada1 = blogService.crearArticulo("prueba1", "prueba prueba", blog1.getId_blog());
//			Entrada entrada2 = blogService.crearEnlace("prueba2", "www.enalce.com", "foto", blog1.getId_blog());
//			Entrada entrada3 = blogService.crearEnlace("prueba3", "www.enalce.com", "foto", blog1.getId_blog());
//			Entrada entrada4 = blogService.crearArticulo("prueba4", "prueba prueba", blog1.getId_blog());
//			Entrada entrada5 = blogService.crearArticulo("prueba5", "prueba prueba", blog1.getId_blog());
//			Entrada entrada6 = blogService.crearEnlace("prueba6", "www.enalce.com", "foto", blog1.getId_blog());
//			Entrada entrada7 = blogService.crearArticulo("prueba7", "prueba prueba", blog1.getId_blog());
//			Entrada entrada8 = blogService.crearEnlace("prueba8", "www.enalce.com", "foto", blog1.getId_blog());
//
//			lista_entradas.add(entrada7);
//			lista_entradas.add(entrada3);
//			lista_entradas.add(entrada2);
//			lista_entradas.add(entrada5);
//			lista_entradas.add(entrada6);
//			lista_entradas.add(entrada4);
//			lista_entradas.add(entrada1);
//			lista_entradas.add(entrada8);
//
//			Collections.sort(lista_entradas);
//			 for (Entrada entrada : lista_entradas) {
//					System.out.println(entrada);
//				}
//
//			List<Entrada> lista_entradas2 = blogService.buscarEntradas(blog1);
//			Assert.assertTrue(lista_entradas.equals(lista_entradas2));
//		} finally {
//			blogService.eliminarEntradasBlog(blog1.getId_blog());
//		}
//
//	}

	@Test
	public void testGetBlogs() throws InstanceNotFoundException, InputValidationException {
		Blog blog1 = null;
		Blog blog2 = null;
		Blog blog3 = null;
		Blog blog4 = null;
		Blog blog5 = null;
		try {
			Usuario usuario1 = usuarioService.findUsuarioByLogin("chrisgomez");

			Usuario usuario2 = usuarioService.findUsuarioByLogin("SmithClerk");
			Usuario usuario3 = usuarioService.findUsuarioByLogin("ALLENSALESMAN");

			blog1 = blogService.crearBlog(usuario1, "blog1");
			blog2 = blogService.crearBlog(usuario2, "blog2");
			blog3 = blogService.crearBlog(usuario3, "blog3");
			blog4 = blogService.crearBlog(usuario1, "blog4");
			blog5 = blogService.crearBlog(usuario3, "blog5");

			List<Blog> lista = blogService.buscarTodosBlogs();
			System.out.println(lista.toString());
			Assert.assertTrue(lista.contains(blogService.buscarBlog(blog1.getId_blog())));
			Assert.assertTrue(lista.contains(blog2));
			Assert.assertTrue(lista.contains(blog3));
			Assert.assertTrue(lista.contains(blog4));
			Assert.assertTrue(lista.contains(blog5));
		} finally {
			blogService.eliminarBlog(blog1.getId_blog());
			blogService.eliminarBlog(blog2.getId_blog());
			blogService.eliminarBlog(blog3.getId_blog());
			blogService.eliminarBlog(blog4.getId_blog());
			blogService.eliminarBlog(blog5.getId_blog());
			blogService.eliminarTodosBlogs();
		}

	}

}