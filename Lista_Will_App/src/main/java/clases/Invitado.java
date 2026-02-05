package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Invitado extends Persona {
	
	private static final long serialVersionUID = 1L;
	private TipoInvitado tipo;
	private ArrayList<Asistencia> asistencia;
	
	public Invitado(String dni, String nombre, LocalDate fechaNac, String email, String telefono, TipoInvitado tipo,
			ArrayList<Asistencia> asistencia) {
		super(dni, nombre, fechaNac, email, telefono);
		this.tipo = tipo;
		this.asistencia = asistencia;
		// Mantener las asistencias ordenadas
		ordenarAsistencias();
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
		ordenarAsistencias();
	}
	
	/**
	 * Agrega una nueva asistencia y genera automáticamente su código
	 * basándose en el último código existente
	 * 
	 * @param nuevaAsistencia La asistencia a agregar (sin código)
	 */
	public void agregarAsistencia(Asistencia nuevaAsistencia) {
		// Generar el código automáticamente
		String ultimoCodigo = obtenerUltimoCodigo();
		String nuevoCodigo = Asistencia.generarSiguienteCodigo(ultimoCodigo);
		nuevaAsistencia.setCodigo(nuevoCodigo);
		
		// Agregar la asistencia
		this.asistencia.add(nuevaAsistencia);
		
		// Mantener ordenado
		ordenarAsistencias();
	}
	
	/**
	 * Obtiene el último código de asistencia de la lista ordenada
	 * 
	 * @return El último código o null si no hay asistencias
	 */
	private String obtenerUltimoCodigo() {
		if (asistencia == null || asistencia.isEmpty()) {
			return null;
		}
		
		// Como la lista está ordenada, el último elemento tiene el código más alto
		Collections.sort(asistencia);
		return asistencia.get(asistencia.size() - 1).getCodigo();
	}
	
	/**
	 * Ordena la lista de asistencias por código
	 */
	private void ordenarAsistencias() {
		if (asistencia != null && !asistencia.isEmpty()) {
			Collections.sort(asistencia);
		}
	}
	
	/**
	 * Convierte la lista de asistencias a un TreeMap ordenado por código
	 * (útil para operaciones que requieren acceso por clave)
	 * 
	 * @return TreeMap con las asistencias ordenadas por código
	 */
	public TreeMap<String, Asistencia> getAsistenciasComoTreeMap() {
		TreeMap<String, Asistencia> mapa = new TreeMap<>();
		if (asistencia != null) {
			for (Asistencia a : asistencia) {
				if (a.getCodigo() != null) {
					mapa.put(a.getCodigo(), a);
				}
			}
		}
		return mapa;
	}
	
	public String tipoPersona() {
		return "Invitado";
	}
	
	@Override
	public String toString() {
		return "Invitado [tipo=" + tipo + ", asistencia=" + asistencia + "]";
	}
}
