package asi.ficblog.model.articulo;

import java.util.Date;

import asi.ficblog.model.entrada.Entrada;

public class Articulo extends Entrada{
	
	private int id_articulo;
	private String titulo_articulo;
	private Date fecha_publicacion_articulo;
	private String texto_articulo;
	private int blog_articulo;
	
	
	public Articulo(int id_articulo, String titulo_articulo, Date fecha_publicacion_articulo, String texto_articulo, boolean me_gusta_articulo,
			int blog_articulo) {
		super(fecha_publicacion_articulo, me_gusta_articulo);
		this.id_articulo = id_articulo;
		this.titulo_articulo = titulo_articulo;
		this.fecha_publicacion_articulo = fecha_publicacion_articulo;
		this.texto_articulo = texto_articulo;
		this.blog_articulo = blog_articulo;
	}

	
	public Articulo(String titulo_articulo, Date fecha_publicacion_articulo, String texto_articulo,
			int blog_articulo) {
		super(fecha_publicacion_articulo);
		this.titulo_articulo = titulo_articulo;
		this.fecha_publicacion_articulo = fecha_publicacion_articulo;
		this.texto_articulo = texto_articulo;
		this.blog_articulo = blog_articulo;
	}

	public int getId_articulo() {
		return id_articulo;
	}


	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}


	public String getTitulo_articulo() {
		return titulo_articulo;
	}


	public void setTitulo_articulo(String titulo_articulo) {
		this.titulo_articulo = titulo_articulo;
	}


	public Date getFecha_publicacion_articulo() {
		return fecha_publicacion_articulo;
	}


	public void setFecha_publicacion_articulo(Date fecha_publicacion_articulo) {
		this.fecha_publicacion_articulo = fecha_publicacion_articulo;
	}


	public String getTexto_articulo() {
		return texto_articulo;
	}


	public void setTexto_articulo(String texto_articulo) {
		this.texto_articulo = texto_articulo;
	}


	public int getBlog_articulo() {
		return blog_articulo;
	}


	public void setBlog_articulo(int blog_articulo) {
		this.blog_articulo = blog_articulo;
	}


	@Override
	public String toString() {
		return "Articulo [id_articulo=" + id_articulo + ", titulo_articulo=" + titulo_articulo
				+ ", fecha_publicacion_articulo=" + fecha_publicacion_articulo + ", texto_articulo=" + texto_articulo
				+ ", blog_articulo=" + blog_articulo + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + blog_articulo;
		result = prime * result + ((fecha_publicacion_articulo == null) ? 0 : fecha_publicacion_articulo.hashCode());
		result = prime * result + id_articulo;
		result = prime * result + ((texto_articulo == null) ? 0 : texto_articulo.hashCode());
		result = prime * result + ((titulo_articulo == null) ? 0 : titulo_articulo.hashCode());
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
		Articulo other = (Articulo) obj;
		if (blog_articulo != other.blog_articulo)
			return false;
		if (fecha_publicacion_articulo == null) {
			if (other.fecha_publicacion_articulo != null)
				return false;
		} else if (!fecha_publicacion_articulo.equals(other.fecha_publicacion_articulo))
			return false;
		if (id_articulo != other.id_articulo)
			return false;
		if (texto_articulo == null) {
			if (other.texto_articulo != null)
				return false;
		} else if (!texto_articulo.equals(other.texto_articulo))
			return false;
		if (titulo_articulo == null) {
			if (other.titulo_articulo != null)
				return false;
		} else if (!titulo_articulo.equals(other.titulo_articulo))
			return false;
		return true;
	}
	
	
	
	
	
	

}
