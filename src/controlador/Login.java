package controlador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import javax.swing.border.Border;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.RellenarCombos;
import modelo.calculos;
import modelo.guardarDatos;
import oracle.sql.DATE;
import vista.FrmPersonas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;

public class Login extends JFrame {
	protected static final JPasswordField Password = new JPasswordField();
	private JTextField txtusuario;
	private JPasswordField txtcontraseña;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	//METODO CONSTRUCTOR
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 559, 360);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JLABEL
		JLabel lbl_tipo_gasto = new JLabel("Usuario");
		lbl_tipo_gasto.setBounds(63, 108, 73, 26);
		contentPane.add(lbl_tipo_gasto);
		
		JLabel lbl_tipo_gasto_1 = new JLabel("Contraseña");
		lbl_tipo_gasto_1.setBounds(63, 158, 85, 26);
		contentPane.add(lbl_tipo_gasto_1);
		
		JButton btn_aceptar = new JButton("Aceptar");
		btn_aceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Usuario = "admin";
				String password = "admin";
				String Usuario1 = "admin1";
				String password1 = "admin1";
				
		if (txtusuario.getText().equals(Usuario)&& txtcontraseña.getText().equals(password)){
				FrmPersonas persona = new FrmPersonas();
				persona.setVisible(true);
				dispose();
				}
			 else if (txtusuario.getText().equals(Usuario1)&& txtcontraseña.getText().equals(password1)) {
					FrmPersonas persona = new FrmPersonas();
					persona.setVisible(true);
					dispose();
				}else {
					FrmPersonas persona = new FrmPersonas();
					persona.setVisible(true);
					dispose();
				}
				
			}
		});
		btn_aceptar.setBounds(374, 221, 101, 25);
		contentPane.add(btn_aceptar);
		
		txtusuario = new JTextField();
		txtusuario.setBounds(227, 111, 223, 22);
		contentPane.add(txtusuario);
		txtusuario.setColumns(10);
		
		txtcontraseña = new JPasswordField();
		txtcontraseña.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {			
					String Usuario = "admin";
					String password = "admin";
					
					//String Pass=new String (Password.getPassword());
			if (txtusuario.getText().equals(Usuario)&& txtcontraseña.getText().equals(password)){
					FrmPersonas persona = new FrmPersonas();
					persona.setVisible(true);
					dispose();
					}else {
						JOptionPane.showInternalMessageDialog(null,"usuario / contraseña incorrecta");
					}
				}
			}
		});
		txtcontraseña.setBounds(225, 162, 225, 19);
		contentPane.add(txtcontraseña);
		
		JLabel lblNewLabel = new JLabel("Bienvenido Usuario");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setBounds(72, 32, 287, 45);
		contentPane.add(lblNewLabel);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cancelar.setBounds(250, 221, 101, 25);
		contentPane.add(btn_cancelar);
		
	}
}
