package clases;

public class RankingAsistencia {

	private String dni;
	private String nombre;
	private String cantAsistencia;

	public RankingAsistencia(String dni, String nombre, Integer cantAsistencia) {
		this.dni = dni;
		this.nombre = nombre;
		this.cantAsistencia = cantAsistencia.toString();
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCantAsistencia() {
		return cantAsistencia;
	}
}
