package clases;

public class Seccion {
	
	private String codigo;
	private String nombre;
	private String url;
	
	private static int contador=0;

	public Seccion(String nombre, String url) {
		super();
		this.codigo = generarCod(nombre);
		this.nombre = nombre;
		this.url = url;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipSec() {
		return nombre;
	}
	public void setTipSec(String nombre) {
		this.nombre = nombre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private static String generarCod(String nombre) {
		contador++;

		String cod = nombre.substring(0,3)+"-"+contador;
		return cod;
	}

	@Override
	public String toString() {
		return "Seccion [codigo=" + codigo + ", nombre=" + nombre + ", url=" + url + "]";
	}	
}
