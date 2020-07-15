/*Autor: Grimaldo Peralta Marco */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class practica1 extends Applet implements ActionListener
{
	Button c_k,k_c;
	TextField t;
	Label l,l0,l1;

	public practica1(){}

	public void init()
	{
		int num = 0;
		c_k = new Button("Celsius - Kelvin");
		k_c = new Button("Kelvin - Celsius");
		t = new TextField(30);
		l = new Label("Ingrese Temperatura");
		l1 = new Label("Resultado:");
		l0 = new Label("      ");

		add(l); add(t); add(c_k); add(k_c); add(l1); add(l0);

		c_k.addActionListener(this);
		k_c.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		int num=0;

		Button b = (Button)e.getSource();
		num = Integer.parseInt(t.getText());

		if(b == c_k)
			num = num + 273;

		if( b == k_c)
			num = num - 273;

		l0.setText(String.valueOf(num));
	}

	public static void main(String s[])
	{
		practica1 p = new practica1();

		p.init(); p.start();

		Frame f = new Frame("Practica_1");
		f.add("Center",p);
		f.setSize(250,280);
		f.setVisible(true);

	}

}
