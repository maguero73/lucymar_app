package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.RellenarCombos;
import modelo.calculos;
import modelo.guardarDatos;
import oracle.sql.DATE;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import java.sql.Date.*;
import com.toedter.calendar.JYearChooser;

import controlador.Connection_BD;

public class FrmPersonas extends JFrame {
	Connection_BD miConexion;
	protected static final JDateChooser JDateChooser = null;
	//private static Date utilDate = new Date();
	//private static SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
	//public static Calendar calendar = new GregorianCalendar();
	//java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	
	
	String sql;
	RellenarCombos re = new RellenarCombos();
	//METODO CONSTRUCTOR
	public FrmPersonas() {
		
		miConexion=new Connection_BD();
		calculos calc=new calculos();
		guardarDatos guardar=new guardarDatos();
		
				
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
				
		String valorTextField1 = txt_monto_gasto.getText().trim();
		int valorComboBox2 = cbo_tipo_gasto.getSelectedIndex();		
		int valorComboBox1 = cbo_titular.getSelectedIndex();
		 		
		    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		    String fecha = formato.format(new java.util.Date());
		    try {
		        java.util.Date parsedDate = formato.parse(fecha);
		        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		    } catch (ParseException e2) {
		        e2.printStackTrace();
		}

		java.sql.Date dato2=null;
		
		//IMPORTANTE CONVERTIMOS LA VARIABLE FECHA DE STRING A DATE (DE LA CLASE JAVA.SQL.DATE)
		dato2=java.sql.Date.valueOf(fecha);
		 
//VALIDACIONES//
			
	//01.VALIDACIONES DEL CAMPO TXT_MONTO_GASTO	
		
		Date selectedDate = dateChooser.getDate();
		
		if (cbo_titular.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "Debe ingresar el titular del gasto", "Error", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(txt_monto_gasto.getText().trim().isEmpty()) {
			  JOptionPane.showMessageDialog(null, "Debe ingresar el monto del gasto", "Error", JOptionPane.WARNING_MESSAGE);
		}else if (!validarNumeros(txt_monto_gasto.getText().trim())){
			JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico", "Error", JOptionPane.WARNING_MESSAGE);
		}else if (cbo_tipo_gasto.getSelectedIndex()==0) {
			
			JOptionPane.showMessageDialog(null, "Debe ingresar el tipo de gasto", "Error", JOptionPane.WARNING_MESSAGE);
		}else if (selectedDate == null) {
			JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del gasto", "Error", JOptionPane.WARNING_MESSAGE);
		}else if (selectedDate.after(new Date())) {
				 // SI LA FECHA SELECCIONADA ES POSTERIOR A LA FECHA ACTUAL ENTONCES ERROR 
				JOptionPane.showMessageDialog(null, "La fecha seleccionada debe ser anterior o igual a la fecha actual", "Error", JOptionPane.WARNING_MESSAGE);

		    	
		    } else {
		    	//LA FECHA SELECCIONADA ES ANTERIOR A LA FECHA ACTUAL, ES CORRECTA
		    	//GUARDA EN LA BASE DE DATOS EL GASTO (TABLA LM_GASTOS)			
				guardar.guardarGastos(valorComboBox2, valorComboBox1, valorTextField1, dato2);
				
		    		}		
				}
			});
		
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
		
		//JTEXTFIELD
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
		cbo_titular.addItem("Seleccione el titular");
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
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(209, 456, 273, 19);
		contentPane.add(dateChooser_1);
		
		JButton btn_grabar_ingreso = new JButton("Grabar");
		btn_grabar_ingreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
	//OBTENER EL VALOR DE LOS COMPONENTES DE INGRESOS
				
		String valorTextField = txt_monto_ingreso.getText().trim();
		int valorComboBox = cbo_tipo_ingreso.getSelectedIndex();		
		int valorComboBox1 = cbo_titular.getSelectedIndex();
		
		//OBTENER EL VALOR DE LOS COMPONENTES DE GASTOS
		 		
		    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		    String fecha1 = formato.format(new java.util.Date());
		    try {
		        java.util.Date parsedDate = formato.parse(fecha1);
		        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		    } catch (ParseException e2) {
		        e2.printStackTrace();
		}
	//SETEAMOS LOS VALORES DE DIA MES Y AÑO EN EL JDATECHOOSER	
	  /*	    
		String dia1 = Integer.toString(dateChooser_1.getCalendar().get(Calendar.DAY_OF_MONTH));
		String mes1 = Integer.toString(dateChooser_1.getCalendar().get(Calendar.MONTH)+1);
		String year1= Integer.toString(dateChooser_1.getCalendar().get(Calendar.YEAR));
		String fecha1 = (year1 + "-" + mes1 + "-" + dia1);
		System.out.println(fecha1);
	*/	
		java.sql.Date dato =null;
		
		//CONVERTIMOS LA VARIABLE DE STRING A DATE (PERO USANDO LA CLASE JAVA.SQL.DATE)
		//IMPORTANTE
			dato=java.sql.Date.valueOf(fecha1);
		
			Date selectedDate = dateChooser_1.getDate();
			
			if (cbo_titular.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(null, "Debe ingresar el titular del ingreso", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			else if(txt_monto_ingreso.getText().trim().isEmpty()) {
				  JOptionPane.showMessageDialog(null, "Debe ingresar el monto del ingreso", "Error", JOptionPane.WARNING_MESSAGE);
			}else if (!validarNumeros(txt_monto_ingreso.getText().trim())){
				JOptionPane.showMessageDialog(null, "Debe ingresar un valor numerico", "Error", JOptionPane.WARNING_MESSAGE);

			}else if (cbo_tipo_ingreso.getSelectedIndex()==0) {
				
				JOptionPane.showMessageDialog(null, "Debe ingresar el tipo de ingreso", "Error", JOptionPane.WARNING_MESSAGE);
			}else if (selectedDate == null) {
				JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del ingreso", "Error", JOptionPane.WARNING_MESSAGE);
			}else if (selectedDate.after(new Date())) {
					 // SI LA FECHA SELECCIONADA ES POSTERIOR A LA FECHA ACTUAL ENTONCES ERROR 
					JOptionPane.showMessageDialog(null, "La fecha seleccionada debe ser anterior o igual a la fecha actual", "Error", JOptionPane.WARNING_MESSAGE);

			    	
			    } else {
			    	//LA FECHA SELECCIONADA ES ANTERIOR A LA FECHA ACTUAL, ES CORRECTA
			    	//GUARDA EN LA BASE DE DATOS EL GASTO (TABLA LM_GASTOS)		
		//Date sqlDate1= dateChooser_1.getCalendar().getTime();
		
		//Date fecha2 = new Date();			
				
		//GUARDA EN LA BASE DE DATOS TODOS LOS INGRESOS (TABLA LM_INGRESOS)
		guardar.guardarIngresos(valorComboBox, valorComboBox1, valorTextField, dato);
				
			   }
			
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
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(862, 135, 122, 19);
		contentPane.add(monthChooser);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(873, 211, 53, 19);
		contentPane.add(yearChooser);
		
		
		JButton btn_calcular = new JButton("Calcular");
		btn_calcular.addActionListener(new ActionListener() {
			
		//JMonthChooser month = new JMonthChooser();
			public void actionPerformed(ActionEvent e) {
				
				// OBTIENE MES ACTUAL
				Month mes = LocalDate.now().getMonth();
				Calendar fecha = new GregorianCalendar();
				fecha.get(Calendar.YEAR);
				String vacio = 0 + "";
					
//MES 8+1 = SEPTIEMBRE -->SI ES MES SEPTIEMBRE Y ES AÑO 2023 ENTONCES					
if (monthChooser.getMonth()== 8 && yearChooser.getYear()== 2023){
	//
	//CALCULO LA SUMATORIA DE TODOS LOS INGRESOS Y EGRESOS MES SEPTIEMBRE
		calc.sumatorias("LM_INGRESOS","total", text_total_ingresos,"01/09/2023","30/09/2023");
		calc.sumatorias("LM_GASTOS", "total", text_total_egresos, "01/09/2023","30/09/2023");
	//CALCULO EL SALDO AL FINAL DE CADA MES	
		calc.saldo("LM_INGRESOS", "LM_GASTOS", "resultado", text_saldo, "01/09/2023","30/09/2023");		
		} 

		else if(monthChooser.getMonth()== 9 && yearChooser.getYear()== 2023) {
			//MES 9+1 = 10 (OCTUBRE)	
			calc.sumatorias("LM_INGRESOS","total", text_total_ingresos,"01/10/2023","31/10/2023");
			calc.sumatorias("LM_GASTOS", "total", text_total_egresos, "01/10/2023","31/10/2023");
			calc.saldo("LM_INGRESOS", "LM_GASTOS", "resultado", text_saldo, "01/10/2023","31/10/2023");
			}
		else if(monthChooser.getMonth()== 10 && yearChooser.getYear()== 2023) {
			//MES 10+1 = 12 (NOVIEMBRE)
			calc.sumatorias("LM_INGRESOS","total", text_total_ingresos,"01/11/2023","30/11/2023");
			calc.sumatorias("LM_GASTOS", "total", text_total_egresos, "01/11/2023","30/11/2023");
			calc.saldo("LM_INGRESOS", "LM_GASTOS", "resultado", text_saldo, "01/11/2023","30/11/2023");
		}
		else if(monthChooser.getMonth()== 11 && yearChooser.getYear()== 2023) {
			//MES 11+1 = 12 (DICIEMBRE)
			calc.sumatorias("LM_INGRESOS","total", text_total_ingresos,"01/12/2023","31/12/2023");
			calc.sumatorias("LM_GASTOS", "total", text_total_egresos, "01/12/2023","31/12/2023");
			calc.saldo("LM_INGRESOS", "LM_GASTOS", "resultado", text_saldo, "01/12/2023","31/12/2023");
		}else {
		
			
			text_total_ingresos.setText(vacio);
			text_total_egresos.setText(vacio);
			text_saldo.setText(vacio);
			
		}
			}					
		
		});
		btn_calcular.setBounds(810, 281, 91, 25);
		contentPane.add(btn_calcular);
		
		JButton btn_imprimir = new JButton("Limpiar");
		btn_imprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_total_ingresos.setText(null);
				text_total_egresos.setText(null);
				text_saldo.setText(null);
				
			}
		});
		btn_imprimir.setBounds(943, 281, 91, 25);
		contentPane.add(btn_imprimir);
		
		JLabel lbl_total_ingresos = new JLabel("Total Ingresos");
		lbl_total_ingresos.setBounds(744, 352, 109, 26);
		contentPane.add(lbl_total_ingresos);
		
		JLabel lbl_total_egresos = new JLabel("Total Egresos");
		lbl_total_egresos.setBounds(744, 385, 109, 26);
		contentPane.add(lbl_total_egresos);
		
		JLabel lbl_saldo = new JLabel("Saldo");
		lbl_saldo.setBounds(744, 425, 109, 26);
		contentPane.add(lbl_saldo);
		

		

		
		
		//----------------RELLENADO DE COMBOS-------------------//
			
	re.RellenarComboBox("LM_TITULAR", "nombre", cbo_titular);
	re.RellenarComboBox("LM_TIPO_GASTO","descripcion", cbo_tipo_gasto);
	re.RellenarComboBox("LM_TIPO_INGRESO", "descripcion", cbo_tipo_ingreso);
	
	text_total_ingresos = new JTextField();
	text_total_ingresos.setBounds(917, 356, 114, 19);
	contentPane.add(text_total_ingresos);
	text_total_ingresos.setColumns(10);
	
	text_total_egresos = new JTextField();
	text_total_egresos.setColumns(10);
	text_total_egresos.setBounds(917, 392, 114, 19);
	contentPane.add(text_total_egresos);
	
	text_saldo = new JTextField();
	text_saldo.setColumns(10);
	text_saldo.setBounds(917, 429, 114, 19);
	text_saldo.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
	contentPane.add(text_saldo);
		
	
	} //CIERRE DEL CONSTRUCTOR	
		
	
	
	public static boolean validarNumeros(String datos)
	{
		return datos.matches("[0-9]*");
		
	}
	
	
	// Metodo para obtener fecha de JDateChooser
	 public String getFecha(JDateChooser jd) {
	   SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
	   if (jd.getDate() != null) {
	     return Formato.format(jd.getDate());
	   } else {
	     return null;
	   }
	 }

	//VARIABLES DE CLASE


	public Statement stm, stm1, stm2;
	public Double monto;
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
	 private JTextField text_total_ingresos, text_total_egresos, text_saldo;
}