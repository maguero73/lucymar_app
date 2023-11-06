package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import controlador.Connection_BD;

public class RellenarCombos {
	
	public RellenarCombos() {
		
		miConexion=new Connection_BD();
		
	}
	
	public void RellenarComboBox(String tabla, String valor, JComboBox<String> combo) {
		

		String sql = "select * from " + tabla + " order by CODIGO";
		Statement st = null;
		
		try {
			
			st=miConexion.conectar().createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			while (rs.next()) {
				
				combo.addItem(rs.getString(valor));
				
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
				
			}
	Connection_BD miConexion;
	 
		}		
			

