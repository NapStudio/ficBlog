package asi.ficblog.model.blogservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.articulo.ArticuloDAO;
import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.blog.BlogDAO;
import asi.ficblog.model.enlace.Enlace;
import asi.ficblog.model.enlace.EnlaceDAO;
import asi.ficblog.model.entrada.Entrada;
import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = false)
public class BlogServiceImplement implements BlogService {

	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private EnlaceDAO enlaceDAO;
	@Autowired
	private ArticuloDAO articuloDAO;

	public Blog crearBlog(Usuario usuario_blog, String nombre_blog) {
		Blog blog = new Blog(nombre_blog, new Date(), usuario_blog.getLogin_usuario());
		return blogDAO.insert(blog);

	}

	public Blog crearBlog(Blog blog) {
		return blogDAO.insert(blog);
	}

	@Transactional(readOnly = true)
	public Blog buscarBlog(int id_blog) throws InstanceNotFoundException {
		return blogDAO.find(id_blog);
	}

	public void eliminarBlog(int id_blog) {
		blogDAO.remove(id_blog);

	}

	public void cambiarTituloBlog(Blog blog, String nuevo_nombre) {
		blog.setTitulo_blog(nuevo_nombre);
		blogDAO.update(blog);
	}

	public Articulo crearArticulo(String titulo_articulo, String texto_articulo, int id_blog) {
		return articuloDAO.insert(new Articulo(titulo_articulo, new Date(), texto_articulo, id_blog));
	}

	public Enlace crearEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, int id_blog) {
		return enlaceDAO.insert(new Enlace(titulo_enlace, new Date(), url_enlace, tipo_contenido, id_blog));
	}

	public void modificarArticulo(String nuevo_titulo_articulo, String nuevo_texto_articulo, int id_articulo)
			throws InstanceNotFoundException {
		Articulo articulo = articuloDAO.find(id_articulo);
		articulo.setTitulo_articulo(nuevo_titulo_articulo);
		articulo.setTexto_articulo(nuevo_texto_articulo);
		articuloDAO.update(articulo);
	}

	public void modificarEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, int id_enlace)
			throws InstanceNotFoundException {
		Enlace enlace = enlaceDAO.find(id_enlace);
		enlace.setTitulo_enlace(titulo_enlace);
		enlace.setUrl_enlace(url_enlace);
		enlace.setTipo_contenido_enlace(tipo_contenido);
		enlaceDAO.update(enlace);
	}

	@Transactional(readOnly = true)
	public Articulo buscarArticulo(int id_articulo) throws InstanceNotFoundException {
		return articuloDAO.find(id_articulo);
	}

	@Transactional(readOnly = true)
	public Enlace buscarEnlace(int id_enlace) throws InstanceNotFoundException {
		return enlaceDAO.find(id_enlace);
	}

	@Transactional(readOnly = true)
	public List<Entrada> buscarEntradas(Blog blog) throws InstanceNotFoundException {
		List<Entrada> lista_entradas = new ArrayList<Entrada>();
		List<Articulo> lista_articulos = new ArrayList<Articulo>();
		List<Enlace> lista_enlaces = new ArrayList<Enlace>();

		lista_articulos = articuloDAO.findByBlog(blog.getId_blog());

		if (!lista_articulos.isEmpty()) {
			lista_entradas.addAll(lista_articulos);

		}
		lista_enlaces = enlaceDAO.findByBlog(blog.getId_blog());
		if (!lista_enlaces.isEmpty()) {
			lista_entradas.addAll(lista_enlaces);

		}
		if (!lista_entradas.isEmpty()) {
			Collections.sort(lista_entradas);
		}

		return lista_entradas;
	}

	public void eliminarArticulo(int id_articulo) {
		articuloDAO.remove(id_articulo);

	}

	public void eliminarEnlace(int id_enlace) {
		enlaceDAO.remove(id_enlace);

	}

	public void eliminarEntradasBlog(int id_blog) {
		articuloDAO.removeAll(id_blog);
		enlaceDAO.removeAll(id_blog);
	}

	@Transactional(readOnly = true)
	public List<Blog> buscarTodosBlogs() {
		return blogDAO.getAll();
	}


	

}
