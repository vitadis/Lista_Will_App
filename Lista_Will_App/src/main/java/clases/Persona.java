package clases;

import java.time.LocalDate;

public abstract class Persona {

	private String dni;
	private String nombre;
	private LocalDate fechaNac;
	private String email;
	private String telefono; // vamos a controlar el sufijo y num

	public Persona(String dni, String nombre, LocalDate fechaNac, String email, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.email = email;
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", email=" + email
				+ ", telefono=" + telefono + "]";
	}

	public abstract int calcularEdad();

}
