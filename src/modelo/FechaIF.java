package modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import modelo.FechaInicioFinDelMes.Pair;


public class FechaIF {
	
	/*
	 * 
	 * ESTE METODO ESTATICO "PAIR" SE NUTRE DE LA CLASE FECHAINICIOFINDELMES.JAVA
	 * EL OBJETO "PAIR" NO ES UNA CLASE INCORPORADA EN JAVA ESTANDAR, POR LO QUE NECESITA
	 * SER DEFINIDA POR EL USUARIO.-
	 * 
	 * 
	 */
	 public static Pair<String, String> obtenerFechasInicioFin(int year, int month) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.YEAR, year);
	        calendar.set(Calendar.MONTH, month);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        calendar.set(Calendar.DAY_OF_MONTH, 1);
	        Date fechaDesde = calendar.getTime();
	        String fechaDesdeStr = dateFormat.format(fechaDesde);

	        calendar.add(Calendar.MONTH, 1);
	        calendar.add(Calendar.DAY_OF_MONTH, -1);
	        Date fechaHasta = calendar.getTime();
	        String fechaHastaStr = dateFormat.format(fechaHasta);

	        return new Pair<>(fechaDesdeStr, fechaHastaStr);
	    }

}
