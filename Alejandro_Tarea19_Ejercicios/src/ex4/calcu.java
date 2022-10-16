package ex4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class calcu extends JFrame {
	
	/*
	 * Definición de los elementos gráficos de la pantalla
	 */
	private JLabel lblResultado = new JLabel(" ");
	private JPanel pnlBotones = new JPanel(new GridLayout(4, 4));
	private JPanel pnlIgual = new JPanel(new GridLayout(1, 1));
	private JButton[] botones = { new JButton("1"), new JButton("2"), new JButton("3"), new JButton("+"),
			new JButton("4"), new JButton("5"), new JButton("6"), new JButton("-"), new JButton("7"), new JButton("8"),
			new JButton("9"), new JButton("*"), new JButton("C"), new JButton("0"), new JButton(","), new JButton("/"),
			new JButton("=") };
	private Dimension dmVentana = new Dimension(300, 440);
	
	private double resultado = 0;
	private double numero;
	
	//Variables control programa
	private static final int SUMA = 1;
	private static final int RESTA = 2;
	private static final int MULTIPLICACION = 3;
	private static final int DIVISION = 4;
	private static final int NINGUNO = 0;
	private int operador = calcu.NINGUNO;
	private boolean hayPunto = false;
	private boolean nuevoNumero = true;
	private NumberFormat nf = NumberFormat.getInstance();

	public calcu() {
		/*
		 * Se preparan las dimensiones de la pantalla.
		 * Se coloca el titulo de la pantalla
		 */
		Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dmPantalla.width - dmVentana.width) / 2;
		int y = (dmPantalla.height - dmVentana.height) / 2;
		this.setLocation(x, y);
		this.setSize(dmVentana);
		this.setTitle("Calculadora");
		/*
		 * Se establecen parámetros estéticos como el fondo, opacidad, y letra
		 */
		lblResultado.setBackground(Color.white);
		lblResultado.setOpaque(true);
		lblResultado.setFont(new Font("Arial", Font.PLAIN, 32));
		PulsaRaton pr = new PulsaRaton();
		
		/*
		 * Mediante el bucle se añaden los botones (numeros y operadores= y se añaden los listeners.
		 */
		for (int i = 0; i < botones.length - 1; i++) {
			pnlBotones.add(botones[i]);
			botones[i].addActionListener(pr);
		}

		//Se modifica el tamaño(longitud) del botón igual.
		pnlIgual.add(botones[botones.length - 1]);
		botones[botones.length - 1].addActionListener(pr);

		pnlIgual.setPreferredSize(new Dimension(0, 50));
		
		//Se añade la sección de resultado(parte superior) y el igual (parte inferior)
		
		this.add(lblResultado, BorderLayout.NORTH);
		this.add(pnlBotones);
		this.add(pnlIgual, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		lblResultado.setText("0,0");
		lblResultado.setHorizontalAlignment(JLabel.RIGHT);
	}

	class PulsaRaton implements ActionListener {
		/*La clase PulsaRaton implementa ActionListener, por lo que se debe sobreescribir 
		 * el método actionPerformed. 
		 * 
		 */
		@Override
	 public void actionPerformed(ActionEvent e) {
			
	/*
	 * Gracias al método getSource se puede recuperar el botón
		 * que el usuario ha pulsado. Se obtenie el texto del botón. Con este switch, sirve de variable de
		 * control en un switch. En caso de pulsar uno de las diferentes operaciones, accederemos al método operar con una
		 * de las diferentes constantes (int). Tambien se regula los casos donde se produce la pulsacion de una coma y el igual (control
		 * de null).
	 */
	 JButton origen = (JButton) e.getSource();
	 String texto = origen.getText();
	 switch (texto) {
	 case "+":
	 operar(calcu.SUMA);
	break;
	 case "-":
	 operar(calcu.RESTA);
	break;
	 case "*":
	 operar(calcu.MULTIPLICACION);
	break;
	 case "/":
	 operar(calcu.DIVISION);
	break;
	 case ",":
	 if (!nuevoNumero){
	 if (!hayPunto){
	 String rdo = lblResultado.getText();
	lblResultado.setText(rdo+",");
	 }
	 } else {
	 lblResultado.setText("0,");
	nuevoNumero = false;
	 }
	hayPunto = true;
	break;
	 case "C":
	 lblResultado.setText("0,0");
	 
	 nuevoNumero = true;
	 hayPunto = false;
	 break;
	  case "=":
	  if (operador != calcu.NINGUNO){
	  String rdo = lblResultado.getText();
	  if (!rdo.isEmpty()){
	  Number n = null;
	 try {
	  n = (Number) nf.parse(rdo);
	 numero = n.doubleValue();
	  } catch (ParseException ex) {
	  numero = 0;
	  }
	 switch (operador) {
	  case calcu.SUMA:
	  resultado += numero;
	 break;
	  case calcu.RESTA:
	  resultado -= numero;
	 break;
	  case calcu.MULTIPLICACION:
	  resultado *= numero;
	 break;
	  case calcu.DIVISION:
	  resultado /= numero;
	  break;
	  default:
	  resultado = numero;
	 break;
	  }
	 operador = calcu.NINGUNO;
	  lblResultado.setText(nf.format(resultado));
	  }
	  }
	 break;
	  default:
	  String rdo = lblResultado.getText();
	 if (nuevoNumero){
	  lblResultado.setText(texto);
	  } else {
	  lblResultado.setText(rdo + texto);
	  }
	 nuevoNumero = false;
	 break;
	  }
	  }
	}

	public void operar(int operacion) {
		if (!nuevoNumero) {
			String rdo = lblResultado.getText();
			if (!rdo.isEmpty()) {
				Number n = null;
				try {
					n = (Number) nf.parse(rdo);
					numero = n.doubleValue();
				} catch (ParseException ex) {

				}
				switch (operador) {
				case calcu.SUMA:
					resultado += numero;
					break;
				case calcu.RESTA:
					resultado -= numero;
					break;
				case calcu.MULTIPLICACION:
					resultado *= numero;
					break;
				case calcu.DIVISION:
					resultado /= numero;
					break;
				default:
					resultado = numero;
				}
				operador = operacion;
				lblResultado.setText(nf.format(resultado));
				nuevoNumero = true;
				hayPunto = false;
			}
		}
	}
}
