package controlador;


import java.awt.EventQueue;
import modelo.ActualizarTipoCambioEnBaseDeDatos;

import vista.FrmPersonas;

public class Ejecuta_MVC {
	
	

//EJECUTA LA APLICACION
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPersonas frame = new FrmPersonas();
					frame.setVisible(true);
					ActualizarTipoCambioEnBaseDeDatos.EjecutarActualizacion();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
