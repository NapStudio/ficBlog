package asi.ficblog.model.enlace;

import java.util.List;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface EnlaceDAO {
	/**
	 * Encuentra un enlace por su id.
	 * 
	 * @throws InstanceNotFoundException
	 *             si no existe un enlace con la id dada.
	 */
	public abstract Enlace find(Long id_enlace)
			throws InstanceNotFoundException;

	/**
	 * Encuentra los enlace de un blog dado.
	 * 
	 */

	public abstract List<Enlace> findByBlog(Long blog_enlace);

	/**
	 * Inserta un enlace.
	 * 
	 * @return un enlace, con su identificador.
	 */
	public abstract Enlace insert(Enlace enlace);

	/**
	 * Actualiza la información de un enlace.
	 * 
	 * @return
	 */
	public abstract Enlace update(Enlace enlace) throws InstanceNotFoundException;

	/**
	 * Elimina un enlace por su id.
	 */
	public abstract void remove(Long id_enlace) throws InstanceNotFoundException;

	public abstract void removeAll(Long blog_enlace);

	public void meGustaEnlace(Long id_enlace, String login_usuario);

	public boolean findUsuarioGustaEnlace(Long id_enlace, String login_usuario);
}
