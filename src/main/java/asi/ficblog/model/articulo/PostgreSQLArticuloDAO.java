package asi.ficblog.model.articulo;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public class PostgreSQLArticuloDAO implements ArticuloDAO {

	private NamedParameterJdbcOperations jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static String CREATE_SQL = "INSERT INTO articulo (titulo_articulo, fecha_publicacion_articulo, texto_articulo, me_gusta_articulo, blog_articulo ) "
			+ "VALUES (:titulo_articulo, :fecha_publicacion_articulo, :texto_articulo, :me_gusta_articulo, :blog_articulo )";

	private static String UPDATE_SQL = "UPDATE articulo SET titulo_articulo = :titulo_articulo, fecha_publicacion_articulo = :fecha_publicacion_articulo, "
			+ "texto_articulo = :texto_articulo, me_gusta_articulo = :me_gusta_articulo, blog_articulo = :blog_articulo "
			+ "WHERE id_articulo = :id_articulo";

	private static String GET_BYBLOG_SQL = "SELECT id_articulo, titulo_articulo, fecha_publicacion_articulo, texto_articulo, me_gusta_articulo, blog_articulo "
			+ "FROM articulo " + "WHERE blog_articulo = :blog_articulo";

	private static String GET_SQL = "SELECT id_articulo, titulo_articulo, fecha_publicacion_articulo, texto_articulo, me_gusta_articulo, blog_articulo "
			+ "FROM articulo " + "WHERE id_articulo = :id_articulo";

	private static String DELETE_ALL_SQL = "DELETE FROM articulo WHERE blog_articulo = :blog_articulo";

	private static String DELETE_SQL = "DELETE FROM articulo WHERE id_articulo = :id_articulo";

	public Articulo insert(Articulo articulo) {

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("titulo_articulo", articulo.getTitulo_articulo())
				.addValue("fecha_publicacion_articulo",
						articulo.getFecha_publicacion_articulo())
				.addValue("texto_articulo", articulo.getTexto_articulo())
				.addValue("me_gusta_articulo", articulo.isMe_gusta_entrada())
				.addValue("blog_articulo", articulo.getBlog_articulo());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(CREATE_SQL, params, keyHolder,
				new String[] { "id_articulo" });

		articulo.setId_articulo(keyHolder.getKey().longValue());

		return articulo;

	}

	public Articulo update(Articulo articulo) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("titulo_articulo", articulo.getTitulo_articulo())
				.addValue("fecha_publicacion_articulo",
						articulo.getFecha_publicacion_articulo())
				.addValue("texto_articulo", articulo.getTexto_articulo())
				.addValue("me_gusta_articulo", articulo.isMe_gusta_entrada())
				.addValue("id_articulo", articulo.getId_articulo())
				.addValue("blog_articulo", articulo.getBlog_articulo());

		jdbcTemplate.update(UPDATE_SQL, params);

		return articulo;
	}

	public Articulo find(Long id_articulo) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_articulo", id_articulo);
		try {
			return jdbcTemplate.queryForObject(GET_SQL, params,
					new ArticuloRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}

	}

	public List<Articulo> findByBlog(Long blog_articulo)
			throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"blog_articulo", blog_articulo);
		return jdbcTemplate.query(GET_BYBLOG_SQL, params,
				new ArticuloRowMapper());

	}

	public void remove(Long id_articulo) throws InstanceNotFoundException {

		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_articulo", id_articulo);

		jdbcTemplate.update(DELETE_SQL, params);

	}

	public void removeAll(Long blog_articulo) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"blog_articulo", blog_articulo);
		;

		jdbcTemplate.update(DELETE_ALL_SQL, params);

	}

}
