/*Autor: Grimaldo Peralta Marco */

import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class InsertaPerros{

	public static void main(String[] args) {
		ventana obj = new ventana();
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}


 class ventana extends JFrame{

	public ventana(){

		setVisible(true);
		setTitle("Inserta Perros");
		setSize(250,220);
		setLocation(300,150);
		add(new mipanel());
	}
}

 class mipanel extends JPanel implements ActionListener{


		Label nombre,raza,genero,edad;
		JButton conectar, insertar;
		TextField campo1,campo2,campo3,campo4;
		String nom,raz,gen;
		int ed;
		String url ="jdbc:mysql://localhost:3306/DBPerros";
		String sentencia= "INSERT INTO perro(nombre,raza,edad,genero)";
		Connection conex;
		String datos; 
		Statement stmt; 
		


	public mipanel(){

		conectar = new JButton("Conectar");
		insertar = new JButton("Insertar");


		nombre=new Label("Nombre: ");
		campo1=new TextField(20);
		raza=new Label("Raza:     ");
		campo2=new TextField(20);
		edad=new Label("Edad:    ");
		campo3=new TextField(20);
		genero=new Label("Genero: ");
		campo4=new TextField(20);

		add(nombre); add(campo1);
		add(raza); add(campo2);
		add(edad); add(campo3);
		add(genero); add(campo4);

		conectar.addActionListener(this); 
		add(conectar); add(insertar);

	}

	public void actionPerformed(ActionEvent e){

	

		JButton btn = (JButton) e.getSource();

		if (btn==conectar) {		
				try{
					Class.forName("com.mysql.jdbc.Driver");
					conex = DriverManager.getConnection(url,"root","root");
					conectar.removeActionListener(this);
					insertar.addActionListener(this);
				}catch(Exception ex){

					ex.printStackTrace();
				}
		}else{

			nom=campo1.getText();
			raz=campo2.getText();
			gen=campo4.getText();
			ed=Integer.parseInt(campo3.getText());

			try{

			datos = "VALUES ('"+nom+"'"+",'"+raz+"'"+","+ed+","+"'"+gen+"');";
			stmt = conex.createStatement();
			stmt.executeUpdate(sentencia+datos);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}


	
	}

}

