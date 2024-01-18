package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.RellenarCombos;
import modelo.calculos;
import modelo.guardarDatos;
import modelo.validaciones;
import modelo.FechaInicioFinDelMes.Pair;
import modelo.FechaIF;
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
		setTitle("lucymar_app");
		
		miConexion=new Connection_BD();
		calculos calc=new calculos();
		guardarDatos guardar=new guardarDatos();
		
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1534, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JLABEL
		JLabel lbl_tipo_gasto = new JLabel("Tipo de Gasto");
		lbl_tipo_gasto.setBounds(47, 316, 144, 26);
		contentPane.add(lbl_tipo_gasto);
		
		lbl_fecha_gasto = new JLabel("Fecha del Gasto");
		lbl_fecha_gasto.setBounds(47, 354, 117, 26);
		contentPane.add(lbl_fecha_gasto);
		
		lbl_monto_gasto = new JLabel("Monto Gasto");
		lbl_monto_gasto.setBounds(47, 260, 144, 26);
		contentPane.add(lbl_monto_gasto);
		
		

		txt_monto_gasto = new JTextField();
		//GLOBO ALERT		
		txt_monto_gasto.setToolTipText("No se permite la coma como separador decimal");
		txt_monto_gasto.setColumns(10);
		txt_monto_gasto.setBounds(209, 260, 136, 28);
		contentPane.add(txt_monto_gasto);
		setLocationRelativeTo(null);
		
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
			
	//VALIDACIONES DEL CAMPO TXT_MONTO_GASTO	
		
		Date selectedDate = dateChooser.getDate();
		

		if (cbo_titular.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "Debe ingresar el titular del gasto", "Error", JOptionPane.WARNING_MESSAGE);
		}
		/*
		 * 
		 * 
		 * VALIDACIONES DEL CAMPO MONTO GASTO
		 * (ATENCION RESPETAR EL ORDEN)
		 */
		
		JRadioButton radio_gasto_pesos1 = new JRadioButton("Pesos");
	//01. VALIDA CAMPO VACIO
		else if(txt_monto_gasto.getText().trim().isEmpty()) {
			  JOptionPane.showMessageDialog(null, "Debe ingresar el monto del gasto", "Error", JOptionPane.WARNING_MESSAGE);
	//02. VALIDA CON FUNCION EXPRESION REGULAR (MAX 9 ENTEROS 2 DECIMALES)	
		}else if (!validaciones.validarNumeros(txt_monto_gasto.getText().trim())){
			JOptionPane.showMessageDialog(null, "El monto del gasto debe ser numérico, positivo, con un máximo de 9 enteros y 2 decimales", "Error Monto Gasto", JOptionPane.WARNING_MESSAGE);
	//03. VALIDA CAMPO EN 0(CERO)		
		}else if ((Double.parseDouble(txt_monto_gasto.getText().trim()))==0){
			JOptionPane.showMessageDialog(null, "El monto del gasto debe ser numérico, positivo, con un máximo de 9 enteros y 2 decimales", "Error Monto Gasto", JOptionPane.WARNING_MESSAGE);	
		}else if {	
		}else if (cbo_tipo_gasto.getSelectedIndex()==0) {
			
			JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de gasto", "Error", JOptionPane.WARNING_MESSAGE);
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
		
		
		
		btn_grabar_gasto.setBounds(534, 302, 117, 25);
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
		btn_nuevo_gasto.setBounds(534, 355, 117, 25);
		contentPane.add(btn_nuevo_gasto);
		
		//BOTON CERRAR
		JButton btn_cerrar = new JButton("Cerrar");
		btn_cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(WIDTH);
			}
		});
		btn_cerrar.setBounds(1066, 620, 117, 25);
		contentPane.add(btn_cerrar);
		
		//TITULO DEL FORMULARIO
		lbl_control_gastos = new JLabel("Control de Gastos");
		lbl_control_gastos.setFont(new Font("Noto Sans CJK KR", Font.BOLD, 20));
		lbl_control_gastos.setForeground(new Color(106, 90, 205));
		lbl_control_gastos.setBounds(47, 69, 191, 33);
		contentPane.add(lbl_control_gastos);
		
		lbl_titular = new JLabel("Titular del ingreso");
		lbl_titular.setBounds(47, 147, 155, 26);
		contentPane.add(lbl_titular);
		
		cbo_titular = new JComboBox<String>();
		cbo_titular.addItem("Seleccione el titular");
		cbo_titular.setBounds(209, 165, 284, 24);
		contentPane.add(cbo_titular);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(209, 361, 273, 19);
		contentPane.add(dateChooser);
		
		cbo_tipo_gasto = new JComboBox<String>();
		cbo_tipo_gasto.addItem("Seleccione el tipo de gasto");
		cbo_tipo_gasto.setBounds(209, 317, 273, 24);
		contentPane.add(cbo_tipo_gasto);
		
		JLabel lbl_tipo_ingreso = new JLabel("Tipo de Ingreso");
		lbl_tipo_ingreso.setBounds(47, 528, 144, 26);
		contentPane.add(lbl_tipo_ingreso);
		
		cbo_tipo_ingreso = new JComboBox<String>();
		cbo_tipo_ingreso.addItem("Seleccione el tipo de ingreso");
		cbo_tipo_ingreso.setBounds(209, 529, 273, 24);
		contentPane.add(cbo_tipo_ingreso);
		
		JLabel lbl_fecha_ingreso = new JLabel("Fecha del Ingreso");
		lbl_fecha_ingreso.setBounds(47, 558, 126, 26);
		contentPane.add(lbl_fecha_ingreso);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(209, 565, 273, 19);
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
		
		java.sql.Date dato =null;
		
		//CONVERTIMOS LA VARIABLE DE STRING A DATE (PERO USANDO LA CLASE JAVA.SQL.DATE)
		//IMPORTANTE!!
			dato=java.sql.Date.valueOf(fecha1);
		
			Date selectedDate = dateChooser_1.getDate();
			
		if (cbo_titular.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "Debe ingresar el titular del ingreso", "Error", JOptionPane.WARNING_MESSAGE);
			}
			/*
			 * 
			 * 
			 * VALIDACIONES DEL CAMPO MONTO INGRESO:
			 */
	// 01. VALIDA CAMPO NULL:
		else if(txt_monto_ingreso.getText().trim().isEmpty()) {
				  JOptionPane.showMessageDialog(null, "Debe ingresar el monto del ingreso", "Error", JOptionPane.WARNING_MESSAGE);
	// 02. VALIDA CON FUNCION EXPRESION REGULAR (MAX 9 ENTEROS Y 2 DECIMALES)			  
		}else if (!validaciones.validarNumeros(txt_monto_ingreso.getText().trim())){
				JOptionPane.showMessageDialog(null, "El monto del ingreso debe ser numérico, positivo, con un máximo de 9 enteros y 2 decimales", "Error Monto Ingreso", JOptionPane.WARNING_MESSAGE);
	// 03. VALIDA CAMPO EN 0(CERO) 			
		}else if (Double.parseDouble(txt_monto_ingreso.getText().trim())==0){
				JOptionPane.showMessageDialog(null, "El monto del ingreso debe ser numérico, positivo, con un máximo de 9 enteros y 2 decimales", "Error Monto Gasto", JOptionPane.WARNING_MESSAGE);
			/*
			 * 
			 * 
			 * VALIDACION DEL CAMPO TIPO INGRESO:
			 */
		}else if (cbo_tipo_ingreso.getSelectedIndex()==0) {
				
				JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de ingreso", "Error", JOptionPane.WARNING_MESSAGE);
			}else if (selectedDate == null) {
				JOptionPane.showMessageDialog(null, "Debe ingresar la fecha del ingreso", "Error", JOptionPane.WARNING_MESSAGE);
			/*
			 * 
			 * 
			 * VALIDACION DEL CAMPO FECHA:
			 */
		  }else if (selectedDate.after(new Date())) {
	 // SI LA FECHA SELECCIONADA ES POSTERIOR A LA FECHA ACTUAL ENTONCES ERROR 
	JOptionPane.showMessageDialog(null, "La fecha seleccionada debe ser anterior o igual a la fecha actual", "Error", JOptionPane.WARNING_MESSAGE);
			    	
			    } else {
		   	//CORRECTO: LA FECHA SELECCIONADA ES ANTERIOR A LA FECHA ACTUAL
			//GUARDA EN LA BASE DE DATOS EL GASTO (TABLA LM_GASTOS)		
		
	/*
	 * 
	 * GUARDA EN LA BASE DE DATOS TODOS LOS INGRESOS (TABLA LM_INGRESOS)
	 * 		    	
	 */
		guardar.guardarIngresos(valorComboBox, valorComboBox1, valorTextField, dato);
				
			   }
			
			}
			
		});
		
		btn_grabar_ingreso.setBounds(534, 501, 117, 25);
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
		btn_nuevo_ingreso.setBounds(534, 559, 117, 25);
		contentPane.add(btn_nuevo_ingreso);
		
		JLabel lbl_estado_rdo = new JLabel("Estado de Resultados");
		lbl_estado_rdo.setForeground(new Color(106, 90, 205));
		lbl_estado_rdo.setFont(new Font("Noto Sans CJK KR", Font.BOLD, 20));
		lbl_estado_rdo.setBounds(787, 69, 224, 33);
		contentPane.add(lbl_estado_rdo);
		
		JLabel lbl_titular_1 = new JLabel("Mes");
		lbl_titular_1.setBounds(763, 165, 85, 26);
		contentPane.add(lbl_titular_1);
		
		JLabel lbl_titular_1_1 = new JLabel("Año");
		lbl_titular_1_1.setBounds(763, 253, 85, 26);
		contentPane.add(lbl_titular_1_1);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(862, 170, 122, 19);
		contentPane.add(monthChooser);
		
		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(873, 260, 53, 19);
		contentPane.add(yearChooser);
		
		
		JButton btn_calcular = new JButton("Calcular");
		btn_calcular.addActionListener(new ActionListener() {
			
	
			public void actionPerformed(ActionEvent e) {
				
	
			  // Obtener la fecha seleccionada por el usuario desde el JDateChooser
		       // JDateChooser dateChooser = new JDateChooser();
		        int mesSeleccionado = monthChooser.getMonth()+1;		       
		        int anioSeleccionado = yearChooser.getYear();
		       

		        // Crear un objeto Calendar y establecerlo con la fecha seleccionada
		        Calendar calendar = Calendar.getInstance();
		   

	// Obtener el mes actual desde el objeto Calendar
   // MesSeleccionado = calendar.get(Calendar.MONTH); // Enero es 0, Febrero es 1, ...
		        int year = yearChooser.getYear();
		        int month = monthChooser.getMonth();
		        
		        LocalDate fechaActual= LocalDate.now();
		        int mesActual=fechaActual.getMonthValue();
		        int anioActual=fechaActual.getYear();

		        
		        Pair<String, String> fechas = FechaIF.obtenerFechasInicioFin(year, month);
		        String fechaDesde = fechas.getFirst();
		        String fechaHasta = fechas.getSecond();
		        String vacio = 0 + ""; //PARA RESETEAR LOS MESES FUTUROS
		        
	// Crear un array para almacenar las sumatorias de cada mes (12 meses)
	         double[] sumatoriaPorMes = new double[12]; //array
		        	        	  
		          
		     for (int i = 0; i < sumatoriaPorMes.length; i++) {

		        if (anioSeleccionado == anioActual) {
		          if(mesSeleccionado <= mesActual) {	
		            if (i == mesSeleccionado) {
		                sumatoriaPorMes[i] = calc.sumatorias(i,"LM_INGRESOS","total", text_total_ingresos,fechaDesde,fechaHasta);
		                sumatoriaPorMes[i] = calc.sumatorias(i,"LM_GASTOS","total", text_total_egresos,fechaDesde,fechaHasta);
		                sumatoriaPorMes[i] = calc.saldo(i,"LM_INGRESOS","LM_GASTOS","resultado",text_saldo,fechaDesde,fechaHasta);

		            }else {	            	
		            	sumatoriaPorMes[i] = 0;
			            }
		          }else {
						text_total_ingresos.setText(vacio);
						text_total_egresos.setText(vacio);
						text_saldo.setText(vacio); 
		            
		          } //CIERRA 2DO IF
		       }else { //ELSE DEL 1ER IF
	                sumatoriaPorMes[i] = calc.sumatorias(i,"LM_INGRESOS","total", text_total_ingresos,fechaDesde,fechaHasta);
	                sumatoriaPorMes[i] = calc.sumatorias(i,"LM_GASTOS","total", text_total_egresos,fechaDesde,fechaHasta);
	                sumatoriaPorMes[i] = calc.saldo(i,"LM_INGRESOS","LM_GASTOS","resultado",text_saldo,fechaDesde,fechaHasta);
					
		        }
		      } //CIERRE DEL FOR   
			}  	
		});
		btn_calcular.setBounds(810, 355, 91, 25);
		contentPane.add(btn_calcular);
		
		JButton btn_imprimir = new JButton("Limpiar");
		btn_imprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_total_ingresos.setText(null);
				text_total_egresos.setText(null);
				text_saldo.setText(null);
				
			}
		});
		btn_imprimir.setBounds(943, 355, 91, 25);
		contentPane.add(btn_imprimir);
		
		JLabel lbl_total_ingresos = new JLabel("Total Ingresos");
		lbl_total_ingresos.setBounds(747, 458, 109, 26);
		contentPane.add(lbl_total_ingresos);
		
		JLabel lbl_total_egresos = new JLabel("Total Egresos");
		lbl_total_egresos.setBounds(747, 491, 109, 26);
		contentPane.add(lbl_total_egresos);
		
		JLabel lbl_saldo = new JLabel("Saldo");
		lbl_saldo.setBounds(747, 531, 109, 26);
		contentPane.add(lbl_saldo);
		

		

		
		
		//----------------RELLENADO DE COMBOS-------------------//
			
	re.RellenarComboBox("LM_TITULAR", "nombre", cbo_titular);
	re.RellenarComboBox("LM_TIPO_GASTO","descripcion", cbo_tipo_gasto);
	re.RellenarComboBox("LM_TIPO_INGRESO", "descripcion", cbo_tipo_ingreso);
	
	
	text_total_ingresos = new JTextField();
	text_total_ingresos.setBounds(873, 465, 114, 19);
	contentPane.add(text_total_ingresos);
	text_total_ingresos.setColumns(10);
	
	text_total_egresos = new JTextField();
	text_total_egresos.setColumns(10);
	text_total_egresos.setBounds(873, 501, 114, 19);
	contentPane.add(text_total_egresos);
	
	text_saldo = new JTextField();
	text_saldo.setColumns(10);
	text_saldo.setBounds(873, 538, 114, 19);
	text_saldo.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
	contentPane.add(text_saldo);
	
	JLabel lbl_titular_2 = new JLabel("o del gasto");
	lbl_titular_2.setBounds(47, 164, 155, 26);
	contentPane.add(lbl_titular_2);
	
	/*
	 * 
	 * 
	 * BARRA DE MENU:
	 */
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(3, -3, 1534, 29);
	contentPane.add(menuBar);
	
	/*
	 * 
	 * 
	 * MENUS:
	 */
	JMenu archivo=new JMenu("Archivo");
	JMenu edicion=new JMenu("Edicion");
	JMenu ver=new JMenu("Ver");
	JMenu herramientas=new JMenu("Herramientas");
	JMenu ayuda=new JMenu("Ayuda");
	
	/*
	 * 
	 * ITEMS:
	 */
	JMenuItem guardar2=new JMenuItem("Guardar");
	JMenuItem guardar_como=new JMenuItem("Guardar Como");
	JMenuItem salir=new JMenuItem("Salir");
	salir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(WIDTH);
		}
	});
	JMenuItem reportes=new JMenuItem("Reportes");
	JMenuItem doc=new JMenuItem("Documentación");
	JMenuItem cortar=new JMenuItem("Cortar");
	JMenuItem copiar=new JMenuItem("Copiar");
	JMenuItem pegar=new JMenuItem("Pegar");
	JMenuItem seleccionar_todo=new JMenuItem("Seleccionar Todo");
	JMenuItem acerca_de=new JMenuItem("Acerca de lucymar_app");
	JMenuItem actualizar=new JMenuItem("Actualizar");
	JMenuItem detalle_ingresos=new JMenuItem("Detalle Ingresos");
	detalle_ingresos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FrmDetalles detalle = new FrmDetalles();
			detalle.setVisible(true);
			setLocationRelativeTo(null);
		}
	});
	JMenuItem detalle_egresos=new JMenuItem("Detalle Egresos");
	JMenuItem estadisticas=new JMenuItem("Estadisticas");
	estadisticas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			
			FrmEstadisticas estadistica = new FrmEstadisticas();
			estadistica.setVisible(true);
			setLocationRelativeTo(null);		
			
		}
		
	});
	
	
	
	/*
	 * 
	 * 
	 * AGREGAMOS LOS ITEMS AL MENU ARCHIVO:
	 */
	archivo.add(guardar2);
	archivo.add(guardar_como);
	archivo.add(salir);
	
	/*
	 * 
	 * 
	 * AGREGAMOS LOS ITEMS AL MENU HERRAMIENTAS:
	 */
	herramientas.add(reportes);
	herramientas.add(estadisticas);
	
	
	/*
	 * 
	 * 
	 * AGREGAMOS LOS ITEMS AL MENU VER:
	 */
	ver.add(doc);
	ver.add(detalle_ingresos);
	ver.add(detalle_egresos);
	
	/*
	 * 
	 * 
	 * AGREGAMOS LOS ITEMS AL MENU EDICION:
	 */
	edicion.add(cortar);
	edicion.add(copiar);
	edicion.add(pegar);
	edicion.add(seleccionar_todo);
	
	/*
	 * 
	 * 
	 * AGREGAMOS LOS ITEMS AL MENU AYUDA:
	 */
	ayuda.add(actualizar);
	ayuda.add(acerca_de);
	
	
	/*
	 * 
	 * 
	 * AGREGAMOS LOS MENUS A LA BARRA:
	 */
	menuBar.add(archivo);
	menuBar.add(ver);
	menuBar.add(edicion);
	menuBar.add(herramientas);
	menuBar.add(ayuda);
	
	getContentPane().add(menuBar);
	
	JButton btnDetalleIngresos = new JButton("Ver Detalle");
	//GLOBO ALERT		
	btnDetalleIngresos.setToolTipText("Detalle de Ingresos");
	btnDetalleIngresos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		FrmDetalles detalle = new FrmDetalles();
		detalle.setVisible(true);
		setLocationRelativeTo(null);
			
		}
	});
	btnDetalleIngresos.setBounds(999, 462, 117, 19);
	contentPane.add(btnDetalleIngresos);
	
	JButton btnDetalleGastos = new JButton("Ver Detalle");
	//GLOBO ALERT		
	btnDetalleGastos.setToolTipText("Detalle de Gastos");
	btnDetalleGastos.setBounds(999, 500, 117, 19);
	contentPane.add(btnDetalleGastos);
	
	txt_monto_ingreso = new JTextField();
	txt_monto_ingreso.setToolTipText("No se permite la coma como separador decimal");
	txt_monto_ingreso.setColumns(10);
	txt_monto_ingreso.setBounds(209, 477, 136, 28);
	contentPane.add(txt_monto_ingreso);
	
	JLabel lbl_monto_ingreso = new JLabel("Monto Ingreso");
	lbl_monto_ingreso.setBounds(47, 477, 144, 26);
	contentPane.add(lbl_monto_ingreso);
	
	//REQUERIMIENTO OBJETIVO MENSUAL:  ENE 2024
	
	JRadioButton radio_gasto_pesos = new JRadioButton("Pesos");
	radio_gasto_pesos.setBounds(353, 262, 75, 23);
	contentPane.add(radio_gasto_pesos);
	
	//radio_gasto_pesos.setSelected(true);
	
	JRadioButton radio_gasto_dolares = new JRadioButton("Dolares");
	radio_gasto_dolares.setBounds(436, 262, 85, 23);
	contentPane.add(radio_gasto_dolares);
	
	JRadioButton radio_ingreso_pesos = new JRadioButton("Pesos");
	radio_ingreso_pesos.setBounds(353, 477, 75, 23);
	contentPane.add(radio_ingreso_pesos);
	
	JRadioButton radio_ingreso_dolares = new JRadioButton("Dolares");
	radio_ingreso_dolares.setBounds(436, 477, 85, 23);
	contentPane.add(radio_ingreso_dolares);
	
	
	} //CIERRE DEL CONSTRUCTOR	
		

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
	public JLabel lbl_control_gastos;
	public JLabel lbl_titular;

	 PreparedStatement pstmt =null;
	 private JTextField text_total_ingresos, text_total_egresos, text_saldo;
	 private JTextField txt_monto_ingreso;
}