package asi.ficblog.model.usuarioservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.blog.BlogDAO;
import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.usuario.UsuarioDAO;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;
import asi.ficblog.model.util.validator.EntityValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private BlogDAO blogDAO;
	
	public Usuario findUsuarioByLogin(String login_usuario) throws InstanceNotFoundException {
		Usuario usuario = usuarioDAO.find(login_usuario);
		return usuario;
	}


	public List<Usuario> findUsuariobyName(String nombre_usuario) {
		List<Usuario> lista_usuarios = new ArrayList<Usuario>();
		lista_usuarios = usuarioDAO.findByName(nombre_usuario);
		return lista_usuarios;
	}


	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void modificarUsuario(Usuario usuario) throws InputValidationException, InstanceNotFoundException {
		EntityValidator.validarUsuario(usuario);
		System.out.println("servicio "+usuario);
		usuarioDAO.update(usuario);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void eliminarUsuario(String login_usuario) throws InstanceNotFoundException{
		List<Blog> blogs = new ArrayList<Blog>();
		try {
			blogs = blogDAO.findByUsuario(login_usuario);
		} catch (InstanceNotFoundException e) {
			throw new InstanceNotFoundException();
		}
		if(blogs!=null&&!blogs.isEmpty()){
			throw new InstanceNotFoundException();
		}else{
			usuarioDAO.remove(login_usuario);			
		}		
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void registrarUsuario(Usuario usuario) throws InputValidationException {
		EntityValidator.validarUsuario(usuario);
		usuarioDAO.insert(usuario);
		
	}

	public List<Usuario> getAllUsuarios() {
		return usuarioDAO.getAll();
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}
	
	

}
