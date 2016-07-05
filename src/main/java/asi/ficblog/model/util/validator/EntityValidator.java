package asi.ficblog.model.util.validator;

import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.enlace.Enlace;
import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.util.exceptions.InputValidationException;

public class EntityValidator {
	public static void validarBlog(Blog blog) throws InputValidationException {
		PropertyValidator.validateMandatoryString("title",
				blog.getTitulo_blog());
		PropertyValidator.validateMandatoryString("usuario",
				blog.getUsuario_blog());
	}

	public static void validarArticulo(Articulo articulo)
			throws InputValidationException {
		PropertyValidator.validateMandatoryString("texto",
				articulo.getTexto_articulo());
		PropertyValidator.validateMandatoryString("articulo",
				articulo.getTitulo_entrada());
		PropertyValidator.validateNotNegativeLong("idBlog",
				articulo.getBlog_articulo());
	}

	public static void validarEnlace(Enlace enlace)
			throws InputValidationException {
		PropertyValidator.validateMandatoryString("titulo",
				enlace.getTitulo_entrada());
		PropertyValidator.validateMandatoryString("enlace",
				enlace.getUrl_enlace());
		PropertyValidator.validateNotNegativeLong("idBlog",
				enlace.getBlog_enlace());
	}

	


	
	public static void validarUsuario(Usuario usuario) throws InputValidationException{
		PropertyValidator.validateMandatoryString("apellidos", usuario.getApellidos_usuario());
		PropertyValidator.validateMandatoryString("contrasinal", usuario.getcontrasinal_usuario());
		PropertyValidator.validateMandatoryString("nick", usuario.getNick_usuario());
		PropertyValidator.validateMandatoryString("login", usuario.getLogin_usuario());
		PropertyValidator.validateMandatoryString("nombre", usuario.getNombre_usuario());
		
	}
}
