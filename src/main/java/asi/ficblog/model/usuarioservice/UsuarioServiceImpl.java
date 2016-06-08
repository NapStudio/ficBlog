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
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;
import asi.ficblog.model.util.validator.PropertyValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	
	public void validarUsuario(Usuario usuario) throws InputValidationException{
		PropertyValidator.validateMandatoryString("apellidos", usuario.getApellidos_usuario());
		PropertyValidator.validateMandatoryString("contraseña", usuario.getContraseña_usuario());
		PropertyValidator.validateMandatoryString("nick", usuario.getNick_usuario());
		PropertyValidator.validateMandatoryString("login", usuario.getLogin_usuario());
		PropertyValidator.validateMandatoryString("nombre", usuario.getNombre_usuario());
		
	}
	
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
	public void modificarUsuario(Usuario usuario) throws InputValidationException, InstanceNotFoundException {
		validarUsuario(usuario);
		usuarioDAO.update(usuario);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void eliminarUsuario(String login_usuario) throws InstanceNotFoundException {
		usuarioDAO.remove(login_usuario);
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void registrarUsuario(Usuario usuario) throws InputValidationException {
		validarUsuario(usuario);
		usuarioDAO.insert(usuario);
		
	}

}
