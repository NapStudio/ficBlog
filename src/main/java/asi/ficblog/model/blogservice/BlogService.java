package asi.ficblog.model.blogservice;

import java.util.List;

import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.enlace.Enlace;
import asi.ficblog.model.entrada.Entrada;
import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface BlogService {
	
	public Blog crearBlog(Usuario usuario_blog, String nombre_blog) throws InputValidationException;
	
	public Blog crearBlog(Blog blog) throws InputValidationException;
	
	public Blog buscarBlog(Long id_blog) throws InstanceNotFoundException, InputValidationException;
	
	public void eliminarBlog(Long id_blog) throws InputValidationException;

	public void cambiarTituloBlog(Blog blog, String nuevo_nombre) throws InputValidationException;
	
	public Articulo crearArticulo(String titulo_articulo, String texto_articulo, Long id_blog) throws InputValidationException;
	
	public Articulo crearArticulo(Articulo articulo) throws InputValidationException;
	
	public Enlace crearEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, Long id_blog) throws InputValidationException, InputValidationException;
	
	public Enlace crearEnlace(Enlace enlace) throws InputValidationException, InputValidationException;
	
	public void modificarArticulo(String nuevo_titulo_articulo, String nuevo_texto_articulo, Long id_articulo) throws InstanceNotFoundException, InputValidationException;
	
	public void modificarArticulo(Articulo articulo) throws InstanceNotFoundException, InputValidationException;
	
	public void modificarEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, Long id_enlace) throws InstanceNotFoundException, InputValidationException;
	
	public void modificarEnlace(Enlace enlace) throws InstanceNotFoundException, InputValidationException;
	
	public List<Entrada> buscarEntradas(Blog blog) throws InstanceNotFoundException, InputValidationException;
	
	public Articulo buscarArticulo(Long id_articulo) throws InstanceNotFoundException , InputValidationException;
	
	public Enlace buscarEnlace(Long id_enlace) throws InstanceNotFoundException, InputValidationException ;
	
	public void eliminarArticulo(Long id_articulo) throws InputValidationException, InstanceNotFoundException;
	
	public void eliminarEnlace(Long id_enlace) throws InputValidationException;
	
	public void eliminarEntradasBlog(Long id_blog) throws InputValidationException, InstanceNotFoundException;
	
	public List<Blog> buscarTodosBlogs();
	
	public void eliminarTodosBlogs() throws InstanceNotFoundException;
	
}
