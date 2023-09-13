package conectaBD;

import java.sql.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.xml.crypto.Data;

import vistas.*;

public class Connection_BD {
//PASO NUMERO 1
	public Connection_BD() {
		
		user="SYSTEM";
		pass="oracle";
		url="jdbc:oracle:thin:@localhost:51521:xe";
		miConexion=null;
		stm=null;
		
	}
	
//PASO NUMERO 2
		public void conectar() {
			
		try {
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
			miConexion=DriverManager.getConnection(url, user, pass);
			JOptionPane.showInternalMessageDialog(null, "Conexión realizada");
			
		}catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Conexión no realizada");
			
		}
			
	}
	//PASO NUMERO 3
		public String leerDatosBD() throws SQLException {
			
		stm= miConexion.createStatement();
		String cadSql = "";
		try {
			
			cadSql="SELECT DISTINCT NOMBRE FROM LM_TITULAR";
			ResultSet rs= stm.executeQuery(cadSql);
			String datos= "";
			while (rs.next()) 
			{
			
				
			   datos+= rs.getString("NOMBRE");

			    return datos;
			}
				
			rs.close();
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La tabla no existe");
			return null;
		}
		return cadSql;
		
		
			
		}
		
		public void ingresarDatosBD(int id, int cod_gasto, int cod_titular, long monto, int fecha) {
			
			int r;
			String cadSql;
			
			try {
				stm=miConexion.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				
				cadSql= "INSERT INTO LM_GASTOS values ('" + id + "','" + cod_gasto + "', '" + cod_titular + "', '" + monto + "', '" + fecha + "')";
				
				r= stm.executeUpdate(cadSql);
				JOptionPane.showMessageDialog(null, r + " Registro agregado");
				
			} 
				//AGREGAR AQUI EXCEPCIONES ORA
				catch (SQLException sqle) { 
				JOptionPane.showMessageDialog(null, sqle); //(null, "Registro no agregado");
			
				}
			}
			
		
		
//VARIABLES DE CLASE
		
		 String user;
		 String pass;
		 String url;
		 Connection miConexion;
		 Statement stm;		
		
	}