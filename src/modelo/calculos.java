package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Connection_BD;

/*
 * 
 * 
 * CLASE QUE REALIZA TODOS LOS CALCULOS
 */
public class calculos {
	
	public calculos() {
		
		miConexion=new Connection_BD();
	}
	
	
	/*
	 * 
	 * 
	 * REALIZA LA SUMATORIA DE LOS INGRESOS / GASTOS
	 */
	public void sumatorias(String tabla, String valor, JTextField text1, String fecha_desde, String fecha_hasta) {
		
		String sql;

        try {	
        	
    sql= "SELECT SUM(monto)as total FROM " + tabla + " WHERE FECHA BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')"; 	
 		
    pstmt=miConexion.conectar().prepareStatement(sql);
   
   //String fecha_desde="01/09/2023";
  // String fecha_hasta="30/09/2023";
   
          	pstmt.setString(1, fecha_desde);
          	pstmt.setString(2, fecha_hasta);
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
	
	

/*
 * 
 * 
 * 
 * 	REALIZA OPERACION DE RESTA ENTRE AMBAS SUMATORIAS	(INGRESOS - GASTOS)
 */
public void saldo(String tabla1, String tabla2,String valor, JTextField text, String fecha_desde, String fecha_hasta) {
		
		
//String sql = "select(select sum(monto)from " + tabla1 + ")- (select sum(monto)from "+ tabla2 + ") as resultado from dual";
	String sql;
	
        try {

        	sql= "select(select sum(monto)from " + tabla1 + " WHERE FECHA BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY'))- (select sum(monto)from "+ tabla2 + " WHERE FECHA BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')) as resultado from dual";		
            pstmt=miConexion.conectar().prepareStatement(sql);
            
            //String fecha_desde="01/09/2023";
           // String fecha_hasta="30/09/2023";
            
                   	pstmt.setString(1, fecha_desde);
                   	pstmt.setString(2, fecha_hasta);
                   	pstmt.setString(3, fecha_desde);
                	pstmt.setString(4, fecha_hasta);
           			ResultSet rs=pstmt.executeQuery();
          
         
            
            while (rs.next()) {            	
            	text.setText(rs.getString(valor));
            	
            }
            
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally {
        }
        
    }    
	
	Connection_BD miConexion;
	//public PreparedStatement pstmt =null;
	public Statement st;
	public PreparedStatement pstmt =null;
}
