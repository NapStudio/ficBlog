package asi.ficblog.model.usuario;

import java.util.List;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;


public interface UsuarioDAO {
	
	/**
	 * Encuentra Usuario por su login.
	 * @throws InstanceNotFoundException Si no encuentra el asi.ficblog.model.usuario.
	 */
	public abstract Usuario find(String login_usuario)	 
			throws InstanceNotFoundException ;
	
	/**
	 * Encuentra Usuario por su nombre.
	 * @throws InstanceNotFoundException Si no encuentra ningun asi.ficblog.model.usuario.
	 */
	public abstract List<Usuario> findByName(String nombre_usuario);
	

	/**
	 * Inserta un nuevo Usuario.
	 * @return El asi.ficblog.model.usuario creado.
	 */
	public abstract Usuario insert(	Usuario usuario);

	/**
	 * Actualiza la información de un asi.ficblog.model.usuario dado.
	 */
	public abstract void update(Usuario usuario);

	/**
	 * Elimina un asi.ficblog.model.usuario por su login
	 */
	public abstract void remove(String login_usuario);
}
