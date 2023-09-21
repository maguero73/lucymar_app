package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import conectaBD.Connection_BD;
import modelo.Funciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
//import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import java.sql.Date.*;
import com.toedter.calendar.JYearChooser;

import ComboBox.RellenarCombos;

public class FrmPersonas extends JFrame {
	
	protected static final JDateChooser JDateChooser = null;
	Connection_BD objConex;
	String sql;
	RellenarCombos re = new RellenarCombos();
	//METODO CONSTRUCTOR
	public FrmPersonas() {
		
		objConex=new Connection_BD();
		
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1434, 741);
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
		JButton btn_grabar_gasto = new JButton("Grabar");
		
		
		btn_grabar_gasto.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				//OBTENER EL VALOR DE LOS COMPONENTES DE GASTOS
				
		String valorTextField1 = txt_monto_gasto.getText();
		int valorComboBox2 = cbo_tipo_gasto.getSelectedIndex();		
		int valorComboBox1 = cbo_titular.getSelectedIndex();
		 
		
		String dia = Integer.toString(dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH));
		String mes = Integer.toString(dateChooser.getCalendar().get(Calendar.MONTH)+1);
		String year= Integer.toString(dateChooser.getCalendar().get(Calendar.YEAR));
		String fecha = (dia + "/" + mes + "/" + year);
		

		
			
			objConex.conectar();    //LLAMA AL METODO CONECTAR
			objConex.guardarGastos(valorComboBox2, valorComboBox1, valorTextField1, fecha);
		
			}}
			
		
			);
		
		
		btn_grabar_gasto.setBounds(534, 205, 117, 25);
		contentPane.add(btn_grabar_gasto);
		
		//BOTON NUEVO
		JButton btn_nuevo_gasto = new JButton("Nuevo");
		btn_nuevo_gasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_monto_gasto.setText("");
				cbo_tipo_gasto.setSelectedIndex(0);
				cbo_titular.setSelectedIndex(0);
				dateChooser.setCalendar(null);
				
			}
		});
		btn_nuevo_gasto.setBounds(534, 281, 117, 25);
		contentPane.add(btn_nuevo_gasto);
		
		//BOTON CERRAR
		JButton btn_cerrar = new JButton("Cerrar");
		btn_cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(WIDTH);
			}
		});
		btn_cerrar.setBounds(1045, 583, 117, 25);
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
		
		cbo_titular = new JComboBox<String>();
		cbo_titular.addItem("Seleccione el titular del gasto o del ingreso");
		cbo_titular.setBounds(209, 116, 264, 24);
		contentPane.add(cbo_titular);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(209, 287, 273, 19);
		contentPane.add(dateChooser);
		
		cbo_tipo_gasto = new JComboBox<String>();
		cbo_tipo_gasto.addItem("Seleccione el tipo de gasto");
		cbo_tipo_gasto.setBounds(209, 243, 273, 24);
		contentPane.add(cbo_tipo_gasto);
		
		JLabel lbl_tipo_ingreso = new JLabel("Tipo de Ingreso");
		lbl_tipo_ingreso.setBounds(47, 411, 144, 26);
		contentPane.add(lbl_tipo_ingreso);
		
		cbo_tipo_ingreso = new JComboBox<String>();
		cbo_tipo_ingreso.addItem("Seleccione el tipo de ingreso");
		cbo_tipo_ingreso.setBounds(209, 413, 273, 24);
		contentPane.add(cbo_tipo_ingreso);
		
		JLabel lbl_fecha_ingreso = new JLabel("Fecha");
		lbl_fecha_ingreso.setBounds(47, 449, 85, 26);
		contentPane.add(lbl_fecha_ingreso);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("dd/MM/yyyy");
		dateChooser_1.setBounds(209, 456, 273, 19);
		contentPane.add(dateChooser_1);
		
		JButton btn_grabar_ingreso = new JButton("Grabar");
		btn_grabar_ingreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//OBTENER EL VALOR DE LOS COMPONENTES DE INGRESOS
				
				String valorTextField = txt_monto_ingreso.getText();
				int valorComboBox = cbo_tipo_ingreso.getSelectedIndex();		
				int valorComboBox1 = cbo_titular.getSelectedIndex();
				 
				
				String dia1 = Integer.toString(dateChooser_1.getCalendar().get(Calendar.DAY_OF_MONTH));
				String mes1 = Integer.toString(dateChooser_1.getCalendar().get(Calendar.MONTH)+1);
				String year1= Integer.toString(dateChooser_1.getCalendar().get(Calendar.YEAR));
				String fecha1 = (dia1 + "/" + mes1 + "/" + year1);
					
				
				objConex.conectar(); 
				objConex.guardarIngresos(valorComboBox, valorComboBox1, valorTextField, fecha1);
				
			}
		});
		btn_grabar_ingreso.setBounds(534, 384, 117, 25);
		contentPane.add(btn_grabar_ingreso);
		
		JButton btn_nuevo_ingreso = new JButton("Nuevo");
		btn_nuevo_ingreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt_monto_ingreso.setText("");
				cbo_tipo_ingreso.setSelectedIndex(0);
				cbo_titular.setSelectedIndex(0);
				dateChooser_1.setCalendar(null);
				
				
			}
		});
		btn_nuevo_ingreso.setBounds(534, 450, 117, 25);
		contentPane.add(btn_nuevo_ingreso);
		
		JLabel lbl_estado_rdo = new JLabel("Estado de Resultados");
		lbl_estado_rdo.setForeground(new Color(106, 90, 205));
		lbl_estado_rdo.setFont(new Font("Noto Sans CJK KR", Font.BOLD, 20));
		lbl_estado_rdo.setBounds(810, 30, 224, 33);
		contentPane.add(lbl_estado_rdo);
		
		JLabel lbl_titular_1 = new JLabel("Mes");
		lbl_titular_1.setBounds(768, 128, 85, 26);
		contentPane.add(lbl_titular_1);
		
		JLabel lbl_titular_1_1 = new JLabel("Año");
		lbl_titular_1_1.setBounds(768, 206, 85, 26);
		contentPane.add(lbl_titular_1_1);
		
		JButton btn_calcular = new JButton("Calcular");
		btn_calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btn_calcular.setBounds(810, 281, 91, 25);
		contentPane.add(btn_calcular);
		
		JEditorPane editor_ingreso = new JEditorPane();
		editor_ingreso.setBounds(917, 352, 117, 21);
		contentPane.add(editor_ingreso);
		
		JButton btn_imprimir = new JButton("Imprimir");
		btn_imprimir.setBounds(943, 281, 91, 25);
		contentPane.add(btn_imprimir);
		
		JLabel lbl_total_ingresos = new JLabel("Total Ingresos");
		lbl_total_ingresos.setBounds(744, 347, 109, 26);
		contentPane.add(lbl_total_ingresos);
		
		JLabel lbl_total_egresos = new JLabel("Total Egresos");
		lbl_total_egresos.setBounds(744, 385, 109, 26);
		contentPane.add(lbl_total_egresos);
		
		JEditorPane editor_egreso = new JEditorPane();
		editor_egreso.setBounds(917, 390, 117, 21);
		contentPane.add(editor_egreso);
		
		JLabel lbl_saldo = new JLabel("Saldo");
		lbl_saldo.setBounds(744, 425, 109, 26);
		contentPane.add(lbl_saldo);
		
		JEditorPane editor_saldo = new JEditorPane();
		editor_saldo.setBounds(917, 430, 117, 21);
		contentPane.add(editor_saldo);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(873, 211, 53, 19);
		contentPane.add(yearChooser);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(862, 135, 122, 19);
		contentPane.add(monthChooser);
		
		//----------------RELLENADO DE COMBOS-------------------//
			
	re.RellenarComboBox("LM_TITULAR", "nombre", cbo_titular);
	re.RellenarComboBox("LM_TIPO_GASTO","descripcion", cbo_tipo_gasto);
	re.RellenarComboBox("LM_TIPO_INGRESO", "descripcion", cbo_tipo_ingreso);
		
	
	} //CIERRE DEL CONSTRUCTOR	
		
		

	
	//VARIABLES DE CLASE

	public String user;
	public String pass;
	public String url;
	public Connection miConexion=null;
	public Statement stm;
	public Statement stm1;
	public Statement stm2;
	
	public JComboBox<String> cbo_titular;
	public JComboBox<String> cbo_tipo_gasto; 
	public JDateChooser dateChooser;
	public JDateChooser dateChooser_1;
	public JComboBox<String> cbo_tipo_ingreso; 
	public JPanel contentPane;
	public JLabel lbl_fecha_gasto;
	public JLabel lbl_monto_gasto;
	public JTextField txt_monto_gasto;
	public JLabel lbl_monto_ingreso;
	public JTextField txt_monto_ingreso;
	public JLabel lbl_control_gastos;
	public JLabel lbl_titular;

	 PreparedStatement pstmt =null;



}