package asi.ficblog.web.controller.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginForm(@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout) {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("loginForm in...");
		if (error != null) {
			mav.addObject("error", "usuarios.login.error");
			System.out.println("error in loginForm!");
		}
		
		if (logout != null) {
			mav.addObject("msg", "usuarios.login.logoutmessage");
		}
		
		
		mav.addObject("login_usuario",  new String());
		mav.addObject("contrasinal_usuario",  new String());
		
		mav.setViewName("login");
		
		return mav;
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.POST)
	public ModelAndView notAllowed() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("403");
		return mav;
	}

}
