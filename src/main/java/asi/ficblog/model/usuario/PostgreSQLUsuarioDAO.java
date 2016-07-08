package asi.ficblog.model.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

@Repository
public class PostgreSQLUsuarioDAO implements UsuarioDAO {

	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static String CREATE_SQL = "INSERT INTO usuario (nombre_usuario, apellidos_usuario, login_usuario, contrasinal_usuario, nick_usuario, enabled) "
			+ "VALUES (:nombre_usuario, :apellidos_usuario, :login_usuario, :contrasinal_usuario, :nick_usuario, :enabled)";

	private static String CREATE_USERROLES_SQL = "INSERT INTO UserRoles (login_usuario, role) "
			+ "VALUES (:login_usuario, :role)";

	private static String UPDATE_SQL = "UPDATE usuario SET nombre_usuario = :nombre_usuario, apellidos_usuario = :apellidos_usuario, "
			+ "contrasinal_usuario = :contrasinal_usuario, nick_usuario = :nick_usuario "
			+ "WHERE login_usuario = :login_usuario";

	private static String GET_ALL_SQL = "SELECT nombre_usuario, apellidos_usuario, login_usuario, "
			+ "contrasinal_usuario, nick_usuario " + "FROM usuario ";

	private static String GET_SQL = GET_ALL_SQL
			+ "WHERE login_usuario = :login_usuario";

	private static String GET_BYNOMBRE_SQL = GET_ALL_SQL
			+ "WHERE nombre_usuario = :nombre_usuario";

	private static String DELETE_SQL = "DELETE FROM usuario WHERE login_usuario = :login_usuario";

	private static String DELETE_USERROLES_SQL = "DELETE FROM UserRoles WHERE login_usuario = :login_usuario";

	public Usuario insert(Usuario usuario) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("nombre_usuario", usuario.getNombre_usuario())
				.addValue("apellidos_usuario", usuario.getApellidos_usuario())
				.addValue("login_usuario", usuario.getLogin_usuario())
				.addValue("enabled", true)
				.addValue("contrasinal_usuario",
						usuario.getcontrasinal_usuario())
				.addValue("nick_usuario", usuario.getNick_usuario());

		jdbcTemplate.update(CREATE_SQL, params);
		insertRole(usuario.getLogin_usuario());

		return usuario;
	}

	public void insertRole(String login) {
		SqlParameterSource params2 = new MapSqlParameterSource().addValue(
				"login_usuario", login).addValue("role", "ROLE_AUTHENTICATED");

		jdbcTemplate.update(CREATE_USERROLES_SQL, params2);

	}

	public Usuario update(Usuario usuario) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("nombre_usuario", usuario.getNombre_usuario())
				.addValue("apellidos_usuario", usuario.getApellidos_usuario())
				.addValue("contrasinal_usuario",
						usuario.getcontrasinal_usuario())
				.addValue("nick_usuario", usuario.getNick_usuario())
				.addValue("login_usuario", usuario.getLogin_usuario());

		try {
			jdbcTemplate.update(UPDATE_SQL, params);
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}
		System.out.println("update DAO");
		return usuario;
	}

	public Usuario find(String login_usuario) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"login_usuario", login_usuario);
		try {
			return jdbcTemplate.queryForObject(GET_SQL, params,
					new UsuarioRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}
	}

	public List<Usuario> findByName(String nombre_usuario) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"nombre_usuario", nombre_usuario);

		return jdbcTemplate.query(GET_BYNOMBRE_SQL, params,
				new UsuarioRowMapper());
	}

	public List<Usuario> getAll() {
		return jdbcTemplate.query(GET_ALL_SQL, new UsuarioRowMapper());
	}

	public void remove(String login_usuario) throws InstanceNotFoundException {

		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"login_usuario", login_usuario);

		try {
			jdbcTemplate.update(DELETE_USERROLES_SQL, params);
		} catch (EmptyResultDataAccessException e) {
			throw new InstanceNotFoundException();
		}
		removeRole(login_usuario);
	}

	public void removeRole(String login_usuario) {

		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"login_usuario", login_usuario);
		jdbcTemplate.update(DELETE_SQL, params);

	}

}
