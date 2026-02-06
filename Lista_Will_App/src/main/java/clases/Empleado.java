package clases;

import java.time.LocalDate;

public class Empleado extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cargo cargo;
	private boolean activo;
	private String pw;

	public Empleado(String dni, String nombre, LocalDate fechaNac, String email, String telefono, Cargo cargo,
			boolean activo) {
		super(dni, nombre, fechaNac, email, telefono);
		this.cargo = cargo;
		this.activo = activo;
	}

	public Empleado(String dni, String nombre, LocalDate fechaNac, String email, String telefono, Cargo cargo,
			boolean activo, String pw) {
		super(dni, nombre, fechaNac, email, telefono);
		this.cargo = cargo;
		this.activo = activo;
		this.pw = pw;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String tipoPersona() {
		return "Empleado";
	}

	@Override
	public String toString() {
		return "Empleado [cargo=" + cargo + ", activo=" + activo + ", pw=" + pw + "]";
	}
}
