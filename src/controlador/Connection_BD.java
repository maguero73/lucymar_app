package controlador;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import javax.xml.crypto.Data;

import vista.*;

public class Connection_BD {

	Connection miConexion = null;
	//METODO CONSTRUCTOR
	public Connection_BD() {
		
		user="SYSTEM";
		pass="oracle";
		url="jdbc:oracle:thin:@localhost:51521:xe";
		miConexion=null;
		st=null;
		
	}
	

		public Connection conectar() {
			
		try {
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
			miConexion=DriverManager.getConnection(url, user, pass);
			//JOptionPane.showInternalMessageDialog(null, "Conexión realizada");
			
		}catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Conexión no realizada");
			
		}
		return miConexion;
			
	}

				
//VARIABLES DE CLASE
public Connection_BD objConex;
public String user;
public String pass;
public String url;
public String fecha;

public PreparedStatement pstmt =null;
public Statement st;		
		
	}