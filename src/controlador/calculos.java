package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Connection_BD;



public class calculos {
	
	public calculos() {
		
		miConexion=new Connection_BD();
	}
	
	
	//REALIZA LA SUMATORIA DE LOS INGRESOS / GASTOS
	public void sumatorias(String tabla, String valor, JTextField text1) {
		
		//double valor = 0;
		Statement st = null;
		String sql = "select sum(monto)as total from " + tabla;
	
        try {

			
            Statement pstmt = miConexion.conectar().createStatement();
            ResultSet rs;
            st=miConexion.conectar().createStatement();
			rs=st.executeQuery(sql);
          
         
            
            while (rs.next()) {
            	
            	text1.setText(rs.getString(valor));
            	
            	
            }
            
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }    
	
	
//REALIZA OPERACION DE RESTA ENTRE AMBAS SUMATORIAS	
	
public void resta(String tabla1, String tabla2,String valor, JTextField text) {
		
		Statement st = null;
		String sql = "select(select sum(monto)from " + tabla1 + ")- (select sum(monto)from "+ tabla2 + ") as resultado from dual";
	
        try {

			
            Statement pstmt = miConexion.conectar().createStatement();
            ResultSet rs;
            st=miConexion.conectar().createStatement();
			rs=st.executeQuery(sql);
          
         
            
            while (rs.next()) {
            	
            	text.setText(rs.getString(valor));
            	
            	
            }
            
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }    
	
	Connection_BD miConexion;


}
