package asi.ficblog.model.blog;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import asi.ficblog.model.usuario.Usuario;

public class Blog {	
	
	private Long id_blog;
	private String titulo_blog;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_creacion_blog;
	private String usuario_blog;
	
	public Blog(){
		
	}
	

	public Blog(Long id_blog, String titulo_blog, Date fecha_creacion_blog, String usuario_blog) {
		super();
		this.id_blog = id_blog;
		this.titulo_blog = titulo_blog;
		this.fecha_creacion_blog = fecha_creacion_blog;
		this.usuario_blog = usuario_blog;
	}
	
	public Blog(String titulo_blog, Date fecha_creacion_blog, String usuario_blog) {
		super();
		this.titulo_blog = titulo_blog;
		this.fecha_creacion_blog = fecha_creacion_blog;
		this.usuario_blog = usuario_blog;
	}
		
	public Long getId_blog() {
		return id_blog;
	}
	public void setId_blog(Long id_blog) {
		this.id_blog = id_blog;
	}
	public String getTitulo_blog() {
		return titulo_blog;
	}
	public void setTitulo_blog(String titulo_blog) {
		this.titulo_blog = titulo_blog;
	}
	public Date getFecha_creacion_blog() {
		return fecha_creacion_blog;
	}
	public void setFecha_creacion_blog(Date fecha_creacion_blog) {
		this.fecha_creacion_blog = fecha_creacion_blog;
	}
	public String getUsuario_blog() {
		return usuario_blog;
	}
	public void setUsuario_blog(String usuario_blog) {
		this.usuario_blog = usuario_blog;
	}
	
	@Override
	public String toString() {
		return "Blog [id_blog=" + id_blog + ", titulo_blog=" + titulo_blog + ", fecha_creacion_blog="
				+ fecha_creacion_blog + ", usuario_blog=" + usuario_blog + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fecha_creacion_blog == null) ? 0 : fecha_creacion_blog
						.hashCode());
		result = prime * result + ((id_blog == null) ? 0 : id_blog.hashCode());
		result = prime * result
				+ ((titulo_blog == null) ? 0 : titulo_blog.hashCode());
		result = prime * result
				+ ((usuario_blog == null) ? 0 : usuario_blog.hashCode());
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
		Blog other = (Blog) obj;
		String fec1=fecha_creacion_blog.toString();
		String fec2=other.fecha_creacion_blog.toString();
		
		if (fecha_creacion_blog == null) {
			if (other.fecha_creacion_blog != null)
				return false;
		} else if (!fec1.equals(fec2))
			return false;
		if (id_blog == null) {
			if (other.id_blog != null)
				return false;
		} else if (!id_blog.equals(other.id_blog))
			return false;
		if (titulo_blog == null) {
			if (other.titulo_blog != null)
				return false;
		} else if (!titulo_blog.equals(other.titulo_blog))
			return false;
		if (usuario_blog == null) {
			if (other.usuario_blog != null)
				return false;
		} else if (!usuario_blog.equals(other.usuario_blog))
			return false;
		return true;
	}

	
	
	
}
