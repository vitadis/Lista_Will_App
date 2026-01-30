package org.openjfx.Lista_Will_App;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.*;

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
	public static void sobreEscribirSec(File archivo) {
		ArrayList<Seccion> secciones = new ArrayList<Seccion>();
		secciones.add(new Seccion("BUCEO", "buceo.com"));
		secciones.add(new Seccion("BICICLETA", "bicicleta.com"));
		secciones.add(new Seccion("PARACAIDAS", "paracaidas.com"));
		secciones.add(new Seccion("FIESTA-JOVEN", "fiestaJoven.com"));

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
			for (Seccion s : secciones) {
				oos.writeObject(s);
			}

		} catch (Exception e) {
			System.out.println("Error de escritura: " + e.getMessage());
		}
	}
	// secciones

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

}
