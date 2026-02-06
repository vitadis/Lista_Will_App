package org.openjfx.Lista_Will_App;

import java.time.LocalDate;
import java.util.*;

import clases.*;

public class GestionarEstadisticas {

	@SuppressWarnings("exports")
	public static ArrayList<RankingAsistencia> estadisticaInvitados(ArrayList<Invitado> invitados) {

		ArrayList<RankingAsistencia> ranking = new ArrayList<>();

		for (Invitado inv : invitados) {
			int cant = inv.getAsistencia() != null ? inv.getAsistencia().size() : 0;

			ranking.add(new RankingAsistencia(inv.getDni(), inv.getNombre(), cant));
		}

		for (int i = 0; i < ranking.size() - 1; i++) {
			for (int j = 0; j < ranking.size() - i - 1; j++) {

				int a = Integer.parseInt(ranking.get(j).getCantAsistencia());
				int b = Integer.parseInt(ranking.get(j + 1).getCantAsistencia());

				if (a < b) {
					RankingAsistencia aux = ranking.get(j);
					ranking.set(j, ranking.get(j + 1));
					ranking.set(j + 1, aux);
				}
			}
		}

		return ranking;
	}

	@SuppressWarnings("exports")
	public static ArrayList<RankingSeccion> estadisticaSecciones(ArrayList<Invitado> invitados) {

		ArrayList<String> secciones = new ArrayList<>();
		ArrayList<Integer> conteo = new ArrayList<>();
		int totalVisitas = 0;

		if (invitados != null) {
			for (Invitado inv : invitados) {

				ArrayList<Asistencia> asistencias = inv.getAsistencia();
				if (asistencias == null || asistencias.isEmpty())
					continue;

				for (Asistencia as : asistencias) {

					ArrayList<String> nombresSec = as.getNombre();
					if (nombresSec == null || nombresSec.isEmpty())
						continue;

					for (String s : nombresSec) {
						int idx = secciones.indexOf(s);
						if (idx == -1) {
							secciones.add(s);
							conteo.add(1);
						} else {
							conteo.set(idx, conteo.get(idx) + 1);
						}
						totalVisitas++;
					}
				}
			}
		}

		ArrayList<RankingSeccion> ranking = new ArrayList<>();
		if (totalVisitas == 0) {
			return ranking;
		}

		ArrayList<Double> porcentajes = new ArrayList<>();
		for (int i = 0; i < secciones.size(); i++) {
			double porcentaje = (conteo.get(i) * 100.0) / totalVisitas;
			ranking.add(new RankingSeccion(secciones.get(i), porcentaje));
			porcentajes.add(porcentaje);
		}

		for (int i = 0; i < ranking.size() - 1; i++) {
			for (int j = 0; j < ranking.size() - i - 1; j++) {

				if (porcentajes.get(j) < porcentajes.get(j + 1)) {

					RankingSeccion aux = ranking.get(j);
					ranking.set(j, ranking.get(j + 1));
					ranking.set(j + 1, aux);

					double auxP = porcentajes.get(j);
					porcentajes.set(j, porcentajes.get(j + 1));
					porcentajes.set(j + 1, auxP);
				}
			}
		}

		return ranking;
	}

	@SuppressWarnings("exports")
	public static ArrayList<RankingFechas> estadisticaFechasRango(ArrayList<Invitado> invitados) {

		ArrayList<String> fechas = new ArrayList<>();
		ArrayList<Integer> conteo = new ArrayList<>();

		if (invitados != null) {
			for (Invitado inv : invitados) {

				ArrayList<Asistencia> asistencias = inv.getAsistencia();
				if (asistencias == null || asistencias.isEmpty())
					continue;

				for (Asistencia as : asistencias) {

					if (as.getFechaIni() == null || as.getFechaFin() == null)
						continue;

					LocalDate inicio = as.getFechaIni();
					LocalDate fin = as.getFechaFin();

					// recorrer las fechas de inicio a fin
					for (LocalDate fecha = inicio; !fecha.isAfter(fin); fecha = fecha.plusDays(1)) {

						String fechaStr = fecha.toString();

						int idx = fechas.indexOf(fechaStr);
						if (idx == -1) {
							fechas.add(fechaStr);
							conteo.add(1);
						} else {
							conteo.set(idx, conteo.get(idx) + 1);
						}
					}
				}
			}
		}

		ArrayList<RankingFechas> ranking = new ArrayList<>();
		if (fechas.isEmpty())
			return ranking;

		ArrayList<Integer> conteoAux = new ArrayList<>();
		for (int i = 0; i < fechas.size(); i++) {
			ranking.add(new RankingFechas(fechas.get(i), conteo.get(i)));
			conteoAux.add(conteo.get(i));
		}

		// burbuja descendente
		for (int i = 0; i < ranking.size() - 1; i++) {
			for (int j = 0; j < ranking.size() - i - 1; j++) {

				if (conteoAux.get(j) < conteoAux.get(j + 1)) {
					RankingFechas aux = ranking.get(j);
					ranking.set(j, ranking.get(j + 1));
					ranking.set(j + 1, aux);

					int temp = conteoAux.get(j);
					conteoAux.set(j, conteoAux.get(j + 1));
					conteoAux.set(j + 1, temp);
				}
			}
		}

		return ranking;
	}

}
