package clases;

import java.time.LocalDate;

public class Empleado extends Persona {
	private String codigo;
	private Cargo cargo;
	private boolean activo;

	public Empleado(String dni, String nombre, LocalDate fechaNac, String email, String telefono, String codigo,
			Cargo cargo, boolean activo) {
		super(dni, nombre, fechaNac, email, telefono);
		this.codigo = codigo;
		this.cargo = cargo;
		this.activo = activo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public int calcularEdad() {
		return 1;
	}

}
