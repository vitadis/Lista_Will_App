package clases;

public class RankingFechas {
	private String fecha;
	private String cantidad;

	public RankingFechas(String fecha, int cantidad) {
		this.fecha = fecha;
		this.cantidad = Integer.toString(cantidad);
	}

	public String getFecha() {
		return fecha;
	}

	public String getCantidad() {
		return cantidad;
	}
}
