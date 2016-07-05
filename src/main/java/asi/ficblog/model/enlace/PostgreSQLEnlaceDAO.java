package asi.ficblog.model.enlace;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

@Repository
public class PostgreSQLEnlaceDAO implements EnlaceDAO {

	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static String CREATE_SQL = "INSERT INTO enlace (titulo_enlace, fecha_publicacion_enlace, url_enlace, tipo_contenido_enlace, me_gusta_enlace, blog_enlace) "
			+ "VALUES (:titulo_enlace, :fecha_publicacion_enlace, :url_enlace, :tipo_contenido_enlace, :me_gusta_enlace, :blog_enlace)";

	private static String UPDATE_SQL = "UPDATE enlace SET titulo_enlace = :titulo_enlace, fecha_publicacion_enlace = :fecha_publicacion_enlace, url_enlace = :url_enlace,"
			+ " tipo_contenido_enlace = :tipo_contenido_enlace, blog_enlace = :blog_enlace "
			+ "WHERE id_enlace = :id_enlace";

	private static String GET_BYBLOG_SQL = "SELECT id_enlace, titulo_enlace, fecha_publicacion_enlace,"
			+ " url_enlace, tipo_contenido_enlace, me_gusta_enlace, blog_enlace "
			+ "FROM enlace " + "WHERE blog_enlace = :blog_enlace";

	private static String GET_SQL = "SELECT id_enlace, titulo_enlace, fecha_publicacion_enlace,"
			+ " url_enlace, tipo_contenido_enlace, me_gusta_enlace, blog_enlace "
			+ "FROM enlace " + "WHERE id_enlace = :id_enlace";

	private static String DELETE_ALL_SQL = "DELETE FROM enlace WHERE blog_enlace = :blog_enlace";

	private static String DELETE_SQL = "DELETE FROM enlace WHERE id_enlace = :id_enlace";

	private static String CREATE_MEGUSTA_SQL = "INSERT INTO megusta(id_enlace, login_usuario) VALUES (:id_enlace, :login_usuario)";

	private static String INCREASE_MEGUSTA_SQL = "UPDATE enlace SET me_gusta_enlace = me_gusta_enlace+1 WHERE id_enlace = :id_enlace";

	private static String GET_MEGUSTA_SQL = "SELECT COUNT(*) FROM megusta"
			+ " WHERE id_enlace = :id_enlace AND"
			+ " login_usuario = :login_usuario";

	public Enlace insert(Enlace enlace) {

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("titulo_enlace", enlace.getTitulo_entrada())
				.addValue("fecha_publicacion_enlace",
						enlace.getFecha_publicacion_entrada())
				.addValue("url_enlace", enlace.getUrl_enlace())
				.addValue("me_gusta_enlace", 0)
				.addValue("tipo_contenido_enlace",
						enlace.getTipo_contenido_enlace())
				.addValue("blog_enlace", enlace.getBlog_enlace());

		System.out.println("\n insertando fecha:");
		System.out.println(enlace.getFecha_publicacion_entrada());
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(CREATE_SQL, params, keyHolder,
				new String[] { "id_enlace" });

		enlace.setId_enlace(keyHolder.getKey().longValue());

		return enlace;
	}

	public Enlace update(Enlace enlace) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("titulo_enlace", enlace.getTitulo_entrada())
				.addValue("fecha_publicacion_enlace",
						enlace.getFecha_publicacion_entrada())
				.addValue("url_enlace", enlace.getUrl_enlace())
				.addValue("tipo_contenido_enlace",
						enlace.getTipo_contenido_enlace())
				.addValue("id_enlace", enlace.getId_enlace())
				.addValue("blog_enlace", enlace.getBlog_enlace());

		jdbcTemplate.update(UPDATE_SQL, params);

		return enlace;
	}

	public Enlace find(Long id_enlace) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_enlace", id_enlace);
		try {
			return jdbcTemplate.queryForObject(GET_SQL, params,
					new EnlaceRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}
	}

	public List<Enlace> findByBlog(Long blog_enlace)
			throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"blog_enlace", blog_enlace);

		return jdbcTemplate
				.query(GET_BYBLOG_SQL, params, new EnlaceRowMapper());
	}

	public void remove(Long id_enlace) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_enlace", id_enlace);

		jdbcTemplate.update(DELETE_SQL, params);
	}

	public void removeAll(Long blog_enlace) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"blog_enlace", blog_enlace);
		;

		jdbcTemplate.update(DELETE_ALL_SQL, params);
	}

	public void meGustaEnlace(Long id_enlace, String login_usuario) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_enlace", id_enlace)
				.addValue("login_usuario", login_usuario);

		jdbcTemplate.update(CREATE_MEGUSTA_SQL, params);
		params = new MapSqlParameterSource().addValue("id_enlace", id_enlace);
		jdbcTemplate.update(INCREASE_MEGUSTA_SQL, params);
	}

	public boolean findUsuarioGustaEnlace(Long id_enlace, String login_usuario) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_enlace", id_enlace)
				.addValue("login_usuario", login_usuario);
		boolean bool = false;
		if (jdbcTemplate.queryForObject(GET_MEGUSTA_SQL, params, Integer.class) > 0) {
			bool = true;
		}
		return bool;
	}

}
