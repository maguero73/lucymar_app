package modelo;

public class validaciones {
	
	
	/**
	 *Comprueba si la cadena coincide con la expresión regular
	 * 
	 * @param datos Este parámetro me permite reutilizar la validación en todos los campos de tipo Number
	 * @return Esta función retorna un valor verdadero si se trata de numeros del 1 al 9 la cantidad que quieras
	 */	
	public static boolean validarNumeros(String datos) {
	    // Utiliza una expresión regular para validar el formato
	    String regex = "^([0-9]){1,9}+(\\.[0-9]{2})?$";
	    
	    return datos.matches(regex);
	}

}
