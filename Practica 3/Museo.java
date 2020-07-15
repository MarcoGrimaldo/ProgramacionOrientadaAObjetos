/*Autor: Grimaldo Peralta Marco */

import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Container;
import javax.swing.*;
import javax.swing.event.*;

public class Museo extends JFrame {
   Canvas3D canvas;
   Appearance ap;
   private Paleta paleta;
   private String etiqs[]={"Visita Guiada 1", "Visita Guiada 2", "Visita Guiada 3"};
   mBehaior Beh;

        
   int mapa[][]= {
            {1,1,19,1,1,1,1,0,1,1,1,1,22,1,1},
	          {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},
            {18,0,0,0,20,1,0,0,0,1,23,0,0,0,26},
            {1,0,0,0,1,1,0,0,0,31,1,0,0,0,1},
            {17,0,0,0,21,1,0,0,0,1,24,0,0,0,25},
            {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},
            {29,0,0,0,0,0,0,0,0,0,0,0,0,0,28},
            {27,0,0,0,0,0,0,0,0,0,0,0,0,0,30},
            {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},
            {16,0,0,0,12,11,0,0,0,7,4,0,0,0,5},
            {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1},
            {15,0,0,0,13,10,0,0,0,8,3,0,0,0,6},
            {1,1,14,1,1,1,1,9,1,1,1,1,2,1,1},
                   };     
    public Museo() {
    	super ("Galeria");
    	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    	canvas=new Canvas3D(config);
        paleta=new Paleta(etiqs,new GridLayout(etiqs.length, 1), new ManejaAccionInt()); 
    	add("North", paleta);
    	add ("Center", canvas);
    	CrearMuseo();	
        setResizable(false);
    	setSize(1200, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    	setVisible(true);
    } 
    void CrearMuseo(){
    	SimpleUniverse univ = new SimpleUniverse (canvas);
    	BranchGroup Bgroup=new BranchGroup();
    	TransformGroup group=new TransformGroup ();
    	TransformGroup grup=new TransformGroup();
    	
    	Pared piso=new Pared (8f, .07f, 12f, "piso.jpg");

        Pared pe1= null;
        TransformGroup p1tg1;
        String imas[]={"nada", "pared1.jpg","1.jpg","2.jpg","3.jpg","6.jpg","5.jpg","stalin.jpg", 
                       "robot.jpg", "insignia.jpg","Ham.png","soviet_propaganda.jpg","mk2.jpg","mk.png", "miranda.jpg",
                       "mk1.jpg","mk3.png","ac1.jpg","ac2.jpg","ac3.gif","ac4.jpg","ac5.jpg",
                       "az1.jpg","az2.jpg","az3.jpg","az4.jpg","az5.jpg","mks.jpg","azs.jpg","artcs.jpg","artcss.jpg","coms.jpg"};
    	for (int n=0;n <mapa.length;n++){
            for (int m=0;m <mapa[0].length;m++){
                if(mapa[n][m] > 0) {
                   pe1=new Pared (0, imas[mapa[n][m]]);
                   p1tg1= Posi.translate(pe1.getBox(), new Vector3f(n, .0f, m));
                   grup.addChild(p1tg1);
                }
                
            }
    	}
    	TransformGroup pisotg= Posi.translate(piso.getBox(), 
                                              new Vector3f(3.5f, -.45f, 2f));
    	grup.addChild(pisotg);
         TransformGroup g=Posi.translate(grup, new Vector3f(-5.5f,0f,-8f));
         group.addChild(g);
        
    	//Behaior
    	TransformGroup trans=new TransformGroup ();
    	trans.addChild(avatar());
    	group.addChild(trans);
        Beh=new mBehaior(univ, trans);
    	group.addChild(Beh);
    	Bgroup.addChild(group);
    	univ.getViewingPlatform().setNominalViewingTransform();
        univ.addBranchGraph(Bgroup); 
    }
    public TransformGroup avatar(){

        Appearance appeye = new Appearance();
        TextureLoader tex=new TextureLoader("eye2.jpg", null);
        
	       appeye.setTexture(tex.getTexture());
	       Sphere eye = new Sphere(0.09f, Primitive.GENERATE_NORMALS | 		
	       Primitive.GENERATE_TEXTURE_COORDS, 32, appeye);
        TransformGroup ttr=new TransformGroup();
        ttr.addChild(eye);
        return ttr;
    }
    public  void visita1(){

     int   pasos=60;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){		
          Beh.Arriba();			
    	  try { Thread.sleep(100);}
    	  catch(InterruptedException ex){}		
      }
      pasos=10;
       for(int i=0;i<pasos;i++){		
          Beh.Izq(9);	
    	  try { Thread.sleep(100);}
    	  catch(InterruptedException ex){}		
      }
       pasos=20;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=55;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=25;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=50; 
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); //Enfrente de expo comunismo
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=30;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=30;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=50;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=30;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=55;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=25;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=35;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }

    }
    public  void visita2(){
      int pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=40;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=20;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=60;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=50;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=50;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=40;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=35;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=50;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=30;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=70;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=35;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=60;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
    }
      public  void visita3(){

        int   pasos=60;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=20;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=25;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); //OJO AQUI
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=50; 
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); //Enfrente de expo comunismo
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=30;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=30;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
        pasos=50;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=30;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
       pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Izq(18); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      } 
      pasos=30;
       canvas.enable(false);
       for(int i=0;i<pasos;i++){    
          Beh.Arriba();     
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
      pasos=10;
       for(int i=0;i<pasos;i++){    
          Beh.Der(9); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      } 
      pasos=50;
       for(int i=0;i<pasos;i++){    
          Beh.Arriba(); 
        try { Thread.sleep(100);}
        catch(InterruptedException ex){}    
      }
     
    }
    

    public static void main(String a[]) {new Museo ();}
    class ManejaAccionInt implements AccionInt {
      public void accion(int n, ActionEvent e){ 
         //System.out.println("accion");
	       if(n==0){ visita1(); }
         if(n==1){ visita2(); }
         if(n==2){ visita3(); }
     }
   } 	
}
