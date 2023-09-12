package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conectaBD.Connection_BD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;

public class FrmPersonas extends JFrame {
	
	Connection_BD objConex;
	
	//METODO CONSTRUCTOR
	public FrmPersonas() {
		
		objConex=new Connection_BD();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 747);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JLABEL
		JLabel lbl_tipo_gasto = new JLabel("Tipo de Gasto");
		lbl_tipo_gasto.setBounds(47, 242, 144, 26);
		contentPane.add(lbl_tipo_gasto);
		
		lbl_fecha_gasto = new JLabel("Fecha");
		lbl_fecha_gasto.setBounds(47, 280, 85, 26);
		contentPane.add(lbl_fecha_gasto);
		
		lbl_monto_gasto = new JLabel("Monto Gasto");
		lbl_monto_gasto.setBounds(47, 204, 117, 26);
		contentPane.add(lbl_monto_gasto);
		

		txt_monto_gasto = new JTextField();
		txt_monto_gasto.setColumns(10);
		txt_monto_gasto.setBounds(209, 204, 136, 28);
		contentPane.add(txt_monto_gasto);
		
		
		//BOTON GRABAR
		JButton btn_grabar = new JButton("Grabar");
		
		
		btn_grabar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				try {
				
					objConex.conectar();    //LLAMA AL METODO CONECTAR
					
					if (e.getSource() == btn_grabar) {
						int codigo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del gasto"));
						int cod_titular =Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del titular"));
						int id_tipo_gasto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tipo de gasto"));
						long monto=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto del gasto"));
						int fecha =Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fecha del gasto"));
						
					//Integer.parseInt(JOptionPane.showInputDialog("Ingrese la fecha"));
					//Date date = java.sql.Date.valueOf(fecha);
						
						objConex.ingresarDatosBD(codigo, cod_titular, id_tipo_gasto, monto, fecha);
						//LLAMA AL METODO INGRESARDATOSBD
					}
				}catch (Exception e2) {
					
				}
				
				
				
				
				
			}
		});
		btn_grabar.setBounds(534, 160, 117, 25);
		contentPane.add(btn_grabar);
		
		//BOTON NUEVO
		JButton btn_nuevo = new JButton("Nuevo");
		btn_nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_nuevo.setBounds(534, 345, 117, 25);
		contentPane.add(btn_nuevo);
		
		//BOTON CERRAR
		JButton btn_cerrar = new JButton("Cerrar");
		btn_cerrar.setBounds(534, 530, 117, 25);
		contentPane.add(btn_cerrar);
		
		
		//JLABEL
		lbl_monto_ingreso = new JLabel("Monto Ingreso");
		lbl_monto_ingreso.setBounds(47, 373, 117, 26);
		contentPane.add(lbl_monto_ingreso);
		
		txt_monto_ingreso = new JTextField();
		txt_monto_ingreso.setColumns(10);
		txt_monto_ingreso.setBounds(209, 373, 136, 28);
		contentPane.add(txt_monto_ingreso);
		
		//TITULO DEL FORMULARIO
		lbl_control_gastos = new JLabel("Control de Gastos");
		lbl_control_gastos.setFont(new Font("Noto Sans CJK KR", Font.BOLD, 20));
		lbl_control_gastos.setForeground(new Color(106, 90, 205));
		lbl_control_gastos.setBounds(47, 30, 191, 33);
		contentPane.add(lbl_control_gastos);
		
		lbl_titular = new JLabel("Titular");
		lbl_titular.setBounds(47, 115, 85, 26);
		contentPane.add(lbl_titular);
		
		JComboBox<String>cbo_titular = new JComboBox<String>();
		cbo_titular.setBounds(209, 116, 273, 24);
		contentPane.add(cbo_titular);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(209, 287, 273, 19);
		contentPane.add(dateChooser);
		
		JComboBox<String> cbo_tipo_gasto = new JComboBox<String>();
		cbo_tipo_gasto.setBounds(209, 243, 273, 24);
		contentPane.add(cbo_tipo_gasto);
		
		JLabel lbl_tipo_ingreso = new JLabel("Tipo de Ingreso");
		lbl_tipo_ingreso.setBounds(47, 411, 144, 26);
		contentPane.add(lbl_tipo_ingreso);
		
		JComboBox<String> cbo_tipo_ingreso = new JComboBox<String>();
		cbo_tipo_ingreso.setBounds(209, 413, 273, 24);
		contentPane.add(cbo_tipo_ingreso);
		
		JLabel lbl_fecha_ingreso = new JLabel("Fecha");
		lbl_fecha_ingreso.setBounds(47, 449, 85, 26);
		contentPane.add(lbl_fecha_ingreso);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(209, 456, 273, 19);
		contentPane.add(dateChooser_1);
		
		//-------------------CONEXION A BASE DE DATOS----------------------------//
		
		user="SYSTEM";
		pass="oracle";
		url="jdbc:oracle:thin:@localhost:51521:xe";
		miConexion=null;
		stm=null;
		
		//CARGA JCOMBOBOX TITULAR DEL GASTO
		
		try {
			Class.forName("oracle.jdbc.OracleDriver").newInstance();
			miConexion=DriverManager.getConnection(url, user, pass);
			//JOptionPane.showInternalMessageDialog(null, "Conexión realizada");
			
			Statement stm=miConexion.createStatement();
			
			String consulta="SELECT DISTINCT NOMBRE FROM LM_TITULAR";
			
			ResultSet rs= stm.executeQuery(consulta);
			
			while(rs.next()) {
				
				cbo_titular.addItem(rs.getString(1));
				
			}
			
			rs.close();
			
						
		}catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Conexión no realizada");
			
			}
			
		//CARGA JCOMBOBOX TITULAR DEL GASTO
		
				try {
					Class.forName("oracle.jdbc.OracleDriver").newInstance();
					DriverManager.getConnection(url, user, pass);
					//JOptionPane.showInternalMessageDialog(null, "Conexión realizada");
					
					Statement stm1=miConexion.createStatement();
					
					String consulta1="SELECT DISTINCT DESCRIPCION FROM LM_TIPO_GASTO";
					
					ResultSet rs= stm1.executeQuery(consulta1);
					
					while(rs.next()) {
						
						cbo_tipo_gasto.addItem(rs.getString(1));
						
					}
					
					rs.close();
					
								
				}catch (Exception e) {
					JOptionPane.showInternalMessageDialog(null, "Conexión no realizada");
					
					}
		
		
				
				//CARGA JCOMBOBOX TITULAR DEL INGRESO
				
				try {
					Class.forName("oracle.jdbc.OracleDriver").newInstance();
					DriverManager.getConnection(url, user, pass);
					//JOptionPane.showInternalMessageDialog(null, "Conexión realizada");
					
					Statement stm2=miConexion.createStatement();
					
					String consulta2="SELECT DISTINCT DESCRIPCION FROM LM_TIPO_INGRESO";
					
					ResultSet rs= stm2.executeQuery(consulta2);
					
					while(rs.next()) {
						
						cbo_tipo_ingreso.addItem(rs.getString(1));
						
					}
					
					rs.close();
					
								
				}catch (Exception e) {
					JOptionPane.showInternalMessageDialog(null, "Conexión no realizada");
					
					}
		
		
		
		
		
	} //CIERRE DEL CONSTRUCTOR	
		
		
	
	
	
	
	//VARIABLES DE CLASE

	private String user;
	private String pass;
	private String url;
	private Connection miConexion;
	private Statement stm;
	private Statement stm1;
	private Statement stm2;
	
	public JComboBox<String> titular;
	
	private JPanel contentPane;
	private JLabel lbl_fecha_gasto;
	private JLabel lbl_monto_gasto;
	private JTextField txt_monto_gasto;
	private JLabel lbl_monto_ingreso;
	private JTextField txt_monto_ingreso;
	private JLabel lbl_control_gastos;
	private JLabel lbl_titular;
} //CIERRE DE LA CLASE
