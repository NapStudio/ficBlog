package asi.ficblog.model.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UsuarioRowMapper implements RowMapper<Usuario> {

	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre_usuario(rs.getString("nombre_usuario"));
		usuario.setApellidos_usuario(rs.getString("apellidos_usuario"));
		usuario.setLogin_usuario(rs.getString("login_usuario"));
		usuario.setContraseña_usuario(rs.getString("contraseña_usuario"));
		usuario.setNick_usuario(rs.getString("nick_usuario"));
		
		return usuario;
	}

}
