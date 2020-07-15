/*Autor: Grimaldo Peralta Marco */

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.RMISecurityManager;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;	
import java.awt.*;

public class cliente extends JFrame implements ActionListener{
		
	String respuesta;
	String pregunta;
	String s,nick,host;
	

	JTextField texto;
	JLabel sal;
	JButton enviar;
	JLabel rob;
	ImageIcon robot;
				

	public cliente()
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
		componentes();
	}
	
	public void componentes()
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
		System.setSecurityManager(new RMISecurityManager());
		pregunta = texto.getText();
							
		if (pregunta.length() != 0){
								
		sal.setText(">" +texto.getText()+"\n");
		texto.setText(null);
		//try{
			IPregunta Q = (IPregunta) Naming.lookup ("questions");
			respuesta = Q.LeePregunta(pregunta);
			sal.setText("+"+respuesta+"\n");
		//}catch(Exception e){sal.setText("Valio verga la paa del mameitor");return;}
								
								
		}
	}

	public static void main(String[] args) {

		cliente obj =new cliente();
	
	}
}