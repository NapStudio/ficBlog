package asi.ficblog.model.blogservice;

import java.util.List;

import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.enlace.Enlace;
import asi.ficblog.model.entrada.Entrada;
import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface BlogService {
	
	public Blog crearBlog(Usuario usuario_blog, String nombre_blog);
	
	public Blog crearBlog(Blog blog);
	
	public Blog buscarBlog(int id_blog) throws InstanceNotFoundException ;
	
	public void eliminarBlog(int id_blog);

	public void cambiarTituloBlog(Blog blog, String nuevo_nombre);
	
	public Articulo crearArticulo(String titulo_articulo, String texto_articulo, int id_blog);
	
	public Enlace crearEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, int id_blog);
	
	public void modificarArticulo(String nuevo_titulo_articulo, String nuevo_texto_articulo, int id_articulo) throws InstanceNotFoundException;
	
	public void modificarEnlace(String titulo_enlace, String url_enlace, String tipo_contenido, int id_enlace) throws InstanceNotFoundException;
	
	public List<Entrada> buscarEntradas(Blog blog) throws InstanceNotFoundException;
	
	public Articulo buscarArticulo(int id_articulo) throws InstanceNotFoundException ;
	
	public Enlace buscarEnlace(int id_enlace) throws InstanceNotFoundException ;
	
	public void eliminarArticulo(int id_articulo);
	
	public void eliminarEnlace(int id_enlace);
	
	public void eliminarEntradasBlog(int id_blog);
	
	public List<Blog> buscarTodosBlogs();
	
}
