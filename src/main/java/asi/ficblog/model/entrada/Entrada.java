package asi.ficblog.model.entrada;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Entrada implements Comparable<Entrada>{
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_publicacion_entrada;
	private boolean me_gusta_entrada;
	private String titulo_entrada;

	public Entrada(){
		
	}
	
	public Entrada(Date fecha_publicacion_entrada, boolean me_gusta_entrada) {
		super();
		this.fecha_publicacion_entrada = fecha_publicacion_entrada;
		this.me_gusta_entrada = me_gusta_entrada;
	}
	
	public Entrada(Date fecha_publicacion_entrada) {
		super();
		this.fecha_publicacion_entrada = fecha_publicacion_entrada;
	}

	public Entrada(Date fecha_publicacion_entrada, boolean me_gusta_entrada,
			String titulo_entrada) {
		super();
		this.fecha_publicacion_entrada = fecha_publicacion_entrada;
		this.me_gusta_entrada = me_gusta_entrada;
		this.titulo_entrada = titulo_entrada;
	}

	public Entrada(Date fecha_publicacion_entrada, String titulo_entrada) {
		super();
		this.fecha_publicacion_entrada = fecha_publicacion_entrada;
		this.titulo_entrada = titulo_entrada;
	}

	public String getTitulo_entrada() {
		return titulo_entrada;
	}

	public void setTitulo_entrada(String titulo_entrada) {
		this.titulo_entrada = titulo_entrada;
	}

	public boolean isMe_gusta_entrada() {
		return me_gusta_entrada;
	}

	public void setMe_gusta_entrada(boolean me_gusta_entrada) {
		this.me_gusta_entrada = me_gusta_entrada;
	}

	public Date getFecha_publicacion_entrada() {
		return fecha_publicacion_entrada;
	}

	public void setFecha_publicacion_entrada(Date fecha_publicacion_entrada) {
		this.fecha_publicacion_entrada = fecha_publicacion_entrada;
	}
	

	public int compareTo(Entrada other) {
		System.out.println("entrada: "+this);
		System.out.println("fecha: "+getFecha_publicacion_entrada());
	    if (getFecha_publicacion_entrada() == null) {
	    	System.out.println(1);
	        return (other.getFecha_publicacion_entrada() == null) ? 0 : -1;
	    }
	    if (other.getFecha_publicacion_entrada() == null) {
	    	System.out.println(2);
	        return 1;
	    }
	    if(other.getFecha_publicacion_entrada().before(getFecha_publicacion_entrada())){
	    	System.out.println(3);
	    	return -1;
	    }else if(other.getFecha_publicacion_entrada().after(getFecha_publicacion_entrada())){
	    	System.out.println(4);
	    	return 1;
	    }
    	System.out.println(5);
	    return 0;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha_publicacion_entrada == null) ? 0 : fecha_publicacion_entrada.hashCode());
		result = prime * result + (me_gusta_entrada ? 1231 : 1237);
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
		Entrada other = (Entrada) obj;
		if (fecha_publicacion_entrada == null) {
			if (other.fecha_publicacion_entrada != null)
				return false;
		} else if (!fecha_publicacion_entrada.equals(other.fecha_publicacion_entrada))
			return false;
		if (me_gusta_entrada != other.me_gusta_entrada)
			return false;
		return true;
	}
	
}
