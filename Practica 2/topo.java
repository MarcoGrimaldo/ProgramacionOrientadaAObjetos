import java.awt.*; 
import java.applet.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.until.*;

public class topo extends Frame implements ActionListener{

	JButton casillas[];
	JPanel verPuntos;
	JLabel puntos;
	JPanel celdas;
	JFrame f;
	ImageIcon topoI = new ImageIcon("topo.png");
	int z,punt = 0;

	public topo(){
		
		verPuntos = new JPanel();
		verPuntos.setLayout(new FlowLayout());
		JLabel pts = new JLabel("Puntos: "); verPuntos.add(pts);
		puntos = new JLabel(""+punt); verPuntos.add(puntos);

		celdas = new JPanel();
		celdas.setLayout(new GridLayout(4,5));
		casillas = new JButton[20];
		for(int i = 0; i < casillas.length ; i++){
			celdas.add(casillas[i] = new JButton(""));
			casillas[i].addActionListener(this);
		}


	}

	TimerTask timer = new TimerTask(){
		public void run(){
			z = random();
			casillas[z].setIcon(topoI);
		}
	}

	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();

		if(b == casillas[z])
		{
			punt++;
			new topo();
		}



	}

	
	public int random(){
		Random n = new Random();

		int num = 0+n.nextInt(20);

		return num;
	}

	public static void main(String s[]){
		frame = new JFrame("Juego del topo");
		frame.add(verPuntos);
        	frame.add(celdas);
		frame.setSize(500,500);
        	frame.setVisible(true);

		new topo();
	}
}
