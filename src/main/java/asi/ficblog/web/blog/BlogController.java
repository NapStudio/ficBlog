package asi.ficblog.web.blog;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.blogservice.BlogService;


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
		public Blog form(Model model) {
			return new Blog("Blog ASI",new Date(),"chrisgomez");
		}
		
		@RequestMapping(value = "/addBlog", method = RequestMethod.POST)
		public String addBlog(Blog blog) {
			
			System.out.println(blog);
			
			return "redirect:/blog/list";
		}
		
		/* EmployeeService */
		public void setBlogService(BlogService blogService) {
			this.blogService = blogService;
		}

		public BlogService getBlogService() {
			return blogService;
		}
		
		
		
}
