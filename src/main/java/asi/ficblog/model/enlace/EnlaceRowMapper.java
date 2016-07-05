package asi.ficblog.model.enlace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.jdbc.core.RowMapper;

public class EnlaceRowMapper implements RowMapper<Enlace> {

	public Enlace mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Enlace enlace = new Enlace();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(rs.getTimestamp("fecha_publicacion_enlace"));
		enlace.setId_enlace(rs.getLong("id_enlace"));
		enlace.setTitulo_entrada(rs.getString("titulo_enlace"));
		enlace.setFecha_publicacion_entrada(calendar);
		enlace.setUrl_enlace(rs.getString("url_enlace"));
		enlace.setMe_gusta_entrada(rs.getInt("me_gusta_enlace"));
		enlace.setTipo_contenido_enlace(rs.getString("tipo_contenido_enlace"));
		enlace.setBlog_enlace(rs.getLong("blog_enlace"));
		
		return enlace;
	}


}
