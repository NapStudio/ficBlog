package asi.ficblog.web.controller.blog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.blogservice.BlogService;
import asi.ficblog.model.entrada.Entrada;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;
import asi.ficblog.model.util.validator.BlogValidator;


@Controller
@RequestMapping(value = "/blog")
public class BlogController {
	
		@Autowired	
		private BlogService blogService;
		
		@Autowired
		private BlogValidator blogValidator;

		@InitBinder("blog")
		protected void initBinder(WebDataBinder binder) {
			binder.setValidator(blogValidator);
		}

		@InitBinder
		public void bindingPreparation(WebDataBinder binder) {
		  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		  binder.registerCustomEditor(Date.class, orderDateEditor);
		}
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public void list(Model model) {		
			System.out.println("list in blogcontroller");
			List<Blog> list = blogService.buscarTodosBlogs();
			model.addAttribute("blogList", list);
		}
		
		@RequestMapping(value = "/details", method = RequestMethod.GET)
		public Model details(Long id_blog, Model model) throws InstanceNotFoundException, InputValidationException {
			model.addAttribute(blogService.buscarBlog(id_blog));
			return model;
		}
		
		@RequestMapping(value = "/addBlogForm", method = RequestMethod.GET)
		public ModelAndView addBlogForm(ModelAndView modelAndView) {
			modelAndView.addObject(new Blog());
			modelAndView.setViewName("blog/add");
			return modelAndView;
		}
		
		@RequestMapping(value = "/addBlog", method = RequestMethod.POST)
		public String addBlog(@Validated Blog blog, BindingResult result, Model model) throws InputValidationException {			
			
			if (result.hasErrors()) {
				
				return "blog/add";		
				
			} else {
			
				blogService.crearBlog(blog);
			
				return "redirect:/blog/list";
			}
		}
		

		
		@RequestMapping(value = "/getEntradas", method = RequestMethod.GET)
		public void getEntradas(Long id_blog, Model model) throws InstanceNotFoundException, InputValidationException {		
			System.out.println("list entradas");
			List<Entrada> list = blogService.buscarEntradas(id_blog);
			model.addAttribute("entradaList", list);
			model.addAttribute("id_blog", id_blog);
		}

		public BlogService getBlogService() {
			return blogService;
		}

		public void setBlogService(BlogService blogService) {
			this.blogService = blogService;
		}

		public BlogValidator getBlogValidator() {
			return blogValidator;
		}

		public void setBlogValidator(BlogValidator blogValidator) {
			this.blogValidator = blogValidator;
		}
		
		
		
		
}
