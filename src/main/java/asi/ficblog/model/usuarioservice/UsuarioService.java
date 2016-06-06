package asi.ficblog.model.usuarioservice;

import java.util.List;

import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface UsuarioService {
	
		public Usuario findUsuarioByLogin(String login_usuario) throws InstanceNotFoundException;
		
		public List<Usuario> findUsuariobyName(String nombre_usuario);
		
		public void registrarUsuario(Usuario usuario) throws InputValidationException;
		
		public void modificarUsuario(Usuario usuario) throws InputValidationException, InstanceNotFoundException;
		
		public void eliminarUsuario(String login_usuario) throws InstanceNotFoundException;
		
}
