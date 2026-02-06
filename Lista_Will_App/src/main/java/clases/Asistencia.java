package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Asistencia implements Serializable, Comparable<Asistencia> {

	private static final long serialVersionUID = 1L;
	private String codigo;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private ArrayList<String> nombre;
	private Integer valoracion;

	/**
	 * Constructor con código manual (para compatibilidad con datos existentes)
	 */
	public Asistencia(String codigo, LocalDate fechaIni, LocalDate fechaFin, Integer valoracion,
			ArrayList<String> codSecciones) {
		super();
		this.codigo = codigo;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.valoracion = valoracion;
		this.nombre = codSecciones;
	}

	/**
	 * Constructor sin código (se generará automáticamente)
	 */
	public Asistencia(LocalDate fechaIni, LocalDate fechaFin, Integer valoracion, ArrayList<String> codSecciones) {
		super();
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.valoracion = valoracion;
		this.nombre = codSecciones;
		// El código se asignará después mediante setCodigo()
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(LocalDate fechaIni) {
		this.fechaIni = fechaIni;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public ArrayList<String> getNombre() {
		return nombre;
	}

	public void setCodSecciones(ArrayList<String> getNombre) {
		this.nombre = getNombre;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	/**
	 * Genera el siguiente código basándose en el último código de una lista
	 * Formato: AST-NNNN (ej: AST-0001, AST-0002, etc.)
	 * 
	 * @param ultimoCodigo El último código existente (puede ser null si no hay asistencias)
	 * @return El siguiente código generado
	 */
	public static String generarSiguienteCodigo(String ultimoCodigo) {
		if (ultimoCodigo == null || ultimoCodigo.isEmpty()) {
			return "AST-0001";
		}

		try {
			// Separar el prefijo del número
			String[] partes = ultimoCodigo.split("-");
			if (partes.length != 2) {
				return "AST-0001";
			}

			// Obtener el número y sumarle 1
			int numeroActual = Integer.parseInt(partes[1]);
			int nuevoNumero = numeroActual + 1;

			// Formatear con 4 dígitos (0001, 0002, etc.)
			return String.format("AST-%04d", nuevoNumero);

		} catch (NumberFormatException e) {
			return "AST-0001";
		}
	}

	/**
	 * Implementación de Comparable para ordenar asistencias por código
	 */
	@Override
	public int compareTo(Asistencia otra) {
		if (this.codigo == null && otra.codigo == null) return 0;
		if (this.codigo == null) return -1;
		if (otra.codigo == null) return 1;
		return this.codigo.compareTo(otra.codigo);
	}

	@Override
	public String toString() {
		return "Asistencia [codigo=" + codigo + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + ", valoracion="
				+ valoracion + "]";
	}
}
