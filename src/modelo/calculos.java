package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Connection_BD;


public class calculos {
	
	public calculos() {
		
		miConexion=new Connection_BD();
	}
	
	
	//REALIZA LA SUMATORIA DE LOS INGRESOS / GASTOS
	public void sumatorias(String tabla, String valor, JTextField text1) {
		
		String sql;

        try {	
        	
    sql= "SELECT SUM(monto)as total FROM " + tabla + " WHERE FECHA BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')"; 	
 		
    pstmt=miConexion.conectar().prepareStatement(sql);
   
   String sept1="01/09/2023";
   String sept2="30/09/2023";
   
          	pstmt.setString(1, sept1);
          	pstmt.setString(2, sept2);
  			ResultSet rs=pstmt.executeQuery();
            
           while (rs.next()){
              	
        	   text1.setText(rs.getString(valor));
        	   
           }
            	                   
            
             rs.close();
             miConexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            
        
        }
        
    }    
	
	
//REALIZA OPERACION DE RESTA ENTRE AMBAS SUMATORIAS	(INGRESOS - GASTOS)
	
public void saldo(String tabla1, String tabla2,String valor, JTextField text) {
		
		
		String sql = "select(select sum(monto)from " + tabla1 + ")- (select sum(monto)from "+ tabla2 + ") as resultado from dual";
	
        try {

			
            Statement pstmt = miConexion.conectar().createStatement();
            ResultSet rs;
            st=miConexion.conectar().createStatement();
			rs=st.executeQuery(sql);
          
         
            
            while (rs.next()) {            	
            	text.setText(rs.getString(valor));
            	System.out.println(rs.getString(valor));
            }
            
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally {
        	
        	try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }    
	
	Connection_BD miConexion;
	//public PreparedStatement pstmt =null;
	public Statement st;
	public PreparedStatement pstmt =null;
}
