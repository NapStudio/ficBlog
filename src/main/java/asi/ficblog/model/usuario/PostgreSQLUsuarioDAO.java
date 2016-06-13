package asi.ficblog.model.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public class PostgreSQLUsuarioDAO implements UsuarioDAO {
	
	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static String CREATE_SQL = "INSERT INTO usuario (nombre_usuario, apellidos_usuario, login_usuario, contraseña_usuario, nick_usuario) "
			+ "VALUES (:nombre_usuario, :apellidos_usuario, :login_usuario, :contraseña_usuario, :nick_usuario)";

	private static String UPDATE_SQL = "UPDATE usuario SET nombre_usuario = :nombre_usuario, apellidos_usuario = :apellidos_usuario, "
			+ "contraseña_usuario = :contraseña_usuario, nick_usuario = :nick_usuario "
			+ "WHERE login_usuario = :login_usuario";

	private static String GET_SQL = "SELECT nombre_usuario, apellidos_usuario, login_usuario, contraseña_usuario, nick_usuario "
			+ "FROM usuario " + "WHERE login_usuario = :login_usuario";

	private static String GET_BYNOMBRE_SQL = "SELECT nombre_usuario, apellidos_usuario, login_usuario, contraseña_usuario, nick_usuario "
			+ "FROM usuario " + "WHERE nombre_usuario = :nombre_usuario";

	private static String DELETE_SQL = "DELETE FROM usuario WHERE login_usuario = :login_usuario";

	public Usuario insert(Usuario usuario) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("nombre_usuario", usuario.getNombre_usuario())
				.addValue("apellidos_usuario", usuario.getApellidos_usuario())
				.addValue("login_usuario", usuario.getLogin_usuario())
				.addValue("contraseña_usuario", usuario.getContraseña_usuario())
				.addValue("nick_usuario", usuario.getNick_usuario());

		jdbcTemplate.update(CREATE_SQL, params);

		return usuario;
	}

	public Usuario update(Usuario usuario) throws InstanceNotFoundException {
		// TODO modificar login??
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("nombre_usuario", usuario.getNombre_usuario())
				.addValue("apellidos_usuario", usuario.getApellidos_usuario())
				.addValue("contraseña_usuario", usuario.getContraseña_usuario())
				.addValue("login_usuario", usuario.getLogin_usuario())
				.addValue("nick_usuario", usuario.getNick_usuario());

		jdbcTemplate.update(UPDATE_SQL, params);

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

	public void remove(String login_usuario) throws InstanceNotFoundException {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"login_usuario", login_usuario);

		jdbcTemplate.update(DELETE_SQL, params);
	}

}
