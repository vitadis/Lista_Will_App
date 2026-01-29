package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Invitado extends Persona{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoInvitado tipo;
	private ArrayList<Asistencia> asistencia;
	
	public Invitado(String dni, String nombre, LocalDate fechaNac, String email, String telefono, TipoInvitado tipo,
			ArrayList<Asistencia> asistencia) {
		super(dni, nombre, fechaNac, email, telefono);
		this.tipo = tipo;
		this.asistencia = asistencia;
	}
	
	public TipoInvitado getTipo() {
		return tipo;
	}
	public void setTipo(TipoInvitado tipo) {
		this.tipo = tipo;
	}
	public ArrayList<Asistencia> getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(ArrayList<Asistencia> asistencia) {
		this.asistencia = asistencia;
	}
	@Override
	public int calcularEdad() {
		return 0;
	}
	
	
	@Override
	public String toString() {
		return "Invitado [tipo=" + tipo + ", asistencia=" + asistencia + "]";
	}

	
	
	
	
	

}
