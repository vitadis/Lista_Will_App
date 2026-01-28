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

	// persona a empleado
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

	// sobreescribir archivo
	public static void sobreEscribir(ArrayList<Persona> array, File archivo) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {

			for (Persona p : array) {
				oos.writeObject(p);
			}

		} catch (Exception e) {
			System.out.println("Error de escritura: " + e.getMessage());
		}
	}

}
