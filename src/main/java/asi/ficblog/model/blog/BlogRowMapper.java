package asi.ficblog.model.blog;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class BlogRowMapper implements RowMapper<Blog> {

	public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Blog blog = new Blog();
		
		blog.setId_blog(rs.getLong("id_blog"));
		blog.setTitulo_blog(rs.getString("titulo_blog"));
		blog.setFecha_creacion_blog(rs.getDate("fecha_creacion_blog"));
		blog.setUsuario_blog(rs.getString("usuario_blog"));
		
		return blog;
	}

}
