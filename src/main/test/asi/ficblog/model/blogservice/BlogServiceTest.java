package asi.ficblog.model.blogservice;

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

	@Test
	public void testCrearBlog() throws InstanceNotFoundException {

		Blog blog = null;
		try {
			Usuario usuario = usuarioService.findUsuarioByLogin("chrisgomez");
			blog = blogService.crearBlog(usuario, "Test blog");
			Assert.assertEquals("Test blog", blog.getTitulo_blog());

		} finally {
			blogService.eliminarBlog(blog.getId_blog());
		}
	}

	@Test
	public void testBuscarBlog() throws InstanceNotFoundException {

		Blog blog = null;
		try {
			Usuario usuario = usuarioService.findUsuarioByLogin("chrisgomez");
			blog = blogService.crearBlog(usuario, "Test blog");
			Assert.assertEquals(blogService.buscarBlog(blog.getId_blog()).getId_blog(), blog.getId_blog());

			// Deletes the product
		} finally {
			blogService.eliminarBlog(blog.getId_blog());
		}
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testEliminarBlog() throws InstanceNotFoundException {
		Blog blog = null;
		Usuario usuario = usuarioService.findUsuarioByLogin("chrisgomez");
		blog = blogService.crearBlog(usuario, "Test blog");
		blogService.eliminarBlog(blog.getId_blog());
		Assert.assertNull(blogService.buscarBlog(blog.getId_blog()));

	}

	@Test
	public void testCambiarTituloBlog() throws InstanceNotFoundException {
		Blog blog = null;
		Usuario usuario = usuarioService.findUsuarioByLogin("chrisgomez");
		blog = blogService.crearBlog(usuario, "Test blog");
		String nuevo_nombre = "El blog";
		blogService.cambiarTituloBlog(blog, nuevo_nombre);
		Blog aux = (blogService.buscarBlog(blog.getId_blog()));
		Assert.assertEquals(blog.getTitulo_blog(), aux.getTitulo_blog());

	}

	@Test
	public void testCrearArticulo() throws InstanceNotFoundException {
		Blog blog = null;
		Articulo articulo = null;
		try {
			blog = blogService.buscarBlog(7369);
			articulo = blogService.crearArticulo("prueba 1", "esta es la prueba", blog.getId_blog());
			Assert.assertEquals(articulo, blogService.buscarArticulo(articulo.getId_articulo()));
		} finally {
			blogService.eliminarArticulo(articulo.getId_articulo());
		}
	}

	@Test
	public void testCrearEnlace() throws InstanceNotFoundException {
		Blog blog = null;
		Enlace enlace = null;
		try {
			blog = blogService.buscarBlog(7369);
			enlace = blogService.crearEnlace("prueba 1", "www.prueba.com", "video", blog.getId_blog());
			Assert.assertEquals(enlace, blogService.buscarEnlace(enlace.getId_enlace()));
		} finally {
			blogService.eliminarEnlace(enlace.getId_enlace());
		}
	}

	@Test
	public void testModificarArticulo() throws InstanceNotFoundException {
		Articulo articulo = null;
		try {
			Blog blog = blogService.buscarBlog(7369);
			articulo = blogService.crearArticulo("prueba 1", "esta es la prueba", blog.getId_blog());
			blogService.modificarArticulo("cambio prueba 1", "esta es la modificacion de la prueba",
					articulo.getId_articulo());
			Articulo aux = (blogService.buscarArticulo(articulo.getId_articulo()));
			Assert.assertEquals("cambio prueba 1", aux.getTitulo_articulo());
			Assert.assertEquals("esta es la modificacion de la prueba", aux.getTexto_articulo());
		} finally {
			blogService.eliminarArticulo(articulo.getId_articulo());
		}
	}

	@Test
	public void testModificarEnlace() throws InstanceNotFoundException {
		Enlace enlace = null;
		try {
			Blog blog = blogService.buscarBlog(7369);
			enlace = blogService.crearEnlace("prueba 1", "www.prueba.com", "video", blog.getId_blog());
			blogService.modificarEnlace("cambio prueba 1", "www.modificacion.com", "foto", enlace.getId_enlace());
			Enlace aux = (blogService.buscarEnlace(enlace.getId_enlace()));
			Assert.assertEquals("cambio prueba 1", aux.getTitulo_enlace());
			Assert.assertEquals("www.modificacion.com", aux.getUrl_enlace());
			Assert.assertEquals("foto", aux.getTipo_contenido_enlace());
		} finally {
			blogService.eliminarEnlace(enlace.getId_enlace());
		}

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testEliminarArticulo() throws InstanceNotFoundException {
		Blog blog = blogService.buscarBlog(7369);
		Articulo articulo = blogService.crearArticulo("prueba 1", "esta es la prueba", blog.getId_blog());
		blogService.eliminarArticulo(articulo.getId_articulo());
		Assert.assertNull(blogService.buscarArticulo(articulo.getId_articulo()));
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testEliminarEnlace() throws InstanceNotFoundException {
		Blog blog = blogService.buscarBlog(7369);
		Enlace enlace = blogService.crearEnlace("prueba 1", "www.prueba.com", "video", blog.getId_blog());
		blogService.eliminarEnlace(enlace.getId_enlace());
		Assert.assertNull(blogService.buscarEnlace(enlace.getId_enlace()));
	}

	@Test
	public void testGetEntradas() throws InstanceNotFoundException {
		Blog blog1 = blogService.buscarBlog(7566);
		try {

			List<Entrada> lista_entradas = new ArrayList<Entrada>();

			System.out.println(lista_entradas.toString() + "131231");

			Entrada entrada1 = blogService.crearArticulo("prueba1", "prueba prueba", blog1.getId_blog());
			Entrada entrada2 = blogService.crearEnlace("prueba2", "www.enalce.com", "foto", blog1.getId_blog());
			Entrada entrada3 = blogService.crearEnlace("prueba3", "www.enalce.com", "foto", blog1.getId_blog());
			Entrada entrada4 = blogService.crearArticulo("prueba4", "prueba prueba", blog1.getId_blog());
			Entrada entrada5 = blogService.crearArticulo("prueba5", "prueba prueba", blog1.getId_blog());
			Entrada entrada6 = blogService.crearEnlace("prueba6", "www.enalce.com", "foto", blog1.getId_blog());
			Entrada entrada7 = blogService.crearArticulo("prueba7", "prueba prueba", blog1.getId_blog());
			Entrada entrada8 = blogService.crearEnlace("prueba8", "www.enalce.com", "foto", blog1.getId_blog());

			lista_entradas.add(entrada7);
			lista_entradas.add(entrada3);
			lista_entradas.add(entrada2);
			lista_entradas.add(entrada5);
			lista_entradas.add(entrada6);
			lista_entradas.add(entrada4);
			lista_entradas.add(entrada1);
			lista_entradas.add(entrada8);

			Collections.sort(lista_entradas);

			List<Entrada> lista_entradas2 = blogService.buscarEntradas(blog1);
			Assert.assertTrue(lista_entradas.equals(lista_entradas2));
		} finally {
			blogService.eliminarEntradasBlog(blog1.getId_blog());
		}

	}

	@Test
	public void testGetBlogs() throws InstanceNotFoundException {
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
			Assert.assertTrue(lista.contains(blog1));
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
		}

	}

}