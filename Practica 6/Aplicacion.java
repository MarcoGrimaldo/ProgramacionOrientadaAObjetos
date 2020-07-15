/*Autor: Grimaldo Peralta Marco */

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Aplicacion extends JFrame implements ActionListener
{
	final String host = "localhost";
	final int puerto = 9999;
	
	Socket conexion;
	
	PrintStream salida;
	DataInputStream entrada;
	String s;
	
	JTextField texto;
	JLabel sal;
	JButton enviar;
	JLabel rob;
	ImageIcon robot;


	public Aplicacion()
	{
		super("CHATBOT");
		
		texto = new JTextField();
		sal = new JLabel("Preguntame algo...\n");
		enviar = new JButton("Enviar ");
		robot = new ImageIcon("robot.gif");
		rob = new JLabel();
		
		enviar.addActionListener(this);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(700,700);
		setVisible(true);
		elementos();
	}
	
	void elementos()
	{	
	setLayout(null);
	this.getContentPane().setBackground(Color.black);
	add(rob);
	add(sal);
	add(texto);
	add(enviar);
		
		rob.setBounds(0,0,600,400);
		rob.setIcon(robot);
		sal.setBounds(0,400,600,60);
		sal.setBorder(BorderFactory.createLineBorder(Color.black));
		sal.setBackground(Color.black);
		sal.setForeground(Color.white);
		texto.setBounds(10,480,400,40);
		texto.setBorder(BorderFactory.createLineBorder(Color.white));
		enviar.setBounds(470,490,85,20);
		
	}
	public void actionPerformed(ActionEvent a)
	{
		try
		{
			conexion = new Socket(host, puerto);
			
			entrada = new DataInputStream(conexion.getInputStream());
			salida = new PrintStream(conexion.getOutputStream());
			
			salida.println(texto.getText());
			sal.setText(" " +texto.getText()+"\n");
			texto.setText(null);
			s = entrada.readLine();
			sal.setText(" " +s+"\n");
		}
		catch (Exception e)
		{
			sal.setText("Error: " + e.getMessage());
			System.out.print("Error: " + e.getMessage());
		}
	}
	
public static void main(String args[])
	{
		new Aplicacion();
	}
}