package asi.ficblog.model.articulo;

import java.util.List;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface ArticuloDAO {
	/**
	 * Encuentra un articulo por su id.
	 * @throws InstanceNotFoundException si no existe un articulo con la id dada.
	 */
	public abstract Articulo find(Integer id_articulo)
			throws InstanceNotFoundException;
	
	
	/**
	 * Encuentra los articulo de un blog dado.
	 * 
	 */
	
	public abstract List<Articulo> findByBlog(int blog_articulo)
			throws InstanceNotFoundException;

	/**
	 * Inserta un articulo.
	 * @return un articulo, con su identificador.
	 */
	public abstract Articulo insert(Articulo articulo);

	/**
	 * Actualiza la información de un articulo.
	 * @return 
	 * @throws InstanceNotFoundException 
	 */
	public abstract Articulo update(Articulo articulo) throws InstanceNotFoundException;

	/**
	 * Elimina un articulo por su id.
	 */
	public abstract void remove(Integer id_articulo) throws InstanceNotFoundException;

	public abstract void removeAll(int blog_articulo) throws InstanceNotFoundException;
}