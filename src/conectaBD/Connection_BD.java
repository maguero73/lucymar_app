package conectaBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.xml.crypto.Data;

import modelo.Funciones;
import vistas.*;
import modelo.Funciones.*;

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
		String sql = "";
		try {
			
			sql="SELECT DISTINCT NOMBRE FROM LM_TITULAR";
			ResultSet rs= stm.executeQuery(sql);
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
		return sql;
		
		
			
		}
		
		public void ingresarDatosBD(int valorComboBox2, int valorComboBox1, String valorTextField1, String fecha) {
			
			
			String sql;
			
			try {
				stm=miConexion.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				
				sql= "INSERT INTO LM_GASTOS (COD_GASTO, COD_TITULAR, MONTO, FECHA) values (?,?,?,?)";
						
						//('" + id + "','" + cod_gasto + "', '" + cod_titular + "', '" + monto + "', '" + fecha + "')";
				
				 pstmt =miConexion.prepareStatement(sql);
				
				 pstmt.setInt(1, valorComboBox2);
				 pstmt.setInt(2, valorComboBox1);
				 pstmt.setString(3, valorTextField1);
				 pstmt.setString(4, fecha);
					
					
					
					
					int rowsAffected=pstmt.executeUpdate();
					if (rowsAffected > 0) {
						JOptionPane.showInternalMessageDialog(null, ("Registro guardado con éxito"));
					
					}else {
						JOptionPane.showInternalMessageDialog(null, ("Error al guardar el registro"));
							}
				
			}catch (SQLException ex) { 
				ex.printStackTrace();
				JOptionPane.showInternalMessageDialog(null, (ex));
				}finally {
					try {
						if (pstmt != null) {
							pstmt.close();
						}
					}catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showInternalMessageDialog(null, (ex));
					}
				}
			}
	
		
		
		
		
//VARIABLES DE CLASE
		
		 String user;
		 String pass;
		 String url;
		 String fecha;
		 Connection miConexion = null;
		 PreparedStatement pstmt =null;
		 Statement stm;		
		
	}