package asi.ficblog.model.entrada;

import java.util.Date;

public class Entrada implements Comparable<Entrada>{
	
	private Date fecha_publicacion_entrada;
	private boolean me_gusta_entrada;

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
	
	public int compareTo(Entrada entrada) {
		return getFecha_publicacion_entrada().compareTo(entrada.getFecha_publicacion_entrada());
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
