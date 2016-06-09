package asi.ficblog.model.blog;

import java.util.List;

import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface BlogDAO {
	/**
	 * Encuentra un blog por su id.
	 * @throws InstanceNotFoundException si no existe un blog con la id dada.
	 */
	public abstract Blog find(Long id_blog)
			throws InstanceNotFoundException;
	
	
	/**
	 * Encuentra los blogs de un usario dado.
	 * 
	 */
	
	public abstract List<Blog> findByUsuario(String usuario)
			throws InstanceNotFoundException;

	/**
	 * Inserta un blog.
	 * @return un blog, con su identificador.
	 */
	public abstract Blog insert(Blog blog);

	/**
	 * Actualiza la información de un blog.
	 * @return 
	 */
	public abstract Blog update(Blog blog);

	/**
	 * Elimina un blog por su id.
	 */
	public abstract void remove(Long id_blog);
	
	public abstract List<Blog> getAll();
	
	public abstract void removeAll();
}
