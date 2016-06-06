package asi.ficblog.model.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import asi.ficblog.model.util.exceptions.InstanceNotFoundException;


public class PostgreSQLUsuarioDAO implements UsuarioDAO{
	
	private DataSource dataSource;
	
	
	public Usuario find(String login_usuario) 
			throws InstanceNotFoundException {

		Usuario usuario = null;

		try {
			Connection connection = dataSource.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(
					"SELECT nombre_usuario, apellidos_usuario, contraseña_usuario, nick_usuario FROM usuario WHERE login_usuario = ?");
			statement.setString(1, login_usuario);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String nombre_usuario = resultSet.getString(1);
				String apellidos_usuario =  resultSet.getString(2);
				String contraseña_usuario =  resultSet.getString(3);
				String nick_usuario =  resultSet.getString(4);

				usuario = new Usuario(nombre_usuario, apellidos_usuario, login_usuario, contraseña_usuario, nick_usuario);

				connection.close();
			} else {
				connection.close();
				throw new InstanceNotFoundException();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return usuario;
	}
	
	public List<Usuario> findByName(String nombre_usuario) {
		
		List<Usuario> list = new ArrayList<Usuario>(); 
		
		try {
			
			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"SELECT nombre_usuario, apellidos_usuario, login_usuario, contraseña_usuario, nick_usuario " + 
					"FROM usuario WHERE nombre_usuario = ?");
			
			statement.setString(1, nombre_usuario);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				list.add(new Usuario(
						resultSet.getString(1),	// nombre
						resultSet.getString(2), // apellido
						resultSet.getString(3), // login
						resultSet.getString(4),	// contraseña
						resultSet.getString(5))	// nick
				);

			}

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		
		return list;
	}

	public Usuario insert(Usuario usuario) {
		
		if (usuario == null)
			return null;
		
		try {
			//TODO: ReturnGeneratedKeys?
			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO usuario (nombre_usuario, apellidos_usuario, login_usuario, contraseña_usuario, nick_usuario) " + 
					"VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, usuario.getNombre_usuario());
			statement.setString(2, usuario.getApellidos_usuario());
			statement.setString(3, usuario.getLogin_usuario());
			statement.setString(4, usuario.getContraseña_usuario());
			statement.setString(5, usuario.getNick_usuario());
			
			statement.executeUpdate();
			
			//ResultSet resultSet = statement.getGeneratedKeys();

			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return usuario;
	}
	
	public void update(Usuario usuario) throws InstanceNotFoundException {
		
		try {
			
			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"UPDATE usuario SET nombre_usuario = ?, apellidos_usuario = ?, contraseña_usuario = ?, nick_usuario = ? " + 
					"WHERE login_usuario = ?");
			
			statement.setString(1, usuario.getNombre_usuario());
			statement.setString(2, usuario.getApellidos_usuario());
			statement.setString(3, usuario.getContraseña_usuario());
			statement.setString(4, usuario.getNick_usuario());
			statement.setString(5, usuario.getLogin_usuario());
			
			int updatedRows=statement.executeUpdate();
			if(updatedRows==0){
				throw new InstanceNotFoundException();
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void remove(String login_usuario) throws InstanceNotFoundException {
		try {
			
			Connection connection = dataSource.getConnection();

			PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM usuario WHERE login_usuario = ?");
			statement.setString(1, login_usuario);
			
			int removedRows=statement.executeUpdate();
			if(removedRows==0){
				throw new InstanceNotFoundException();				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
