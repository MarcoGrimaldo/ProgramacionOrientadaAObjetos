/*Autor: Grimaldo Peralta Marco Antonio */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class examen extends Applet implements ActionListener
{
	Button sig;
	TextField resp;
	Label preg,rdo;
	String pre[] = {"1.-go","2.-have","3.-can","4.-fly","5.-drink","Presione Siguiente para ver resultados"};
	String res[] = {"ir","tener","poder","volar","beber"};
	int i = 0,cont = 0;

	public void init()
	{
		sig = new Button("Siguiente");
		resp = new TextField(30);
		preg = new Label("Traduzca las siguentes palabras al español");

		add(preg); add(resp); add(sig);

		sig.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		Button b = (Button)e.getSource();
		String aux;

		if(i == 6)
			preg.setText("Resultados: "+cont+"/5");

		preg.setText(pre[i]);

		if( i != 0 )
		{
			aux = resp.getText();
			if(res[i-1].equals(aux))
				cont = cont + 1;
		}

		i = i + 1;

		resp.setText("");

		
	}




}

