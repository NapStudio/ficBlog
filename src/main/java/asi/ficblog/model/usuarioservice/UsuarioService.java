package asi.ficblog.model.usuarioservice;

import java.util.List;

import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public interface UsuarioService {
	
		public Usuario findUsuarioByLogin(String login_usuario) throws InstanceNotFoundException;
		
		public List<Usuario> findUsuariobyName(String nombre_usuario);
		
		public void registrarUsuarioCompleto(String nombre_usuario, String apellidos_usuario, String login_usuario, String contraseña_usuario,
				String nick_usuario);
		
		public void registrarUsuario(Usuario usuario);
		
		public void modificarUsuario(Usuario usuario);
		
		public void eliminarUsuario(String login_usuario);
		
}
