package asi.ficblog.model.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import asi.ficblog.model.usuario.Usuario;


public class UsuarioValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Usuario.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		
		Usuario usuario = (Usuario) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "error.usuario.login.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "error.usuario.nombre.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "error.usuario.nick.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "error.usuario.apellidos.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contraseña", "error.usuario.contraseña.empty");
		
		if (usuario.getLogin_usuario() != null && ! usuario.getLogin_usuario().equals("") &&
				usuario.getLogin_usuario().length() < 10) {
			errors.rejectValue("login", "error.usuario.login.tooshort");
		}
		if (usuario.getNick_usuario() != null && ! usuario.getNick_usuario().equals("") &&
				usuario.getNick_usuario().length() < 5) {
			errors.rejectValue("login", "error.usuario.login.tooshort");
		}
		if (usuario.getContraseña_usuario() != null && ! usuario.getContraseña_usuario().equals("") &&
				usuario.getContraseña_usuario().length() < 8) {
			errors.rejectValue("login", "error.usuario.login.tooshort");
		}
		
	}
}
