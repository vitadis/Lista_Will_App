package clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Asistencia {
	private String codigo;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private ArrayList<String> codSecciones;
	private Integer valoracion;

	public Asistencia(String codigo, LocalDate fechaIni, LocalDate fechaFin, Integer valoracion,
			ArrayList<String> codSecciones) {
		super();
		this.codigo = codigo;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.valoracion = valoracion;
		this.codSecciones = codSecciones;
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

	public ArrayList<String> getCodSecciones() {
		return codSecciones;
	}

	public void setCodSecciones(ArrayList<String> codSecciones) {
		this.codSecciones = codSecciones;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valocaion) {
		this.valoracion = valocaion;
	}

	@Override
	public String toString() {
		return "Asistencia [codigo=" + codigo + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + ", valocaion="
				+ valoracion + "]";
	}

}
