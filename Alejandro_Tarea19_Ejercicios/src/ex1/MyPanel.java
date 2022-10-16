package ex1;

import javax.swing.*;
import java.awt.event.*;

public class MyPanel extends JFrame implements ActionListener {
    private JTextField textfield1;
    private JLabel label1;
    private JButton boton1;
    private JLabel label2;
    
    public MyPanel() {
    	
    	//Definicion de la estructua. Se añaden los actionListener
        setLayout(null);
        setTitle("Saludador");
        label1=new JLabel("Nombre:");
        label1.setBounds(10,10,100,30);
        label2=new JLabel("Escribe un nombre para saludar");
        label2.setBounds(10,50,200,30);
        add(label1);
        add(label2);
        textfield1=new JTextField();
        textfield1.setBounds(120,10,150,20);
        add(textfield1);
        boton1=new JButton("Aceptar");
        boton1.setBounds(10,80,100,30);
        add(boton1);
        boton1.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
    	String name;
    	//Al pulsar el botón aceptar se obtiene el contenido del TextField
    	//Se pasa el resultado a un string, que se muestra en un JOptionPane.
        if (e.getActionCommand()=="Aceptar") {
        	name=textfield1.getText();
        	JOptionPane.showMessageDialog(null, "¡Hola "+name+"!");
        }
    }
}
