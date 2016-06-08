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
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;
import asi.ficblog.model.util.validator.PropertyValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDAO blogDAO;
	@Autowired
	private EnlaceDAO enlaceDAO;
	@Autowired
	private ArticuloDAO articuloDAO;
	
	
	
	public void validarBlog(Blog blog) throws InputValidationException{
		PropertyValidator.validateMandatoryString("title", blog.getTitulo_blog());
		PropertyValidator.validateMandatoryString("usuario", blog.getUsuario_blog());
	}
	
	public void validarArticulo(Articulo articulo) throws InputValidationException{
		PropertyValidator.validateMandatoryString("texto", articulo.getTexto_articulo());
		PropertyValidator.validateMandatoryString("articulo", articulo.getTitulo_articulo());
		PropertyValidator.validateInt("idBlog", articulo.getBlog_articulo());
	}
	
	public void validarEnlace(Enlace enlace) throws InputValidationException{
		PropertyValidator.validateMandatoryString("titulo", enlace.getTitulo_enlace());
		PropertyValidator.validateMandatoryString("enlace", enlace.getUrl_enlace());
		PropertyValidator.validateInt("idBlog", enlace.getBlog_enlace());
	}

	public Blog crearBlog(Usuario usuario_blog, String nombre_blog) throws InputValidationException {
		Blog blog = new Blog(nombre_blog, new Date(), usuario_blog.getLogin_usuario());
		validarBlog(blog);
		return blogDAO.insert(blog);

	}

	public Blog crearBlog(Blog blog) throws InputValidationException {
		validarBlog(blog);
		return blogDAO.insert(blog);
	}

	@Transactional(readOnly = true)
	public Blog buscarBlog(int id_blog) throws InstanceNotFoundException {
		return blogDAO.find(id_blog);
	}

	public void eliminarBlog(int id_blog) {
		blogDAO.remove(id_blog);

	}

	public void cambiarTituloBlog(Blog blog, String nuevo_nombre) throws InputValidationException {
		blog.setTitulo_blog(nuevo_nombre);
		validarBlog(blog);
		blogDAO.update(blog);
	}

	public Articulo crearArticulo(String titulo_articulo, String texto_articulo, int id_blog) throws InputValidationException {
		Articulo articulo= new Articulo(titulo_articulo, new Date(), texto_articulo, id_blog);
		validarArticulo(articulo);
		return articuloDAO.insert(articulo);
	}

	public Articulo crearArticulo(Articulo articulo)
			throws InputValidationException {
		validarArticulo(articulo);
		return articuloDAO.insert(articulo);
	}

	public Enlace crearEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, int id_blog) throws InputValidationException {
		Enlace enlace = new Enlace(titulo_enlace, new Date(), url_enlace, tipo_contenido, id_blog);
		validarEnlace(enlace);
		return enlaceDAO.insert(enlace);
	}

	public Enlace crearEnlace(Enlace enlace) throws InputValidationException,
			InputValidationException {
		validarEnlace(enlace);
		return enlaceDAO.insert(enlace);
	}
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void modificarArticulo(String nuevo_titulo_articulo, String nuevo_texto_articulo, int id_articulo)
			throws InstanceNotFoundException, InputValidationException {
		Articulo articulo = articuloDAO.find(id_articulo);
		articulo.setTitulo_articulo(nuevo_titulo_articulo);
		articulo.setTexto_articulo(nuevo_texto_articulo);
		validarArticulo(articulo);
		articuloDAO.update(articulo);
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void modificarArticulo(Articulo articulo)
			throws InstanceNotFoundException, InputValidationException {
		validarArticulo(articulo);
		articuloDAO.update(articulo);		
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void modificarEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, int id_enlace)
			throws InstanceNotFoundException, InputValidationException {
		Enlace enlace = enlaceDAO.find(id_enlace);
		enlace.setTitulo_enlace(titulo_enlace);
		enlace.setUrl_enlace(url_enlace);
		enlace.setTipo_contenido_enlace(tipo_contenido);
		validarEnlace(enlace);
		enlaceDAO.update(enlace);
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void modificarEnlace(Enlace enlace)
			throws InstanceNotFoundException, InputValidationException {
		validarEnlace(enlace);
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

	public void eliminarArticulo(int id_articulo) throws InstanceNotFoundException {
		articuloDAO.remove(id_articulo);

	}

	public void eliminarEnlace(int id_enlace) {
		enlaceDAO.remove(id_enlace);

	}

	public void eliminarEntradasBlog(int id_blog) throws InstanceNotFoundException {
		articuloDAO.removeAll(id_blog);
		enlaceDAO.removeAll(id_blog);
	}

	@Transactional(readOnly = true)
	public List<Blog> buscarTodosBlogs() {
		return blogDAO.getAll();
	}


	

}
