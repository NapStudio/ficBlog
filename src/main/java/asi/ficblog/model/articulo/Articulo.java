package asi.ficblog.model.articulo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import asi.ficblog.model.entrada.Entrada;

public class Articulo extends Entrada{
	
	private Long id_articulo;
	private String texto_articulo;
	private Long blog_articulo;
	
	public Articulo(){
		
	}
	
	
	public Articulo(Long id_articulo, String titulo_articulo, Date fecha_publicacion_articulo, String texto_articulo, boolean me_gusta_articulo,
			Long blog_articulo) {
		super(fecha_publicacion_articulo, me_gusta_articulo, titulo_articulo);
		this.id_articulo = id_articulo;
		this.texto_articulo = texto_articulo;
		this.blog_articulo = blog_articulo;
	}

	
	public Articulo(String titulo_articulo, Date fecha_publicacion_articulo, String texto_articulo,
			Long blog_articulo) {
		super(fecha_publicacion_articulo, titulo_articulo);
		this.texto_articulo = texto_articulo;
		this.blog_articulo = blog_articulo;
	}

	public long getId_articulo() {
		return id_articulo;
	}


	public void setId_articulo(Long id_articulo) {
		this.id_articulo = id_articulo;
	}

	public String getTexto_articulo() {
		return texto_articulo;
	}


	public void setTexto_articulo(String texto_articulo) {
		this.texto_articulo = texto_articulo;
	}


	public Long getBlog_articulo() {
		return blog_articulo;
	}


	public void setBlog_articulo(Long blog_articulo) {
		this.blog_articulo = blog_articulo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((blog_articulo == null) ? 0 : blog_articulo.hashCode());
		result = prime * result
				+ ((id_articulo == null) ? 0 : id_articulo.hashCode());
		result = prime * result
				+ ((texto_articulo == null) ? 0 : texto_articulo.hashCode());
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
		if (blog_articulo == null) {
			if (other.blog_articulo != null)
				return false;
		} else if (!blog_articulo.equals(other.blog_articulo))
			return false;
		if (id_articulo == null) {
			if (other.id_articulo != null)
				return false;
		} else if (!id_articulo.equals(other.id_articulo))
			return false;
		if (texto_articulo == null) {
			if (other.texto_articulo != null)
				return false;
		} else if (!texto_articulo.equals(other.texto_articulo))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Articulo [id_articulo=" + id_articulo + ", texto_articulo="
				+ texto_articulo + ", blog_articulo=" + blog_articulo
				+ ", getTitulo_entrada()=" + getTitulo_entrada()
				+ ", isMe_gusta_entrada()=" + isMe_gusta_entrada()
				+ ", getFecha_publicacion_entrada()="
				+ getFecha_publicacion_entrada() + "]";
	}






	
	

}
