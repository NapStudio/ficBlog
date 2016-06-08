package asi.ficblog.model.enlace;

import java.util.List;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface EnlaceDAO {
	/**
	 * Encuentra un enlace por su id.
	 * @throws InstanceNotFoundException si no existe un enlace con la id dada.
	 */
	public abstract Enlace find(Integer id_enlace)
			throws InstanceNotFoundException;
	
	
	/**
	 * Encuentra los enlace de un blog dado.
	 * 
	 */
	
	public abstract List<Enlace> findByBlog(int blog_enlace)
			throws InstanceNotFoundException;

	/**
	 * Inserta un enlace.
	 * @return un enlace, con su identificador.
	 */
	public abstract Enlace insert(Enlace enlace);

	/**
	 * Actualiza la información de un enlace.
	 * @return 
	 */
	public abstract Enlace update(Enlace enlace);

	/**
	 * Elimina un enlace por su id.
	 */
	public abstract void remove(Integer id_enlace);


	public abstract void removeAll(int blog_enlace);
}
