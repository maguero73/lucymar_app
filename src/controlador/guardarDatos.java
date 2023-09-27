package controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import modelo.Connection_BD;

public class guardarDatos {
	
	public guardarDatos() {
		
		miConexion=new Connection_BD();
	}
	
	////////////////////////////////////////
	//METODO GUARDAR GASTOS
////////////////////////////////////////
	
	public void guardarGastos(int valorComboBox2, int valorComboBox1, String valorTextField1, String fecha) {
		
		Statement st = null;
		String sql;
		
		try {
			st=miConexion.conectar().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			
			sql= "INSERT INTO LM_GASTOS (COD_GASTO, COD_TITULAR, MONTO, FECHA) values (?,?,?,?)";
					
			 pstmt =miConexion.conectar().prepareStatement(sql);
			
			 pstmt.setInt(1, valorComboBox2);
			 pstmt.setInt(2, valorComboBox1);
			 pstmt.setString(3, valorTextField1);
			 pstmt.setString(4, fecha);
				
				
				
				
				int rowsAffected=pstmt.executeUpdate();
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, ("Registro guardado con éxito"), "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
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
				st=miConexion.conectar().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				
				sql1= "INSERT INTO LM_INGRESOS (COD_INGRESO, COD_TITULAR, MONTO, FECHA) values (?,?,?,?)";
						
				 pstmt =miConexion.conectar().prepareStatement(sql1);
				
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
	
	Connection_BD miConexion;
	public PreparedStatement pstmt =null;
	public Statement st;
}
