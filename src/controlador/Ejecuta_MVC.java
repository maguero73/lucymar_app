package controlador;

import java.awt.EventQueue;

import vista.FrmPersonas;

public class Ejecuta_MVC {
	
	

//EJECUTA LA APLICACION
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPersonas frame = new FrmPersonas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
