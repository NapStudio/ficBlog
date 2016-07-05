package asi.ficblog.model.articulo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.jdbc.core.RowMapper;


public class ArticuloRowMapper implements RowMapper<Articulo> {

	public Articulo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Articulo articulo = new Articulo();
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(rs.getTimestamp("fecha_publicacion_articulo"));
		
		articulo.setTitulo_entrada(rs.getString("titulo_articulo"));
		articulo.setMe_gusta_entrada(rs.getInt("me_gusta_articulo"));
		articulo.setFecha_publicacion_entrada(calendar);
		articulo.setId_articulo(rs.getLong("id_articulo"));
		articulo.setTexto_articulo(rs.getString("texto_articulo"));
		articulo.setBlog_articulo(rs.getLong("blog_articulo"));
		
		return articulo;
	}

}
