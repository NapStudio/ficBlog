package asi.ficblog.model.blogservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;
import asi.ficblog.model.util.validator.EntityValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private EnlaceDAO enlaceDAO;
	@Autowired
	private ArticuloDAO articuloDAO;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Calendar date;
	
	public Blog crearBlog(Usuario usuario_blog, String nombre_blog)
			throws InputValidationException {

		date = Calendar.getInstance();
		System.out.println("date en crearBlogservice: " + date);
		Blog blog = new Blog(nombre_blog, date, usuario_blog.getLogin_usuario());
		EntityValidator.validarBlog(blog);
		return blogDAO.insert(blog);

	}

	public Blog crearBlog(Blog blog) throws InputValidationException {
		EntityValidator.validarBlog(blog);
		blog.setFecha_creacion_blog(Calendar.getInstance());
		return blogDAO.insert(blog);
	}

	@Transactional(readOnly = true)
	public Blog buscarBlog(Long id_blog) throws InstanceNotFoundException {
		return blogDAO.find(id_blog);
	}

	public void eliminarBlog(Long id_blog) throws InstanceNotFoundException {
		blogDAO.remove(id_blog);

	}

	public void cambiarTituloBlog(Blog blog, String nuevo_nombre)
			throws InputValidationException, InstanceNotFoundException {
		blog.setTitulo_blog(nuevo_nombre);
		EntityValidator.validarBlog(blog);
		blogDAO.update(blog);
	}

	public Articulo crearArticulo(String titulo_articulo,
			String texto_articulo, Long id_blog)
			throws InputValidationException {
		date = Calendar.getInstance();
		Articulo articulo = new Articulo(titulo_articulo, date, texto_articulo,
				id_blog);
		System.out.println(date);
		EntityValidator.validarArticulo(articulo);
		return articuloDAO.insert(articulo);
	}

	public Articulo crearArticulo(Articulo articulo)
			throws InputValidationException {
		EntityValidator.validarArticulo(articulo);
		date = Calendar.getInstance();
		articulo.setFecha_publicacion_entrada(date);
		System.out.println("crear articulo: " + articulo);
		return articuloDAO.insert(articulo);
	}

	public Enlace crearEnlace(String titulo_enlace, String url_enlace,
			String tipo_contenido, Long id_blog)
			throws InputValidationException {
		date = Calendar.getInstance();
		Enlace enlace = new Enlace(titulo_enlace, date, url_enlace,
				tipo_contenido, id_blog);
		return enlaceDAO.insert(enlace);
	}

	public Enlace crearEnlace(Enlace enlace) throws InputValidationException,
			InputValidationException {
		EntityValidator.validarEnlace(enlace);
		date = Calendar.getInstance();
		enlace.setFecha_publicacion_entrada(date);
		return enlaceDAO.insert(enlace);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void modificarArticulo(String nuevo_titulo_articulo,
			String nuevo_texto_articulo, Long id_articulo)
			throws InstanceNotFoundException, InputValidationException {
		Articulo articulo = articuloDAO.find(id_articulo);
		articulo.setTitulo_entrada(nuevo_titulo_articulo);
		articulo.setTexto_articulo(nuevo_texto_articulo);
		EntityValidator.validarArticulo(articulo);
		articuloDAO.update(articulo);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void modificarArticulo(Articulo articulo)
			throws InstanceNotFoundException, InputValidationException {
		EntityValidator.validarArticulo(articulo);
		articuloDAO.update(articulo);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void modificarEnlace(String titulo_enlace, String url_enlace,
			String tipo_contenido, Long id_enlace)
			throws InstanceNotFoundException, InputValidationException {
		Enlace enlace = enlaceDAO.find(id_enlace);
		enlace.setTitulo_entrada(titulo_enlace);
		enlace.setUrl_enlace(url_enlace);
		enlace.setTipo_contenido_enlace(tipo_contenido);
		EntityValidator.validarEnlace(enlace);
		enlaceDAO.update(enlace);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void modificarEnlace(Enlace enlace)
			throws InstanceNotFoundException, InputValidationException {
		EntityValidator.validarEnlace(enlace);
		enlaceDAO.update(enlace);
	}

	@Transactional(readOnly = true)
	public Articulo buscarArticulo(Long id_articulo)
			throws InstanceNotFoundException {
		return articuloDAO.find(id_articulo);
	}

	@Transactional(readOnly = true)
	public Enlace buscarEnlace(Long id_enlace) throws InstanceNotFoundException {
		return enlaceDAO.find(id_enlace);
	}

	@Transactional(readOnly = true)
	public List<Entrada> buscarEntradas(Long id_blog)
			throws InstanceNotFoundException {
		List<Entrada> lista_entradas = new ArrayList<Entrada>();
		List<Articulo> lista_articulos = new ArrayList<Articulo>();
		List<Enlace> lista_enlaces = new ArrayList<Enlace>();

		lista_articulos = articuloDAO.findByBlog(id_blog);
		if (!lista_articulos.isEmpty()) {
			lista_entradas.addAll(lista_articulos);
		}
		lista_enlaces = enlaceDAO.findByBlog(id_blog);
		if (!lista_enlaces.isEmpty()) {
			lista_entradas.addAll(lista_enlaces);
		}
		if (!lista_entradas.isEmpty()) {
			System.out.println(lista_entradas);
			Collections.sort(lista_entradas);
		}

		return lista_entradas;
	}

	public void eliminarArticulo(Long id_articulo)
			throws InstanceNotFoundException {
		articuloDAO.remove(id_articulo);

	}

	public void eliminarEnlace(Long id_enlace) throws InstanceNotFoundException {
		enlaceDAO.remove(id_enlace);

	}

	public void eliminarEntradasBlog(Long id_blog)
			throws InstanceNotFoundException {
		articuloDAO.removeAll(id_blog);
		enlaceDAO.removeAll(id_blog);
	}

	@Transactional(readOnly = true)
	public List<Blog> buscarTodosBlogs() {
		List<Blog> blogs = blogDAO.getAll();
		Collections.sort(blogs);
		return blogs;
	}

	public void eliminarTodosBlogs() throws InstanceNotFoundException {
		List<Blog> blogs = buscarTodosBlogs();
		for (Blog blog : blogs) {
			eliminarEntradasBlog(blog.getId_blog());
			eliminarBlog(blog.getId_blog());
		}
	}

	public void meGustaArticulo(Long id_articulo, String login_usuario)
			throws InstanceNotFoundException {
		if (!articuloDAO.findUsuarioGustaArticulo(id_articulo, login_usuario)) {
			articuloDAO.meGustaArticulo(id_articulo, login_usuario);
		}

	}

	public void meGustaEnlace(Long id_enlace, String login_usuario)
			throws InstanceNotFoundException {
		if (!articuloDAO.findUsuarioGustaArticulo(id_enlace, login_usuario)) {
			enlaceDAO.meGustaEnlace(id_enlace, login_usuario);
		}

	}

	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	public EnlaceDAO getEnlaceDAO() {
		return enlaceDAO;
	}

	public void setEnlaceDAO(EnlaceDAO enlaceDAO) {
		this.enlaceDAO = enlaceDAO;
	}

	public ArticuloDAO getArticuloDAO() {
		return articuloDAO;
	}

	public void setArticuloDAO(ArticuloDAO articuloDAO) {
		this.articuloDAO = articuloDAO;
	}

}
