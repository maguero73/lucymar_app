package beans;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class usoBeanPropio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usoBeanPropio frame = new usoBeanPropio();
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
	public usoBeanPropio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 400, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		visorDeImagenes visorDeImagenes_ = new visorDeImagenes();
		visorDeImagenes_.setEscogeImagen("/home/mariano/eclipse-workspace/lucymar_app/logo2.jpg");
		visorDeImagenes_.setText("imagen");
		contentPane.add(visorDeImagenes_);
	}

}
