package ComboBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import conectaBD.Connection_BD;

public class RellenarCombos {
	
	public RellenarCombos() {
		
		user="SYSTEM";
		pass="oracle";
		url="jdbc:oracle:thin:@localhost:51521:xe";
		miConexion=null;
		
	}
	
	public void RellenarComboBox(String tabla, String valor, JComboBox combo) {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
			miConexion=DriverManager.getConnection(url, user, pass);
			//JOptionPane.showInternalMessageDialog(null, "Conexión realizada");
			
		}catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Conexión no realizada");
			
		}
		
		
		String sql = "select * from " + tabla;
		Statement st = null;
		Connection_BD con = new Connection_BD();
		con.conectar();
		try {
			
			st=miConexion.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				
				combo.addItem(rs.getString(valor));
				
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
				
			}
	Connection miConexion = null;
	 String user;
	 String pass;
	 String url;
	 String fecha;
	 
		}		
			

