package ex1;

import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		
		MyPanel p= new MyPanel();
		//Se estable tamaño, no permite redimensionado, cerrado y visibilidad
		p.setBounds(0, 0, 350, 170);
		p.setResizable(false);
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setVisible(true);

	}

}
