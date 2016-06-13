package asi.ficblog.web.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class UsuarioController {
	
		//@Autowired	
		private UsuarioService usuarioService;
		
		@Autowired
		UsuarioValidator usuarioValidator;
		
		@InitBinder("issue")
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(usuarioValidator);
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public void list(Model model) {		
			model.addAttribute("usuarioList", usuarioService.getAllUsuarios());
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public Model details(String login_usuario, Model model) throws InstanceNotFoundException, InputValidationException {
			model.addAttribute(usuarioService.findUsuarioByLogin(login_usuario));
			return model;
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView updateUsuarioForm(String login_usuario, ModelAndView modelAndView) throws InstanceNotFoundException {
			System.out.println("entramos buscando usuario: ");
			Usuario usuario = usuarioService.findUsuarioByLogin(login_usuario);
			System.out.println(usuario);
			modelAndView.addObject("usuario", usuario);
			modelAndView.setViewName("usuario/update");
			return modelAndView;
		}
		
		@RequestMapping(value = "/updateUsuario", method = RequestMethod.POST)
		public String updateUsuario(@ModelAttribute("usuario") Usuario usuario) throws InputValidationException, InstanceNotFoundException {
			System.out.println("modificamos "+usuario);
			usuarioService.modificarUsuario(usuario);
			return "redirect:/main/usuario/list";
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView addUsuarioForm(ModelAndView modelAndView) {
			modelAndView.addObject(new Usuario());
			modelAndView.setViewName("usuario/add");
			return modelAndView;
		}

		
		@RequestMapping(value = "/addUsuario", method = RequestMethod.POST)
		public String addUsuario(@Validated Usuario usuario, BindingResult result, Model model) throws InputValidationException {			
			
			if (result.hasErrors()) {
				
				return "usuario/add";		
				
			} else {
			
				usuarioService.registrarUsuario(usuario);
			
				return "redirect:/main/usuario/list";
			}
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String delete(String login_usuario) throws InstanceNotFoundException {
			usuarioService.eliminarUsuario(login_usuario);
			return "redirect:/main/usuario/list";
		}
		
		/* EmployeeService */
		public void setUsuarioService(UsuarioService usuarioService) {
			this.usuarioService = usuarioService;
		}

		public UsuarioService getUsuarioService() {
			return usuarioService;
		}
		
}