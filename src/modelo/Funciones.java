package modelo;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

import vistas.*;

public class Funciones {
	
	SimpleDateFormat Formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getFecha(JDateChooser jd){
		
		if (jd.getDate()!= null) {
			
			return Formato.format(jd.getDate());
			
			
		}else {
			return null;
		}
		
		
	}
	
	
	
}

