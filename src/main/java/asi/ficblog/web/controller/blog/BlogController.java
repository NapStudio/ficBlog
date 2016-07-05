package asi.ficblog.web.controller.blog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.blog.Blog;
import asi.ficblog.model.blogservice.BlogService;
import asi.ficblog.model.enlace.Enlace;
import asi.ficblog.model.entrada.Entrada;
import asi.ficblog.model.util.exceptions.InputValidationException;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;
import asi.ficblog.model.util.validator.BlogValidator;
import asi.ficblog.model.util.validator.ArticuloValidator;
import asi.ficblog.model.util.validator.EnlaceValidator;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private BlogValidator blogValidator;

	@Autowired
	private ArticuloValidator articuloValidator;

	@Autowired
	private EnlaceValidator enlaceValidator;

	@InitBinder("blog")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(blogValidator);
	}

	@InitBinder("articulo")
	protected void initArticuloBinder(WebDataBinder binder) {
		binder.setValidator(articuloValidator);
	}

	@InitBinder("enlace")
	protected void initEnlaceBinder(WebDataBinder binder) {
		binder.setValidator(enlaceValidator);
	}

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat,
				true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(String login_usuario, Model model) {
		System.out.println("list in blogcontroller");
		List<Blog> list = blogService.buscarTodosBlogs();
		if (login_usuario != null) {
			List<Blog> blogs = new ArrayList<Blog>();
			for (Blog b : list) {
				System.out.println("usuario: " + b.getUsuario_blog() + " o "
						+ login_usuario);
				if (b.getUsuario_blog().equals(login_usuario)) {
					blogs.add(b);
				}
			}
			model.addAttribute("blogList", blogs);
		} else {
			model.addAttribute("blogList", list);
		}

	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Model details(Long id_blog, Model model)
			throws InstanceNotFoundException, InputValidationException {
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
	public String addBlog(@Validated Blog blog, BindingResult result,
			Model model) throws InputValidationException {

		if (result.hasErrors()) {

			return "blog/add";

		} else {

			blogService.crearBlog(blog);

			return "redirect:/blog/list";
		}
	}

	@RequestMapping(value = "/getEntradas", method = RequestMethod.GET)
	public void getEntradas(String usuario_blog, Long id_blog, Model model)
			throws InstanceNotFoundException, InputValidationException {
		System.out.println("list entradas");
		List<Entrada> list = blogService.buscarEntradas(id_blog);
		model.addAttribute("entradaList", list);
		model.addAttribute("id_blog", id_blog);
		model.addAttribute("usuario_blog", usuario_blog);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Long id_blog) throws InstanceNotFoundException,
			InputValidationException {
		blogService.eliminarBlog(id_blog);
		return "redirect:/blog/list";
	}

	@RequestMapping(value = "articulo/meGusta", method = RequestMethod.GET)
	public String meGustaArticulo(Long id_articulo, String login_usuario)
			throws InstanceNotFoundException, InputValidationException {
		blogService.meGustaArticulo(id_articulo, login_usuario);
		Long id_blog = (blogService.buscarArticulo(id_articulo)
				.getBlog_articulo());
		String ownerBlog = blogService.buscarBlog(id_blog).getUsuario_blog();
		return "redirect:/blog/getEntradas?id_blog=" + id_blog
				+ "&usuario_blog=" + ownerBlog;
	}

	@RequestMapping(value = "enlace/meGusta", method = RequestMethod.GET)
	public String meGustaEnlace(Long id_enlace, String login_usuario)
			throws InstanceNotFoundException, InputValidationException {
		blogService.meGustaEnlace(id_enlace, login_usuario);
		Long id_blog = (blogService.buscarEnlace(id_enlace).getBlog_enlace());
		String ownerBlog = blogService.buscarBlog(id_blog).getUsuario_blog();
		return "redirect:/blog/getEntradas?id_blog=" + id_blog
				+ "&usuario_blog=" + ownerBlog;
	}

	@RequestMapping(value = "/addArticuloForm", method = RequestMethod.GET)
	public ModelAndView addArticuloForm(Long id_blog, ModelAndView modelAndView) {
		Articulo articulo = new Articulo();
		articulo.setBlog_articulo(id_blog);
		modelAndView.addObject(articulo);
		modelAndView.setViewName("blog/addArticulo");
		return modelAndView;
	}

	@RequestMapping(value = "/addArticulo", method = RequestMethod.POST)
	public String addArticulo(@Validated Articulo articulo,
			BindingResult result, Model model) throws InputValidationException,
			InstanceNotFoundException {

		if (result.hasErrors()) {

			return "redirect:addArticuloForm?id_blog="
					+ articulo.getBlog_articulo();

		} else {
			System.out.println(articulo);
			blogService.crearArticulo(articulo);
			Blog blog = blogService.buscarBlog(articulo.getBlog_articulo());
			return "redirect:/blog/getEntradas?id_blog="
					+ articulo.getBlog_articulo() + "&usuario_blog="
					+ blog.getUsuario_blog();
		}
	}

	@RequestMapping(value = "/articulo/delete", method = RequestMethod.GET)
	public String deleteArticulo(Long id_articulo)
			throws InstanceNotFoundException, InputValidationException {
		Blog blog = blogService.buscarBlog(blogService.buscarArticulo(
				id_articulo).getBlog_articulo());
		blogService.eliminarArticulo(id_articulo);
		return "redirect:/blog/getEntradas?id_blog="
					+ blog.getId_blog() + "&usuario_blog="
					+ blog.getUsuario_blog();
	}

	@RequestMapping(value = "/addEnlaceForm", method = RequestMethod.GET)
	public ModelAndView addEnlaceForm(Long id_blog, ModelAndView modelAndView) {
		Enlace enlace = new Enlace();
		enlace.setBlog_enlace(id_blog);
		modelAndView.addObject(enlace);
		modelAndView.setViewName("blog/addEnlace");
		return modelAndView;
	}

	@RequestMapping(value = "/addEnlace", method = RequestMethod.POST)
	public String addEnlace(@Validated Enlace enlace, BindingResult result,
			Model model) throws InputValidationException {

		if (result.hasErrors()) {

			return "redirect:addArticuloForm?id_blog="
					+ enlace.getBlog_enlace();

		} else {

			blogService.crearEnlace(enlace);

			return "redirect:/blog/getEntradas?id_blog="
					+ enlace.getBlog_enlace();
		}
	}

	@RequestMapping(value = "/enlace/delete", method = RequestMethod.GET)
	public String deleteEnlace(Long id_enlace)
			throws InstanceNotFoundException, InputValidationException {
		Blog blog = blogService.buscarBlog(blogService.buscarEnlace(
				id_enlace).getBlog_enlace());
		blogService.eliminarEnlace(id_enlace);
		return "redirect:/blog/getEntradas?id_blog="
		+ blog.getId_blog() + "&usuario_blog="
		+ blog.getUsuario_blog();
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

	public ArticuloValidator getEntradaValidator() {
		return articuloValidator;
	}

	public void setEntradaValidator(ArticuloValidator entradaValidator) {
		this.articuloValidator = entradaValidator;
	}

}
