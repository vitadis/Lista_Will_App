package clases;

import java.time.LocalDate;

public class Asistencia {
	private String codigo;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private Integer valocaion;

	public Asistencia(String codigo, LocalDate fechaIni, LocalDate fechaFin, Integer valocaion) {
		super();
		this.codigo = codigo;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.valocaion = valocaion;
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

	public Integer getValocaion() {
		return valocaion;
	}

	public void setValocaion(Integer valocaion) {
		this.valocaion = valocaion;
	}

	@Override
	public String toString() {
		return "Asistencia [codigo=" + codigo + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + ", valocaion="
				+ valocaion + "]";
	}

}
