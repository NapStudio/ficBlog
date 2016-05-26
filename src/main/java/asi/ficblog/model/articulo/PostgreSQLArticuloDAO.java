package asi.ficblog.model.articulo;

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

public class PostgreSQLArticuloDAO implements ArticuloDAO {

	private DataSource dataSource;

	public Articulo find(Integer id_articulo) throws InstanceNotFoundException {
		Articulo articulo = null;

		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"SELECT titulo_articulo, fecha_publicacion_articulo, texto_articulo, me_gusta_articulo, blog_articulo FROM articulo WHERE id_articulo = ?");
			statement.setInt(1, id_articulo);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String titulo_articulo = resultSet.getString(1);
				Date fecha_publicacion_articulo = resultSet.getTimestamp(2);
				String texto_articulo = resultSet.getString(3);
				boolean me_gusta_articulo=resultSet.getBoolean(4);
				int blog_articulo = resultSet.getInt(5);

				articulo = new Articulo(id_articulo, titulo_articulo, fecha_publicacion_articulo, texto_articulo, me_gusta_articulo,
						blog_articulo);
			} else {
				connection.close();
				throw new InstanceNotFoundException();
			}
			connection.close();

		} catch (SQLException e) {	
			throw new RuntimeException(e);
		}

		return articulo;
	}

	public List<Articulo> findByBlog(int blog_articulo) throws InstanceNotFoundException {
		List<Articulo> list = new ArrayList<Articulo>();

		try {

			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT id_articulo, titulo_articulo, fecha_publicacion_articulo, texto_articulo, me_gusta_articulo, blog_articulo "
							+ "FROM articulo WHERE blog_articulo = ?");

			statement.setInt(1, blog_articulo);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				list.add(new Articulo(resultSet.getInt(1), // id_articulo
						resultSet.getString(2), // titulo_articulo
						resultSet.getTimestamp(3), // fecha_publicacion_articulo
						resultSet.getString(4), // texto_articulo
						resultSet.getBoolean(5), // me_gusta_articulo
						resultSet.getInt(6))// blog_articulo
				);

			}

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public Articulo insert(Articulo articulo) {
		if (articulo == null)
			return null;
		
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(articulo.getFecha_publicacion_articulo().getTime());
		articulo.setFecha_publicacion_articulo(sqlTimestamp);
		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO articulo (titulo_articulo, fecha_publicacion_articulo, texto_articulo, me_gusta_articulo, blog_articulo ) "
							+ "VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, articulo.getTitulo_articulo());
			statement.setTimestamp(2, sqlTimestamp);
			statement.setString(3, articulo.getTexto_articulo());
			statement.setBoolean(4, articulo.isMe_gusta_entrada());
			statement.setInt(5, articulo.getBlog_articulo());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();


			if (resultSet != null && resultSet.next())
				articulo.setId_articulo(resultSet.getInt(1));
			

			connection.close();

		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}

		return articulo;
	}

	public void update(Articulo articulo) {
		try {

			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE articulo SET titulo_articulo = ?, fecha_publicacion_articulo = ?, texto_articulo = ?, me_gusta_articulo = ?, blog_articulo = ? "
							+ "WHERE id_articulo = ?");
			
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(articulo.getFecha_publicacion_articulo().getTime());
			articulo.setFecha_publicacion_articulo(sqlTimestamp);

			statement.setString(1, articulo.getTitulo_articulo());
			statement.setTimestamp(2, sqlTimestamp);
			statement.setString(3, articulo.getTexto_articulo());
			statement.setBoolean(4, articulo.isMe_gusta_entrada());
			statement.setInt(5, articulo.getBlog_articulo());
			statement.setInt(6, articulo.getId_articulo());

			statement.executeUpdate();


			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Integer id_articulo) {
		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM articulo WHERE id_articulo = ?");
			statement.setInt(1, id_articulo);

			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void removeAll(int blog_articulo) {
		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM articulo WHERE blog_articulo = ?");
			statement.setInt(1, blog_articulo);
			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
