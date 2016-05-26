package asi.ficblog.model.enlace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;

public class PostgreSQLEnlaceDAO implements EnlaceDAO {

	private DataSource dataSource;

	public Enlace find(Integer id_enlace) throws InstanceNotFoundException {
		Enlace enlace = null;

		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"SELECT titulo_enlace, fecha_publicacion_enlace, url_enlace, tipo_contenido_enlace, me_gusta_enlace, blog_enlace FROM enlace WHERE id_enlace = ?");
			statement.setInt(1, id_enlace);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String titulo_enlace = resultSet.getString(1);
				Date fecha_publicacion_enlace = resultSet.getTimestamp(2);
				String url_enlace = resultSet.getString(3);
				String tipo_contenido_enlace = resultSet.getString(4);
				Boolean me_gusta_enlace = resultSet.getBoolean(5);
				int blog_enlace = resultSet.getInt(6);

				enlace = new Enlace(id_enlace, titulo_enlace, fecha_publicacion_enlace, url_enlace,
						tipo_contenido_enlace, me_gusta_enlace, blog_enlace);

				connection.close();
			} else {

				connection.close();
				throw new InstanceNotFoundException();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return enlace;
	}

	public List<Enlace> findByBlog(int blog_enlace) throws InstanceNotFoundException {
		List<Enlace> list = new ArrayList<Enlace>();

		try {

			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id_enlace, titulo_enlace, fecha_publicacion_enlace, url_enlace, tipo_contenido_enlace, me_gusta_enlace, blog_enlace "
							+ "FROM enlace WHERE blog_enlace = ?");

			statement.setInt(1, blog_enlace);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				list.add(new Enlace(resultSet.getInt(1), // id_enlace
						resultSet.getString(2), // titulo_enlace
						resultSet.getTimestamp(3), // fecha_publicacion_enlace
						resultSet.getString(4), // url_enlace
						resultSet.getString(5), // tipo_contenido_enlace
						resultSet.getBoolean(6), // me_gusta_enlace
						resultSet.getInt(7))// blog_enlace
				);

			}

			connection.close();
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}

		return list;
	}

	public Enlace insert(Enlace enlace) {
		if (enlace == null)
			return null;

		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(enlace.getFecha_publicacion_enlace().getTime());
		enlace.setFecha_publicacion_enlace(sqlTimestamp);

		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO enlace (titulo_enlace, fecha_publicacion_enlace, url_enlace, tipo_contenido_enlace, me_gusta_enlace, blog_enlace) "
							+ "VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, enlace.getTitulo_enlace());
			statement.setTimestamp(2, sqlTimestamp);
			statement.setString(3, enlace.getUrl_enlace());
			statement.setString(4, enlace.getTipo_contenido_enlace());
			statement.setBoolean(5, enlace.isMe_gusta_entrada());
			statement.setInt(6, enlace.getBlog_enlace());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet != null && resultSet.next())
				enlace.setId_enlace(resultSet.getInt(1));

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return enlace;
	}

	public void update(Enlace enlace) {
		try {
			// TODO; preguntar se no update podese cambiar o ID;
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE enlace SET titulo_enlace = ?, fecha_publicacion_enlace = ?, url_enlace = ?, tipo_contenido_enlace = ?, me_gusta_enlace = ?, blog_enlace = ? "
							+ "WHERE id_enlace = ?");

			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(enlace.getFecha_publicacion_enlace().getTime());

			statement.setString(1, enlace.getTitulo_enlace());
			statement.setTimestamp(2, sqlTimestamp);
			statement.setString(3, enlace.getUrl_enlace());
			statement.setString(4, enlace.getTipo_contenido_enlace());
			statement.setBoolean(5, enlace.isMe_gusta_entrada());
			statement.setInt(6, enlace.getBlog_enlace());
			statement.setInt(7, enlace.getId_enlace());

			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void remove(Integer id_enlace) {
		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM enlace WHERE id_enlace = ?");
			statement.setInt(1, id_enlace);

			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void removeAll(int blog_enlace) {
		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM enlace WHERE blog_enlace = ?");
			statement.setInt(1, blog_enlace);
			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
