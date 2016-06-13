package asi.ficblog.model.blog;


import java.util.List;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import asi.ficblog.model.articulo.Articulo;
import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

@Repository
public class PostgreSQLBlogDAO implements BlogDAO {
	
	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;
	
	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static String CREATE_SQL =
	"INSERT INTO blog (titulo_blog, fecha_creacion_blog, usuario_blog) " 
	+ "VALUES (:titulo_blog, :fecha_creacion_blog, :usuario_blog)";
	
	private static String UPDATE_SQL =
	"UPDATE blog SET titulo_blog = :titulo_blog, fecha_creacion_blog = :fecha_creacion_blog, usuario_blog = :usuario_blog "
	+ "WHERE id_blog = :id_blog";
	
	private static String GET_ALL_SQL = 
			"SELECT id_blog, titulo_blog, fecha_creacion_blog, usuario_blog FROM blog";

	
	private static String GET_SQL = 
			GET_ALL_SQL+" WHERE id_blog = :id_blog";
	
	private static String GET_BYUSUARIO_SQL = 
			GET_ALL_SQL+" WHERE usuario_blog = :usuario_blog";

	
	private static String DELETE_ALL_SQL =
			"DELETE FROM blog";
	
	private static String DELETE_ALLArticulos_SQL =
			"DELETE FROM articulo";
	
	private static String DELETE_ALLEnlaces_SQL =
			"DELETE FROM enlace";
	
	private static String DELETE_SQL =
			DELETE_ALL_SQL+" WHERE id_blog = :id_blog";
	

	public Blog insert(Blog blog) {

		SqlParameterSource params = new MapSqlParameterSource().
				addValue("titulo_blog", blog.getTitulo_blog()).
				addValue("fecha_creacion_blog",blog.getFecha_creacion_blog()).
				addValue("usuario_blog", blog.getUsuario_blog());
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			jdbcTemplate.update(CREATE_SQL, params, keyHolder, new String [] {"id_blog"});
			
			blog.setId_blog(keyHolder.getKey().longValue());
			
			return blog;		
	}
	
	public Blog update(Blog blog) {
		
		SqlParameterSource params = new MapSqlParameterSource().
				addValue("titulo_articulo", blog.getTitulo_blog()).
				addValue("titulo_blog", blog.getTitulo_blog()).
				addValue("fecha_creacion_blog", blog.getFecha_creacion_blog()).
				addValue("id_blog", blog.getId_blog()).
				addValue("usuario_blog", blog.getUsuario_blog());
			
			jdbcTemplate.update(UPDATE_SQL, params);
			
			return blog;
		
	}
	
	public Blog find(Long id_blog) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().
				addValue("id_blog", id_blog);
		try{
			return jdbcTemplate.queryForObject(GET_SQL, params,new BlogRowMapper());
		}catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}
			
		
	}
	
	

	public List<Blog> findByUsuario(String usuario_blog) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().
				addValue("usuario_blog", usuario_blog);
		return jdbcTemplate.query(GET_BYUSUARIO_SQL, params, new BlogRowMapper());		
	}
	
	public List<Blog> getAll() {
		System.out.println("getAll");
		List<Blog> blogs=jdbcTemplate.query(GET_ALL_SQL, new BlogRowMapper());	
		System.out.println(blogs);
		return blogs;
	}	

	public void remove(Long id_blog) {		SqlParameterSource params = new MapSqlParameterSource().
				addValue("id_blog", id_blog);
		
		jdbcTemplate.update(DELETE_SQL, params);
	}
	


	public void removeAll() {
		jdbcTemplate.query(DELETE_ALL_SQL, new BlogRowMapper());
	}

}
