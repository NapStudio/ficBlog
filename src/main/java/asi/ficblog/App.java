package asi.ficblog;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import asi.ficblog.model.usuario.Usuario;
import asi.ficblog.model.usuarioservice.UsuarioService;

public class App{
    public static void main( String[] args ) throws Exception{
    	ApplicationContext context = 
    			new FileSystemXmlApplicationContext("resources/spring-module.xml");
    	
    	UsuarioService usuarioService =
    			(UsuarioService) context.getBean("usuarioServiceBean");
    	
    	Usuario e = usuarioService.findUsuarioByLogin("chrisgomez");
    	System.out.println(e);
    	
    	
    }
}
