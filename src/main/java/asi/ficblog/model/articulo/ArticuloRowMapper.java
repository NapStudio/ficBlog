package asi.ficblog.model.articulo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class ArticuloRowMapper implements RowMapper<Articulo> {

	public Articulo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Articulo articulo = new Articulo();
		
		// TODO Falta título y fecha
		articulo.setTitulo_entrada(rs.getString("titulo_articulo"));
		articulo.setMe_gusta_entrada(rs.getBoolean("me_gusta_articulo"));
		articulo.setFecha_publicacion_entrada(rs.getDate("fecha_publicacion_articulo"));
		articulo.setId_articulo(rs.getLong("id_articulo"));
		articulo.setTexto_articulo(rs.getString("texto_articulo"));
		articulo.setBlog_articulo(rs.getLong("blog_articulo"));
		
		return articulo;
	}

}
