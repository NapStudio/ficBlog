package asi.ficblog.model.usuarioservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.usuario.UsuarioDAO;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class UsuarioServiceImplement implements UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	
	public Usuario findUsuarioByLogin(String login_usuario) throws InstanceNotFoundException {
		Usuario usuario = usuarioDAO.find(login_usuario);
		return usuario;
	}


	public List<Usuario> findUsuariobyName(String nombre_usuario) {
		List<Usuario> lista_usuarios = new ArrayList<Usuario>();
		lista_usuarios = usuarioDAO.findByName(nombre_usuario);
		return lista_usuarios;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void registrarUsuarioCompleto(String nombre_usuario, String apellidos_usuario, String login_usuario,
			String contraseña_usuario, String nick_usuario) {
		Usuario usuario = new Usuario(nombre_usuario, apellidos_usuario, login_usuario, contraseña_usuario,
				nick_usuario);
		usuarioDAO.insert(usuario);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void modificarUsuario(Usuario usuario) {
		usuarioDAO.update(usuario);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void eliminarUsuario(String login_usuario) {
		usuarioDAO.remove(login_usuario);

	}


	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void registrarUsuario(Usuario usuario) {
		usuarioDAO.insert(usuario);
		
	}

}
