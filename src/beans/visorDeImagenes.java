package beans;


import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class visorDeImagenes extends JLabel{
	
	public visorDeImagenes(){
		
		setBorder (BorderFactory.createEtchedBorder());
		
		
		
	}
	
	
	public void setEscogeImagen(String ElegirImagen) {
		
		try {
			
			archivo =new File(ElegirImagen);
			setIcon(new ImageIcon(ImageIO.read(archivo)));
			
			
		}catch(IOException e) {
			
			archivo=null;
			
			setIcon(null);
		}
		
	}

	public String getEscogeImagen() {
		
		if(archivo==null) return null;
		
		else return archivo.getPath();
	}
	
	public Dimension getPreferredSize() {
		
		return new Dimension(600,400);
	}
	
	private File archivo=null;
}
