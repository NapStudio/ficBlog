package asi.ficblog.model.enlace;

import java.util.Date;

import asi.ficblog.model.entrada.Entrada;

public class Enlace extends Entrada{
	
	private int id_enlace;
	private String titulo_enlace;
	private Date fecha_publicacion_enlace;
	private String url_enlace;
	private String tipo_contenido_enlace;
	private int blog_enlace;
	
	

	public Enlace(int id_enlace, String titulo_enlace, Date fecha_publicacion_enlace, String url_enlace,
			String tipo_contenido, boolean me_gusta_enlace, int blog_enlace) {
		super(fecha_publicacion_enlace, me_gusta_enlace);
		this.id_enlace = id_enlace;
		this.titulo_enlace = titulo_enlace;
		this.fecha_publicacion_enlace = fecha_publicacion_enlace;
		this.url_enlace = url_enlace;
		this.tipo_contenido_enlace = tipo_contenido;
		this.blog_enlace = blog_enlace;
	}
	
	public Enlace(String titulo_enlace, Date fecha_publicacion_enlace, String url_enlace,
			String tipo_contenido, int blog_enlace) {
		super(fecha_publicacion_enlace);
		this.titulo_enlace = titulo_enlace;
		this.fecha_publicacion_enlace = fecha_publicacion_enlace;
		this.url_enlace = url_enlace;
		this.tipo_contenido_enlace = tipo_contenido;
		this.blog_enlace = blog_enlace;
	}


	public int getBlog_enlace() {
		return blog_enlace;
	}

	public void setBlog_enlace(int blog_enlace) {
		this.blog_enlace = blog_enlace;
	}

	public int getId_enlace() {
		return id_enlace;
	}

	public void setId_enlace(int id_enlace) {
		this.id_enlace = id_enlace;
	}

	public String getTitulo_enlace() {
		return titulo_enlace;
	}

	public void setTitulo_enlace(String titulo_enlace) {
		this.titulo_enlace = titulo_enlace;
	}

	public Date getFecha_publicacion_enlace() {
		return fecha_publicacion_enlace;
	}

	public void setFecha_publicacion_enlace(Date fecha_publicacion_enlace) {
		this.fecha_publicacion_enlace = fecha_publicacion_enlace;
	}

	public String getUrl_enlace() {
		return url_enlace;
	}

	public void setUrl_enlace(String url_enlace) {
		this.url_enlace = url_enlace;
	}
	public String getTipo_contenido_enlace() {
		return tipo_contenido_enlace;
	}

	public void setTipo_contenido_enlace(String tipo_contenido_enlace) {
		this.tipo_contenido_enlace = tipo_contenido_enlace;
	}

	@Override
	public String toString() {
		return "Enlace [id_enlace=" + id_enlace + ", titulo_enlace=" + titulo_enlace + ", fecha_publicacion_enlace="
				+ fecha_publicacion_enlace + ", url_enlace=" + url_enlace + ", tipo_contenido_enlace=" + tipo_contenido_enlace
				+ ", blog_enlace=" + blog_enlace + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + blog_enlace;
		result = prime * result + ((fecha_publicacion_enlace == null) ? 0 : fecha_publicacion_enlace.hashCode());
		result = prime * result + id_enlace;
		result = prime * result + ((tipo_contenido_enlace == null) ? 0 : tipo_contenido_enlace.hashCode());
		result = prime * result + ((titulo_enlace == null) ? 0 : titulo_enlace.hashCode());
		result = prime * result + ((url_enlace == null) ? 0 : url_enlace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enlace other = (Enlace) obj;
		if (blog_enlace != other.blog_enlace)
			return false;
		if (fecha_publicacion_enlace == null) {
			if (other.fecha_publicacion_enlace != null)
				return false;
		} else if (!fecha_publicacion_enlace.equals(other.fecha_publicacion_enlace))
			return false;
		if (id_enlace != other.id_enlace)
			return false;
		if (tipo_contenido_enlace == null) {
			if (other.tipo_contenido_enlace != null)
				return false;
		} else if (!tipo_contenido_enlace.equals(other.tipo_contenido_enlace))
			return false;
		if (titulo_enlace == null) {
			if (other.titulo_enlace != null)
				return false;
		} else if (!titulo_enlace.equals(other.titulo_enlace))
			return false;
		if (url_enlace == null) {
			if (other.url_enlace != null)
				return false;
		} else if (!url_enlace.equals(other.url_enlace))
			return false;
		return true;
	}
	
	

}
