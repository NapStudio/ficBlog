package asi.ficblog.web.blog;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.blogservice.BlogService;
import asi.ficblog.model.entrada.Entrada;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;


@Controller
public class BlogController {
	
		//@Autowired	
		private BlogService blogService;
		
		@RequestMapping(method = RequestMethod.GET)
		public void list(Model model) {		
			System.out.println("list in blogcontroller");
			List<Blog> list = blogService.buscarTodosBlogs();
			model.addAttribute("blogList", list);
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public Model details(Long id_blog, Model model) throws InstanceNotFoundException, InputValidationException {
			model.addAttribute(blogService.buscarBlog(id_blog));
			return model;
		}
		
		@RequestMapping(method = RequestMethod.GET)
		public Blog form(Model model) {
			return new Blog("Blog ASI",new Date(),"chrisgomez");
		}
		
		@RequestMapping(value = "/addBlog", method = RequestMethod.POST)
		public String addBlog(Blog blog) {
			
			System.out.println(blog);
			
			return "redirect:/blog/list";
		}
		
		@RequestMapping(value = "/getEntradas", method = RequestMethod.GET)
		public void getEntradas(Long id_blog, Model model) throws InstanceNotFoundException, InputValidationException {		
			System.out.println("list entradas");
			Long asd=(long) 7499;
			Articulo articulo= new Articulo("nuewo", new Date(),"textiii",asd);
			blogService.crearArticulo(articulo);
			List<Entrada> list = blogService.buscarEntradas(id_blog);
			list.add(articulo);
			System.out.println("lista de entradas"+list);
			model.addAttribute("entradaList", list);
			model.addAttribute("id_blog", id_blog);
		}
		
		/* EmployeeService */
		public void setBlogService(BlogService blogService) {
			this.blogService = blogService;
		}

		public BlogService getBlogService() {
			return blogService;
		}
		
		
		
}
