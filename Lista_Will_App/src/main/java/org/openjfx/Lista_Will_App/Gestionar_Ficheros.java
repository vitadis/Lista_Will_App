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
	public static ArrayList<Persona> leerFichero(File archivo) {

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
