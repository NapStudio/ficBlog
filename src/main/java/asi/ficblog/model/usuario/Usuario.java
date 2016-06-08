package asi.ficblog.model.usuario;

public class Usuario {	

	private String nombre_usuario;
	private String apellidos_usuario;
	private String login_usuario;
	private String contraseña_usuario;
	private String nick_usuario;
	
	public Usuario(){
		
	}
	
	public Usuario(String nombre_usuario, String apellidos_usuario, String login_usuario, String contraseña_usuario,
			String nick_usuario) {
		this.nombre_usuario = nombre_usuario;
		this.apellidos_usuario = apellidos_usuario;
		this.login_usuario = login_usuario;
		this.contraseña_usuario = contraseña_usuario;
		this.nick_usuario = nick_usuario;
	}	
	
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getApellidos_usuario() {
		return apellidos_usuario;
	}
	public void setApellidos_usuario(String apellidos_usuario) {
		this.apellidos_usuario = apellidos_usuario;
	}
	public String getLogin_usuario() {
		return login_usuario;
	}
	public void setLogin_usuario(String login_usuario) {
		this.login_usuario = login_usuario;
	}
	public String getContraseña_usuario() {
		return contraseña_usuario;
	}
	public void setContraseña_usuario(String contraseña_usuario) {
		this.contraseña_usuario = contraseña_usuario;
	}
	public String getNick_usuario() {
		return nick_usuario;
	}
	public void setNick_usuario(String nick_usuario) {
		this.nick_usuario = nick_usuario;
	}
	
	@Override
	public String toString() {
		return "Usuario [nombre_usuario=" + nombre_usuario + ", apellidos_usuario=" + apellidos_usuario
				+ ", login_usuario=" + login_usuario + ", contraseña_usuario=" + contraseña_usuario + ", nick_usuario="
				+ nick_usuario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos_usuario == null) ? 0 : apellidos_usuario.hashCode());
		result = prime * result + ((contraseña_usuario == null) ? 0 : contraseña_usuario.hashCode());
		result = prime * result + ((login_usuario == null) ? 0 : login_usuario.hashCode());
		result = prime * result + ((nick_usuario == null) ? 0 : nick_usuario.hashCode());
		result = prime * result + ((nombre_usuario == null) ? 0 : nombre_usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellidos_usuario == null) {
			if (other.apellidos_usuario != null)
				return false;
		} else if (!apellidos_usuario.equals(other.apellidos_usuario))
			return false;
		if (contraseña_usuario == null) {
			if (other.contraseña_usuario != null)
				return false;
		} else if (!contraseña_usuario.equals(other.contraseña_usuario))
			return false;
		if (login_usuario == null) {
			if (other.login_usuario != null)
				return false;
		} else if (!login_usuario.equals(other.login_usuario))
			return false;
		if (nick_usuario == null) {
			if (other.nick_usuario != null)
				return false;
		} else if (!nick_usuario.equals(other.nick_usuario))
			return false;
		if (nombre_usuario == null) {
			if (other.nombre_usuario != null)
				return false;
		} else if (!nombre_usuario.equals(other.nombre_usuario))
			return false;
		return true;
	}
	

}
