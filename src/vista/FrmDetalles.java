package vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrmDetalles extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDetalles frame = new FrmDetalles();
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
	public FrmDetalles() {
		
		setTitle("Detalle Ingresos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,300);

        // Obtén el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcula las coordenadas x e y para centrar el JFrame
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        // Establece la ubicación del JFrame en el centro de la pantalla
        setLocation(x, y);
		setBounds(300, 150, 800, 600);
		contentPane = 																																																																																								new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
