package pe.edu.proyecto.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class UtilesVarios {

	public String obtnerFec(String fecha) {
		// String fecha = incidenteform.getFecha();
		String dia = fecha.substring(0, 2);
		String mes = fecha.substring(3, 5);
		String anho = fecha.substring(6, 10);
		String fec = anho + "-" + mes + "-" + dia;
		return fec;
	}

	public String enviarFec(String fecha) {
		// String fecha = incidenteform.getFecha();
		String dia = fecha.substring(8, 10);
		String mes = fecha.substring(5, 7);
		String anho = fecha.substring(0, 4);
		String fec = dia + "/" + mes + "/" + anho;
		return fec;
	}

	public int fechasDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(fechaInicial);
		try {
			fechaInicial = df.parse(fechaInicioString);
		} catch (ParseException ex) {
		}

		String fechaFinalString = df.format(fechaFinal);
		try {
			fechaFinal = df.parse(fechaFinalString);
		} catch (ParseException ex) {
		}

		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}
}
