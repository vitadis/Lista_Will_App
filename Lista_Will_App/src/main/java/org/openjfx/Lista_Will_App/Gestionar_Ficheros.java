package org.openjfx.Lista_Will_App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import clases.Asistencia;
import clases.Cargo;
import clases.Empleado;
import clases.Invitado;
import clases.Persona;
import clases.Seccion;
import clases.TipoInvitado;

public class Gestionar_Ficheros {

	// leer fichero
	@SuppressWarnings("exports")
	public static ArrayList<Persona> leerFicheroPersona(File archivo) {

		ArrayList<Persona> lista = new ArrayList<>();

		if (!archivo.exists()) {
			return lista;
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {

			while (true) {
				Persona p = (Persona) ois.readObject();
				lista.add(p);
			}

		} catch (EOFException e) {
			// Fin de fichero
		} catch (Exception e) {
			System.out.println("Error de lectura: " + e.getMessage());
		}

		return lista;
	}

	// ArrayList objetos separados
	@SuppressWarnings("exports")
	public static ArrayList<Empleado> listEmpleado(File archivo) {
		ArrayList<Persona> lista = leerFicheroPersona(archivo);
		ArrayList<Empleado> listEmp = new ArrayList<Empleado>();
		for (Persona p : lista) {
			if (p instanceof Empleado) {
				Empleado e = (Empleado) p;
				listEmp.add(e);
			}
		}
		return listEmp;
	}

	@SuppressWarnings("exports")
	public static ArrayList<Invitado> listInvitado(File archivo) {
		ArrayList<Persona> lista = leerFicheroPersona(archivo);
		ArrayList<Invitado> listaIn = new ArrayList<Invitado>();
		for (Persona p : lista) {
			if (p instanceof Invitado) {
				Invitado i = (Invitado) p;
				listaIn.add(i);
			}
		}
		return listaIn;
	}

	// sobreescribir archivo
	public static void sobreEscribirPersona(@SuppressWarnings("exports") ArrayList<Persona> array, File archivo) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {

			for (Persona p : array) {
				oos.writeObject(p);
			}

		} catch (Exception e) {
			System.out.println("Error de escritura: " + e.getMessage());
		}
	}

	// sobreescribir secciones
	public static void sobreEscribirSec() {
		ArrayList<Seccion> secciones = new ArrayList<Seccion>();
		secciones.add(new Seccion("BUCEO", "buceo.com"));
		secciones.add(new Seccion("BICICLETA", "bicicleta.com"));
		secciones.add(new Seccion("PARACAIDAS", "paracaidas.com"));
		secciones.add(new Seccion("FIESTA-JOVEN", "fiestaJoven.com"));

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("secciones.csv"))) {

			bw.write("codigo,nombre,url");
			bw.newLine();

			for (Seccion c : secciones) {
				bw.write(c.toCsv());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void inicializarDatos() {
		File filePer = new File("persona.dat");

		ArrayList<Persona> personas = new ArrayList<>();

		// Crear empleado administrador
		Empleado admin = new Empleado("11111111A", "admin", LocalDate.of(1990, 1, 1), "admin@listawill.com",
				"688788883", Cargo.ADMINISTRADOR, true, "admin123");
		personas.add(admin);

		// Crear otro empleado administrador
		Empleado admin2 = new Empleado("22222222B", "Will", LocalDate.of(1985, 5, 15), "will@listawill.com",
				"632726578", Cargo.ADMINISTRADOR, true, "will2026");
		personas.add(admin2);

		Empleado admin3 = new Empleado("12121212S", "P Diddy", LocalDate.of(1969, 11, 4), "Diddy@listawill.com",
				"614597843", Cargo.ADMINISTRADOR, true, "Diddy2026");
		personas.add(admin3);

		// Crear algunos invitados de prueba con asistencias
		ArrayList<Asistencia> asistencias1 = new ArrayList<>();
		ArrayList<String> secciones1 = new ArrayList<>();
		secciones1.add("BUCEO");
		secciones1.add("BICICLETA");
		asistencias1.add(new Asistencia("AST001", LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 15), 5, secciones1));

		Invitado inv1 = new Invitado("12345678A", "Juan Pérez García", LocalDate.of(1995, 3, 20),
				"juan.perez@example.com", "666123456", TipoInvitado.VIP, asistencias1);
		personas.add(inv1);

		// Invitado con múltiples asistencias
		ArrayList<Asistencia> asistencias2 = new ArrayList<>();
		ArrayList<String> secciones2 = new ArrayList<>();
		secciones2.add("PARACAIDAS");
		asistencias2.add(new Asistencia("AST002", LocalDate.of(2024, 7, 10), LocalDate.of(2024, 7, 20), 4, secciones2));

		ArrayList<String> secciones3 = new ArrayList<>();
		secciones3.add("FIESTA-JOVEN");
		asistencias2.add(new Asistencia("AST003", LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 5), 5, secciones3));

		Invitado inv2 = new Invitado("87654321B", "María López Martínez", LocalDate.of(1992, 8, 15),
				"maria.lopez@example.com", "666987654", TipoInvitado.TRIPLE_VIP, asistencias2);
		personas.add(inv2);

		// Invitado sin asistencias
		Invitado inv3 = new Invitado("11223344C", "Carlos Rodríguez Sánchez", LocalDate.of(1998, 11, 30),
				"carlos.rodriguez@example.com", "666456789", TipoInvitado.TRIPLE_VIP, new ArrayList<Asistencia>());
		personas.add(inv3);

		// Invitado VIP con asistencia reciente
		ArrayList<Asistencia> asistencias4 = new ArrayList<>();
		ArrayList<String> secciones4 = new ArrayList<>();
		secciones4.add("BUCEO");
		secciones4.add("BICICLETA");
		secciones4.add("PARACAIDAS");
		asistencias4.add(new Asistencia("AST004", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 1, 25), 5, secciones4));

		Invitado inv4 = new Invitado("99887766D", "Ana Fernández Torres", LocalDate.of(1988, 4, 12),
				"ana.fernandez@example.com", "666321654", TipoInvitado.VIP, asistencias4);
		personas.add(inv4);
		
		ArrayList<Asistencia> asistencias5 = new ArrayList<>();
		ArrayList<String> secciones5 = new ArrayList<>();
		secciones5.add("FIESTA-JOVEN");
		asistencias5.add(new Asistencia("AST005", LocalDate.of(2026, 2, 14), LocalDate.of(2024, 12, 5), 5, secciones5));
		
		Invitado inv5 = new Invitado("74753964B", "Aitor Gartzia Espina", LocalDate.of(2007, 9, 6),
				"jonasSexum@example.com", "688739716", TipoInvitado.VIP, asistencias5);
		personas.add(inv5);
		
		ArrayList<Asistencia> asistencias6 = new ArrayList<>();
		ArrayList<String> secciones6 = new ArrayList<>();
		secciones6.add("FIESTA-JOVEN");
		asistencias6.add(new Asistencia("AST006", LocalDate.of(2026, 2, 14), LocalDate.of(2024, 12, 5), 5, secciones6));
		
		Invitado inv6 = new Invitado("16161616D", "Joel Mamani Flores", LocalDate.of(2150, 8, 22),
				"srMamani@example.com", "632726578", TipoInvitado.VIP, asistencias6);
		personas.add(inv6);
		
		ArrayList<Asistencia> asistencias7 = new ArrayList<>();
		ArrayList<String> secciones7 = new ArrayList<>();
		secciones7.add("FIESTA-JOVEN");
		asistencias7.add(new Asistencia("AST007", LocalDate.of(2026, 2, 14), LocalDate.of(2024, 12, 5), 5, secciones7));
		
		Invitado inv7 = new Invitado("02020202S", "Jair Mamani Turpo", LocalDate.of(1450, 5, 6),
				"mgm@example.com", "631406664", TipoInvitado.VIP, asistencias7);
		personas.add(inv7);
		
		ArrayList<Asistencia> asistencias8 = new ArrayList<>();
		ArrayList<String> secciones8 = new ArrayList<>();
		secciones8.add("FIESTA-JOVEN");
		asistencias8.add(new Asistencia("AST008", LocalDate.of(2026, 2, 14), LocalDate.of(2024, 12, 5), 5, secciones8));
		
		Invitado inv8 = new Invitado("96969696C", "Cristiano Ronaldo dos Santos Aveiro", LocalDate.of(2002, 8, 14),
				"cristianoReal@example.com", "689456123", TipoInvitado.TRIPLE_VIP, asistencias8);
		personas.add(inv8);
		
		ArrayList<Asistencia> asistencias9 = new ArrayList<>();
		ArrayList<String> secciones9 = new ArrayList<>();
		secciones9.add("FIESTA-JOVEN");
		asistencias9.add(new Asistencia("AST009", LocalDate.of(2026, 2, 14), LocalDate.of(2024, 12, 5), 5, secciones9));
		
		Invitado inv9 = new Invitado("11261126M", "Lionel Andrés Messi Cuccittini", LocalDate.of(2004, 10, 16),
				"messiReal@example.com", "687945123", TipoInvitado.TRIPLE_VIP, asistencias9);
		personas.add(inv9);

		// Guardar todos los datos
		Gestionar_Ficheros.sobreEscribirPersona(personas, filePer);

		System.out.println("Datos de prueba inicializados correctamente");
		System.out.println("Usuarios administradores creados:");
		System.out.println("  - Usuario: admin | Password: admin123");
		System.out.println("  - Usuario: Will | Password: will2026");
		System.out.println("  - Usuario: P Diddy | Password: Diddy2026");
		System.out.println("Invitados de prueba creados: 4");
	}

	// --------COMPROBAR EXISTENCIA DEL ARRAYLIST---------
	@SuppressWarnings("exports")
	public static int indiceALPEEmpleado(ArrayList<Persona> lista, String dni) {
		int indice = 0;
		for (Persona p : lista) {
			if (p instanceof Empleado && dni.equalsIgnoreCase(p.getDni())) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	@SuppressWarnings("exports")
	public static int indiceALPInvitado(ArrayList<Persona> lista, String dni) {
		int indice = 0;
		for (Persona p : lista) {
			if (p instanceof Invitado && dni.equalsIgnoreCase(p.getDni())) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	// comprobar existencia de los administradores
	public static boolean existeAdmin(File archivo, String nombre, String pw) {
		ArrayList<Persona> lista = leerFicheroPersona(archivo);

		for (Persona p : lista) {
			if (p instanceof Empleado) {
				Empleado e = (Empleado) p;
				if (e.getNombre().equalsIgnoreCase(nombre) && e.getPw().equals(pw)
						&& e.getCargo() == Cargo.ADMINISTRADOR) {
					return true;
				}
			}
		}
		return false;
	}

	// aplicar filtros
	@SuppressWarnings("exports")
	public static ArrayList<Invitado> filtrarInvitadosPorEdad(ArrayList<Invitado> lista, int edadMinima) {
		ArrayList<Invitado> filtrados = new ArrayList<>();

		for (Invitado i : lista) {
			int edad = calcularEdad(i.getFechaNac());
			if (edad >= edadMinima) {
				filtrados.add(i);
			}
		}
		return filtrados;
	}

	public static int calcularEdad(LocalDate fechaNac) {
		return Period.between(fechaNac, LocalDate.now()).getYears();
	}

	@SuppressWarnings("exports")
	public static ArrayList<Invitado> filtrarInvitadosEntreFechas(ArrayList<Invitado> lista, LocalDate fechaInicio,
			LocalDate fechaFin) {

		ArrayList<Invitado> filtrados = new ArrayList<>();

		for (Invitado i : lista) {
			LocalDate fechaNac = i.getFechaNac();

			if ((fechaNac.isEqual(fechaInicio) || fechaNac.isAfter(fechaInicio))
					&& (fechaNac.isEqual(fechaFin) || fechaNac.isBefore(fechaFin))) {

				filtrados.add(i);
			}
		}
		return filtrados;
	}

	// gestionar ficheros
	public static String actualizarPersonas() {
		File fOriginal = new File("persona.dat");
		File fAuxiliar = new File("persona.tmp");
		File fBackup = new File("persona.bak");

		// Proceso de sustitución y backup
		if (fOriginal.exists()) {
			if (fBackup.exists())
				fBackup.delete();
			if (fOriginal.renameTo(fBackup)) {
				fAuxiliar.renameTo(fOriginal);
				return "Backup creado y fichero actualizado.";
			}
		} else {
			// Si no existía original, el auxiliar simplemente se renombra
			fAuxiliar.renameTo(fOriginal);
			return "Primer fichero creado.";
		}
		return null;
	}

	/**
	 * Método técnico: Obtiene el ArrayList de secciones disponibles desde el archivo CSV

	 */
	@SuppressWarnings("exports")
	public static ArrayList<Seccion> obtenerSecciones() {
		ArrayList<Seccion> secciones = new ArrayList<>();
		File archivoSecciones = new File("secciones.csv");
		
		// Si el archivo no existe, crear uno con las secciones por defecto
		if (!archivoSecciones.exists()) {
			sobreEscribirSec();
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(archivoSecciones))) {
			String linea;
			boolean primeraLinea = true;
			
			while ((linea = br.readLine()) != null) {
				// Saltar la cabecera (primera línea)
				if (primeraLinea) {
					primeraLinea = false;
					continue;
				}
				
				// Parsear la línea CSV: codigo,nombre,url
				String[] partes = linea.split(",");
				if (partes.length >= 2) {
					String nombre = partes[1].trim();
					String url = partes.length >= 3 ? partes[2].trim() : "";
					secciones.add(new Seccion(nombre, url));
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de secciones: " + e.getMessage());
			// En caso de error, retornar secciones por defecto
			secciones.add(new Seccion("BUCEO", "buceo.com"));
			secciones.add(new Seccion("BICICLETA", "bicicleta.com"));
			secciones.add(new Seccion("PARACAIDAS", "paracaidas.com"));
			secciones.add(new Seccion("FIESTA-JOVEN", "fiestaJoven.com"));
		}
		
		return secciones;
	}

}
