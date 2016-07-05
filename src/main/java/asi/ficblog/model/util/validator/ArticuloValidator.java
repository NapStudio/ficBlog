package asi.ficblog.model.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import asi.ficblog.model.articulo.Articulo;

@Component
public class ArticuloValidator implements Validator {
	
	
	public boolean supports(Class<?> clazz) {
		return Articulo.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo_entrada", "error.blogs.nombre.empty");

	}
}
