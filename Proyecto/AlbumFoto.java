/*Autor: Grimaldo Peralta Marco */

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.EventQueue;

public class AlbumFoto extends JFrame implements ActionListener/*, Runnable */
{
	JButton fotos[],sig,ant,pres,comEdit,para,imgEdit,comtsEdit,comSave,setVel,menos;
	JPanel superior,inferior,comentario,zoom,contentPane;;
	JFrame frame;
	JLabel pik,lzoom;
	ImageIcon imag[],atras,adelante;
	ImageIcon icons[];
	JTextField coment,entrada,vel;
	String cadena,dir,newDir;
	ArrayList coments;
	FileReader f;
	BufferedReader b;
	BufferedImage bimage[],fotosDim[],botonAd,botonAt;
	JSlider slider;
	Thread hilo;
	int k=0,numZoom,flag,intVel;
	String nomImag[] = {"0.jpg", "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg","9.jpg",
		"10.jpg", "11.jpg", "12.jpg","13.jpg","14.jpg","15.jpg","16.jpg","17.jpg","18.jpg","19.jpg","20.jpg",
		"21.jpg","22.jpg","23.jpg","24.jpg","25.jpg","26.jpg","27.jpg","28.jpg","29.jpg","30.jpg","31.jpg",
		"32.jpg","33.jpg","34.jpg","35.jpg","36.jpg","37.jpg","38.jpg","39.jpg"};

	public AlbumFoto(String dirSel) throws FileNotFoundException, IOException
	{
	    super("Album Fotografico Digital");
		//selecDir();
		dir=dirSel;
		numZoom = 100;
		flag = 0;
		intVel = 2000;
	    leeComent("comentarios.txt");
	    setFotos();
	    grid();


	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(870,820);
		setVisible(true);
		ventana();
	}

	public void leeComent(String comentDir) throws FileNotFoundException, IOException
	{
		try {
			f = new FileReader(dir+comentDir);
			b = new BufferedReader(f);
			coments = new ArrayList();

			while((cadena = b.readLine()) != null)
			{
         		coments.add(cadena);
        		// System.out.println((String)coments.get(i));
        		//i++;
         
      		}

      		b.close();
      	} catch(FileNotFoundException e){
      		msgError();
      		new AlbumFoto("");
      	}

	}

	public void msgError()
	{
		JOptionPane.showMessageDialog(null, "ERROR!\n\n En "+dir+"\nNo se encuentra:\n-''comentarios.txt''\n\nAsegurese que exista.");
	}

	public void setFotos()
	{

		imag = new ImageIcon[41];

		for (int a = 0; a<40 ;a++ ) 
		{
			imag[a] = new ImageIcon(dir+nomImag[a]);
		}

		atras = new ImageIcon(setIcono(loadImage(dir+"atras.png"),70,50));
		adelante = new ImageIcon(setIcono(loadImage(dir+"adelante.png"),70,50));

		//ImageIcon fot = new ImageIcon(path_ala_imagen);
	}

	 public static BufferedImage loadImage(String pathName) 
	 {
        BufferedImage bimag = null;
        try {
            bimag = ImageIO.read(new File(pathName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimag;
    }


	    public static BufferedImage setIcono(BufferedImage bufferedImage,int x, int y) 
	    {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();

        BufferedImage bufim = new BufferedImage(x, y, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, x, y, 0, 0, w, h, null);
        g.dispose();
        
        return bufim;
    	}

    	 public static BufferedImage setfotosDim(BufferedImage bufferedImage) 
	    {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();

        BufferedImage bufim = new BufferedImage(500, 500, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, 500, 500, 0, 0, w, h, null);
        g.dispose();
        
        return bufim;
    	}

	public void grid()
	{
		fotos = new JButton[40];
		superior = new JPanel();
		superior.setLayout(new GridLayout(4,10));
		
		for(int i = 0; i < 40 ; i++)
		{
			superior.add(fotos[i] = new JButton(""+i));
			fotos[i].addActionListener(this);

		}
		bimage = new BufferedImage[41];

		for(int b = 0; b < 40 ; b++)
		{
			bimage[b] = setIcono(loadImage(dir+nomImag[b]),50,50);
		}

		icons = new ImageIcon[41];

		for(int a = 0; a < 40 ; a++)
		{
			icons[a] = new ImageIcon(bimage[a]);
			fotos[a].setIcon(icons[a]);
		}
	}


	public void ventana() throws FileNotFoundException, IOException
	{
		setLayout(null);
		this.getContentPane().setBackground(Color.white);

		inferior = new JPanel();
		JPanel inferior0 = new JPanel();
		comentario = new JPanel();
		JPanel presen = new JPanel();
		zoom = new JPanel();
		JLabel textCom = new JLabel("Comentario:");
		lzoom = new JLabel("Zoom:"+numZoom+"%");

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(zoom);

		slider = new JSlider(JSlider.HORIZONTAL, 1, 7, 4);
		slider.setMajorTickSpacing(1); // de cuanto en cuanto los números en el slider
		slider.setMinorTickSpacing(1); //las rayitas de cuanto en cuanto
		slider.addChangeListener(e -> sliderChanged() );

		comEdit = new JButton("Editar");
		comEdit.addActionListener(this);
		imgEdit = new JButton("Cambiar Album");
		imgEdit.addActionListener(this);
		comtsEdit = new JButton("Cambiar Comentarios");
		comtsEdit.addActionListener(this);
		comSave = new JButton("Guardar");
		comSave.addActionListener(this);

		pik = new JLabel("");
		ant = new JButton("");
		ant.setIcon(atras);
		ant.addActionListener(this);
		sig = new JButton("");
		sig.setIcon(adelante);
		sig.addActionListener(this);
		pres = new JButton("Diapositivas");
		pres.addActionListener(this);
		setVel = new JButton("+");
		setVel.addActionListener(this);
		vel = new JTextField(""+intVel);


		pik.setIcon(imag[k]);
		coment = new JTextField((String)coments.get(0));
		coment.setEditable(false);
		zoom.add(pik);
		zoom.setVisible(true);
		superior.setVisible(true);

		add(superior);
		add(ant);
		add(pres);
		add(setVel);
		add(vel);
		add(imgEdit);
		add(comEdit);
		add(comSave);
		add(scroll);
		add(sig);
		add(slider);
		add(lzoom);
		add(textCom);
		add(coment);

		superior.setBounds(0,0,850,250);
		ant.setBounds(30,440,70,50);
		pres.setBounds(2,595,120,30);
		vel.setBounds(2,630,76,30);
		setVel.setBounds(79,630,42,30);
		imgEdit.setBounds(728,630,120,30);
		scroll.setBounds(125,260,600,400);
		sig.setBounds(755,440,70,50);
		slider.setBounds(10,670,740,20);
		lzoom.setBounds(770,670,100,20);
		textCom.setBounds(15,725,100,10);
		coment.setBounds(100,700,500,70);
		comEdit.setBounds(610,710,110,50);
		comSave.setBounds(730,710,110,50);
	}

	public void sliderChanged(){

		BufferedImage redim;
		ImageIcon aux0;

		int escala = slider.getValue(); /*toma el valor que tiene el slider y lo guarda como entero */

		int y = imag[k].getIconHeight();
		int x = imag[k].getIconWidth();

		if(escala == 1){
			pik.setIcon(new ImageIcon(setIcono(loadImage(dir+nomImag[k]),(x/8),(y/8))));
			numZoom = 10;
		}
		if(escala == 2){
			pik.setIcon(new ImageIcon(setIcono(loadImage(dir+nomImag[k]),(x/4),(y/4))));
			numZoom = 25;
		}
		if(escala == 3){
			pik.setIcon(new ImageIcon(setIcono(loadImage(dir+nomImag[k]),(x/2),(y/2))));
			numZoom = 50;
		}
		if(escala == 4){
			pik.setIcon(new ImageIcon(setIcono(loadImage(dir+nomImag[k]),(x),(y))));
			numZoom = 100;
		}
		if(escala == 5){
			pik.setIcon(new ImageIcon(setIcono(loadImage(dir+nomImag[k]),(x*2),(y*2))));
			numZoom = 150;
		}
		if(escala == 6){
			pik.setIcon(new ImageIcon(setIcono(loadImage(dir+nomImag[k]),(x*4),(y*4))));
			numZoom = 200;
		}
		if(escala == 7){
			pik.setIcon(new ImageIcon(setIcono(loadImage(dir+nomImag[k]),(x*8),(y*8))));
			numZoom = 400;
			//redim = setIcono(loadImage(nomImag[k]),(x*8),(y*8));
		}

		lzoom.setText("Zoom: "+numZoom+"%");

		//aux0 = new ImageIcon(redim);

		//pik.setIcon(new ImageIcon(redim));

	}




	public void actionPerformed(ActionEvent e)  //throws FileNotFoundException
	{
		//JFileChooser selector = new JFileChooser(); // se crea un objeto de tipo JFileChooser
				// el cual contendra la ruta de la imagen que se selecciones, y se abrira en la ruta C:/Users/

		String x;
		int a;

		JButton b = (JButton)e.getSource();

		if(b == ant)
		{
			if(k != 0)
			{
				k--;
				pik.setIcon(imag[k]);
				coment.setText((String)coments.get(k));
			}

			if(k == 0)
			{
				pik.setIcon(imag[39]);
				coment.setText((String)coments.get(39));
				k = 39;
			}

		}

		else if(b == sig)
		{
			if(k != 39)
			{
				k++;
				pik.setIcon(imag[k]);
				coment.setText((String)coments.get(k));
			}

			if(k == 39)
			{
				pik.setIcon(imag[0]);
				coment.setText((String)coments.get(0));
				k = 0;
			}
		}

		else if(b == pres)
		{
			if(flag == 0)
			{
				if(k != 0 || k != 39)
				{

					hilo = new Thread()
					{
						public void run()
						{
							for(int i = 0; i < 40 ; i++)
								fotos[i].setEnabled(false);
							sig.setEnabled(false);
							ant.setEnabled(false);
							slider.setEnabled(false);
							//vel.setEnabled(false);
							//setVel.setEnabled(false)

							pres.setText("Detener");
							flag = 1;

	   						while(k < 40)
							{
								try{
									Thread.sleep(intVel);
									if(k == 39)
										break;
									pik.setIcon(imag[k+1]);
									coment.setText((String)coments.get(k+1));

								} catch(Exception ex){return;}
								k++;
							}

							pres.setText("Diapositivas");

							k = 0;
							pik.setIcon(imag[k]);
							coment.setText((String)coments.get(k));

							flag = 0;

							for(int i = 0; i < 40 ; i++)
								fotos[i].setEnabled(true);
							sig.setEnabled(true);
							ant.setEnabled(true);
							slider.setEnabled(true);


	   	 				}
					};

					hilo.start();
				}
			}

			if(flag == 1)
			{
				pres.setText("Diapositivas");

				for(int i = 0; i < 40 ; i++)
					fotos[i].setEnabled(true);
				pres.setEnabled(true);
				sig.setEnabled(true);
				ant.setEnabled(true);
				slider.setEnabled(true);

				hilo.stop();

				flag = 0;

			}

		}


		else if(b == imgEdit)
		{
			JFileChooser fc=new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seleccion = fc.showOpenDialog(contentPane);
			File archivo = fc.getSelectedFile();

			String mm[] = {"",""};

			/*La llamada showOpenDialog() se quedará bloqueada hasta que el usuario elija un fichero y cierre la ventana. 
			A la vuelta, en selección tendremos:
			JFileChooser.CANCEL_OPTION Si el usuario le ha dado al botón cancelar.
			JFileChooser.APPROVE_OPTION Si el usuario le ha dado al botón aceptar
			JFileCHooser.ERROR_OPTION Si ha ocurrido algún error.
			*/
				if (seleccion == JFileChooser.APPROVE_OPTION) { // si el usuario selecciono una imagen correcta 
					//try { 
						newDir = archivo.getPath();
						newDir = newDir+"/";

						try {
							setVisible(false);
 							new AlbumFoto(newDir);
						}
						catch(IOException excn) {
  							excn.printStackTrace();
						}

					//} catch (IOException excp) { // si existe alguan exepcion se atrapa
						
					//	excp.printStackTrace();
					//}
				}
		}

		/*else if(b == comtsEdit)
		{
				hola("txt");
		}*/

		else if(b == comEdit)
		{
			coment.setEditable(true);
		}

		else if(b == comSave)
		{
			coment.setEditable(false);
			String aux = coment.getText();
			coments.set(k,aux);
			guardaComents("comentarios.txt");

		}

		else if(b == setVel)
		{
			String ax = vel.getText();
			intVel = Integer.parseInt(ax);
		}

		else 
		{
			x = b.getText();
			a = Integer.parseInt(x);
			k = a;
			pik.setIcon(imag[a]);
			coment.setText((String)coments.get(a));
			slider.setValue(4); 
		}

	}

	public void guardaComents(String dirCom)
	{
		FileWriter fic = null;
		PrintWriter pw = null;

		try{
			fic = new FileWriter(dirCom);
			pw = new PrintWriter(fic);

			for(int j=0 ; j < coments.size(); j++)
			{
				pw.println((coments.get(j)));
			}

			fic.close();
		} catch(Exception e2) { e2.printStackTrace();}
	}

	public static void main(String[] args) throws IOException 
	{
    	new AlbumFoto("");
    }


}