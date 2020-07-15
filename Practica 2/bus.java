/*Autor: Grimaldo Peralta Marco */

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;

public class bus extends JFrame implements ActionListener
{
	JButton asientos[],ocuB;
	JPanel panelSuperior;
	JPanel panelInferior;
	JLabel noAsientos[];
	JFrame frame;
	ImageIcon icoDis = new ImageIcon("asientoDis.jpg");
	ImageIcon icoOcu = new ImageIcon("asientoOcu.jpg");
	JLabel asiOcu;
	int ocu = 0,n = 0;
	String num = " ";

	public bus()
	{
		muestra();
		grid();
		ventana();
	}

	public void muestra()
	{
		panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout());
		ocuB = new JButton("Ocupados:");
		asiOcu = new JLabel("                          ");
		panelSuperior.add(ocuB);
		panelSuperior.add(asiOcu);
		ocuB.addActionListener(this);

	}

	public void grid()
	{
		icoDis = new ImageIcon("asientoDis.jpg");
		icoOcu = new ImageIcon("asientoOcu.jpg");
		asientos = new JButton[44];
		noAsientos = new JLabel[44];
		panelInferior= new JPanel();
		panelInferior.setLayout(new GridLayout(4,11));
		for(int i = 0; i < asientos.length ; i++)
		{
			panelInferior.add(noAsientos[i] = new JLabel(""+(i+1)));
			panelInferior.add(asientos[i] = new JButton(""+(i+1)+","));
			asientos[i].setIcon(icoDis);
			asientos[i].addActionListener(this);
		}
	}

	public void ventana()
	{
		frame = new JFrame("Autobus");
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		frame.add(panelSuperior);
        	frame.add(panelInferior);
        	frame.pack();
		frame.setSize(800, 260);
        	frame.setVisible(true);
        	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e)
	{

		JButton b=(JButton)e.getSource();

		if(b == ocuB)
		{
			if(ocu == 0)
				asiOcu.setText("No hay asientos ocupados");
			else
				asiOcu.setText(num);
		}

		else
		{
			b.setIcon(icoOcu);
			num = num + b.getText();
			ocu++;
		}
	}

	public static void main(String s[])
	{
		new bus();

	}

}
