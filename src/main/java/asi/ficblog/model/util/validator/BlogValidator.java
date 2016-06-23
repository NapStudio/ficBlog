package asi.ficblog.model.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import asi.ficblog.model.blog.Blog;

@Component
public class BlogValidator implements Validator {
	
	
	public boolean supports(Class<?> clazz) {
		return Blog.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo_blog", "error.blogs.nombre.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usuario_blog", "error.blogs.usuario.empty");
		
//		Blog blog = (Blog) obj;
//		if (blog.getTitulo_blog() != null && ! blog.getTitulo_blog().equals("")) {
//			errors.rejectValue("titulo_blog", "error.usuario.login.tooshort");
//		}
//		if (blog.getUsuario_blog() != null && ! blog.getUsuario_blog().equals("")) {
//			errors.rejectValue("usuario_blog", "error.usuario.login.tooshort");
//		}
		
	}
}
