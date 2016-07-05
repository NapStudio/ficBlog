package asi.ficblog.web.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.usuarioservice.UsuarioService;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;
import asi.ficblog.model.util.validator.UsuarioValidator;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	UsuarioValidator usuarioValidator;

	@InitBinder("usuario")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(usuarioValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		model.addAttribute("usuarioList", usuarioService.getAllUsuarios());
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Model details(String login_usuario, Model model)
			throws InstanceNotFoundException, InputValidationException {
		model.addAttribute(usuarioService.findUsuarioByLogin(login_usuario));
		return model;
	}

	@RequestMapping(value = "/updateUsuarioForm", method = RequestMethod.GET)
	public ModelAndView updateUsuarioForm(String login_usuario,
			ModelAndView modelAndView) throws InstanceNotFoundException {
		System.out.println("entramos buscando usuario: ");
		Usuario usuario = usuarioService.findUsuarioByLogin(login_usuario);
		System.out.println(usuario);
		modelAndView.addObject("usuario", usuario);
		modelAndView.setViewName("usuario/update");
		return modelAndView;
	}

	@RequestMapping(value = "/updateUsuario", method = RequestMethod.POST)
	public String updateUsuario(@ModelAttribute("usuario") Usuario usuario,
			BindingResult result) throws InputValidationException,
			InstanceNotFoundException {
		usuarioValidator.validate(usuario, result);

		if (result.hasErrors()) {
			return "usuario/update";
		} else {

			usuarioService.modificarUsuario(usuario);
			return "redirect:/usuario/details?login_usuario="+usuario.getLogin_usuario();
		}
	}

	@RequestMapping(value = "/addUsuarioForm", method = RequestMethod.GET)
	public ModelAndView addUsuarioForm(ModelAndView modelAndView) {
		modelAndView.addObject(new Usuario());
		modelAndView.setViewName("usuario/add");
		return modelAndView;
	}

	@RequestMapping(value = "/addUsuario", method = RequestMethod.POST)
	public String addUsuario(@Validated Usuario usuario, BindingResult result,
			Model model) throws InputValidationException, DuplicateKeyException {
		try {
			if (result.hasErrors()) {

				return "usuario/add";

			} else {

				usuarioService.registrarUsuario(usuario);

				return "redirect:/usuario/list";
			}
		} catch (DuplicateKeyException e) {
			//TODO handle login usuario not unique
			model.addAttribute(new ObjectError("login_usuario", "error.usuarios.login.tooshort"));
		}

		return "usuario/add";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String login_usuario) throws InstanceNotFoundException {
		usuarioService.eliminarUsuario(login_usuario);
		return "redirect:/usuario/list";
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public UsuarioValidator getUsuarioValidator() {
		return usuarioValidator;
	}

	public void setUsuarioValidator(UsuarioValidator usuarioValidator) {
		this.usuarioValidator = usuarioValidator;
	}

}