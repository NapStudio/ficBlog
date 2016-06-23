package asi.ficblog.model.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import asi.ficblog.model.usuario.Usuario;

@Component
public class UsuarioValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Usuario.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		
		Usuario usuario = (Usuario) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login_usuario", "error.usuarios.login.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre_usuario", "error.usuarios.nombre.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick_usuario", "error.usuarios.nick.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos_usuario", "error.usuarios.apellidos.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contrasinal_usuario", "error.usuarios.contrasinal.empty");
		
		if (usuario.getLogin_usuario() != null && ! usuario.getLogin_usuario().equals("") &&
				usuario.getLogin_usuario().length() < 10) {
			errors.rejectValue("login_usuario", "error.usuarios.login.tooshort");
		}
		if (usuario.getNick_usuario() != null && ! usuario.getNick_usuario().equals("") &&
				usuario.getNick_usuario().length() < 5) {
			errors.rejectValue("nick_usuario", "error.usuarios.login.tooshort");
		}
		if (usuario.getcontrasinal_usuario() != null && ! usuario.getcontrasinal_usuario().equals("") &&
				usuario.getcontrasinal_usuario().length() < 8) {
			errors.rejectValue("contrasinal_usuario", "error.usuarios.login.tooshort");
		}
		
	}
}
