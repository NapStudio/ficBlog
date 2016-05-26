package asi.ficblog.model.blog;

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

public class PostgreSQLBlogDAO implements BlogDAO {

	private DataSource dataSource;

	public Blog find(Integer id_blog) throws InstanceNotFoundException {

		Blog blog = null;

		try {
			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"SELECT titulo_blog, fecha_creacion_blog, usuario_blog FROM blog WHERE id_blog = ?");
			statement.setInt(1, id_blog);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String titulo_blog = resultSet.getString(1);
				Date fecha_creacion_blog = resultSet.getTimestamp(2);
				String usuario_blog = resultSet.getString(3);

				blog = new Blog(id_blog, titulo_blog, fecha_creacion_blog, usuario_blog);

				connection.close();
			} else {
				connection.close();
				throw new InstanceNotFoundException();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return blog;
	}

	public List<Blog> findByUsuario(String usuario) throws InstanceNotFoundException {
		List<Blog> list = new ArrayList<Blog>();

		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection
					.prepareStatement("SELECT id_blog, titulo_blog, fecha_creacion_blog, usuario_blog "
							+ "FROM blog WHERE usuario_blog = ?");

			statement.setString(1, usuario);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				list.add(new Blog(resultSet.getInt(1), // id_blog
						resultSet.getString(2), // titulo_blog
						resultSet.getTimestamp(3), // fecha_creacion_blog
						resultSet.getString(4)) // usuario_blog
				);

			}
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;
	}

	public Blog insert(Blog blog) {

		if (blog == null)
			return null;

		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO blog (titulo_blog, fecha_creacion_blog, usuario_blog) " + "VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(blog.getFecha_creacion_blog().getTime());
			
			statement.setString(1, blog.getTitulo_blog());
			statement.setTimestamp(2, sqlTimestamp);
			statement.setString(3, blog.getUsuario_blog());

			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet != null && resultSet.next())
				blog.setId_blog(resultSet.getInt(1));

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return blog;
	}

	public void update(Blog blog) {
		try {
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(blog.getFecha_creacion_blog().getTime());
			
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE blog SET titulo_blog = ?, fecha_creacion_blog = ?, usuario_blog = ? "
							+ "WHERE id_blog = ?");

			statement.setString(1, blog.getTitulo_blog());
			statement.setTimestamp(2, sqlTimestamp);
			statement.setString(3, blog.getUsuario_blog());
			statement.setInt(4, blog.getId_blog());

			statement.executeUpdate();

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Integer id_blog) {
		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM blog WHERE id_blog = ?");
			statement.setInt(1, id_blog);

			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Blog> getAll() {
		List<Blog> list = new ArrayList<Blog>();

		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection
					.prepareStatement("SELECT id_blog, titulo_blog, fecha_creacion_blog, usuario_blog " + "FROM blog");

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				list.add(new Blog(resultSet.getInt(1), // id_blog
						resultSet.getString(2), // titulo_blog
						resultSet.getTimestamp(3), // fecha_creacion_blog
						resultSet.getString(4)) // usuario_blog
				);

			}
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return list;

	}

	public void removeAll() {
		try {

			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement("DELETE FROM blog");

			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
