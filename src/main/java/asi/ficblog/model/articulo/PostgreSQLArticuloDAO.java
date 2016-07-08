package asi.ficblog.model.articulo;

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
public class PostgreSQLArticuloDAO implements ArticuloDAO {

	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static String CREATE_SQL = "INSERT INTO articulo (titulo_articulo, fecha_publicacion_articulo,"
			+ " texto_articulo, me_gusta_articulo, blog_articulo ) "
			+ "VALUES (:titulo_articulo, :fecha_publicacion_articulo, :texto_articulo, :me_gusta_articulo, :blog_articulo )";

	private static String UPDATE_SQL = "UPDATE articulo SET titulo_articulo = :titulo_articulo, "
			+ "fecha_publicacion_articulo = :fecha_publicacion_articulo, "
			+ "texto_articulo = :texto_articulo, blog_articulo = :blog_articulo "
			+ "WHERE id_articulo = :id_articulo";

	private static String GET_BYBLOG_SQL = "SELECT id_articulo, titulo_articulo, fecha_publicacion_articulo,"
			+ " texto_articulo, me_gusta_articulo, blog_articulo "
			+ "FROM articulo " + "WHERE blog_articulo = :blog_articulo";

	private static String GET_SQL = "SELECT id_articulo, titulo_articulo, fecha_publicacion_articulo,"
			+ " texto_articulo, me_gusta_articulo, blog_articulo "
			+ "FROM articulo " + "WHERE id_articulo = :id_articulo";

	private static String DELETE_ALL_SQL = "DELETE FROM articulo WHERE blog_articulo = :blog_articulo";

	private static String DELETE_SQL = "DELETE FROM articulo WHERE id_articulo = :id_articulo";

	private static String CREATE_MEGUSTA_SQL = "INSERT INTO megusta(id_articulo, login_usuario) VALUES (:id_articulo, :login_usuario)";

	private static String INCREASE_MEGUSTA_SQL = "UPDATE articulo SET me_gusta_articulo = me_gusta_articulo+1"
			+ "WHERE id_articulo = :id_articulo";

	private static String GET_MEGUSTA_SQL = "SELECT COUNT(*) FROM megusta"
			+ " WHERE id_articulo = :id_articulo AND"
			+ " login_usuario = :login_usuario";

	public Articulo insert(Articulo articulo) {

		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("titulo_articulo", articulo.getTitulo_entrada())
				.addValue("fecha_publicacion_articulo",
						articulo.getFecha_publicacion_entrada())
				.addValue("texto_articulo", articulo.getTexto_articulo())
				.addValue("me_gusta_articulo", 0)
				.addValue("blog_articulo", articulo.getBlog_articulo());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(CREATE_SQL, params, keyHolder,
				new String[] { "id_articulo" });

		articulo.setId_articulo(keyHolder.getKey().longValue());

		System.out.println("articulo insert: " + articulo);

		return articulo;

	}

	public Articulo update(Articulo articulo) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("titulo_articulo", articulo.getTitulo_entrada())
				.addValue("fecha_publicacion_articulo",
						articulo.getFecha_publicacion_entrada())
				.addValue("texto_articulo", articulo.getTexto_articulo())
				.addValue("id_articulo", articulo.getId_articulo())
				.addValue("blog_articulo", articulo.getBlog_articulo());

		try {
			jdbcTemplate.update(UPDATE_SQL, params);
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}

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

	public List<Articulo> findByBlog(Long blog_articulo) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"blog_articulo", blog_articulo);
		return jdbcTemplate.query(GET_BYBLOG_SQL, params,
				new ArticuloRowMapper());

	}

	public void remove(Long id_articulo) throws InstanceNotFoundException {

		try {
			SqlParameterSource params = new MapSqlParameterSource().addValue(
					"id_articulo", id_articulo);

			jdbcTemplate.update(DELETE_SQL, params);
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}

	}

	public void removeAll(Long blog_articulo) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"blog_articulo", blog_articulo);
		try {
			jdbcTemplate.update(DELETE_ALL_SQL, params);
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}

	}

	public void meGustaArticulo(Long id_articulo, String login_usuario) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_articulo", id_articulo).addValue("login_usuario",
				login_usuario);

		jdbcTemplate.update(CREATE_MEGUSTA_SQL, params);
		params = new MapSqlParameterSource().addValue("id_articulo",
				id_articulo);
		jdbcTemplate.update(INCREASE_MEGUSTA_SQL, params);
	}

	public boolean findUsuarioGustaArticulo(Long id_articulo,
			String login_usuario) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"id_articulo", id_articulo).addValue("login_usuario",
				login_usuario);
		boolean bool = false;
		if (jdbcTemplate.queryForObject(GET_MEGUSTA_SQL, params, Integer.class) > 0) {
			bool = true;
		}
		return bool;
	}

}
