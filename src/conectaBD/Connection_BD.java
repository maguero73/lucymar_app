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
		
		st=null;
		
	}
	
//PASO NUMERO 2
		public void conectar() {
			
		try {
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
			miConexion=DriverManager.getConnection(url, user, pass);
			//JOptionPane.showInternalMessageDialog(null, "Conexión realizada");
			
		}catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Conexión no realizada");
			
		}
			
	}

		
		////////////////////////////////////////
		//METODO GUARDAR GASTOS
	////////////////////////////////////////
		
		public void guardarGastos(int valorComboBox2, int valorComboBox1, String valorTextField1, String fecha) {
			
			
			String sql;
			
			try {
				st=miConexion.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				
				sql= "INSERT INTO LM_GASTOS (COD_GASTO, COD_TITULAR, MONTO, FECHA) values (?,?,?,?)";
						
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
	
	////////////////////////////////////////
		//METODO GUARDAR INGRESOS
	////////////////////////////////////////
		
public void guardarIngresos(int valorComboBox, int valorComboBox1, String valorTextField, String fecha1) {
			
			
			String sql1;
			
			try {
				st=miConexion.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				
				sql1= "INSERT INTO LM_INGRESOS (COD_INGRESO, COD_TITULAR, MONTO, FECHA) values (?,?,?,?)";
						
				 pstmt =miConexion.prepareStatement(sql1);
				
				 pstmt.setInt(1, valorComboBox);
				 pstmt.setInt(2, valorComboBox1);
				 pstmt.setString(3, valorTextField);
				 pstmt.setString(4, fecha1);
					
					
					
					
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
Connection_BD objConex;
		 String user;
		 String pass;
		 String url;
		 String fecha;
		 Connection miConexion = null;
		 PreparedStatement pstmt =null;
		 Statement st;		
		
	}