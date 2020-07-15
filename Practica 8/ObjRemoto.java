/*Autor: Grimaldo Peralta Marco */

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;


public class ObjRemoto extends UnicastRemoteObject implements IPregunta{


	String sin = "No te entiendo";
	
	String respuestas []={"Hola, Como estas?","Muy bien, gracias!","Para que quieres saber eso?","En el olimpo papu, ESCOM","Platicar con personas desconocidas",
		"Si, tengo dos hermanos","El rojo tornasol","El rock clasico","Los Tacos de pastor","Matematicas avanzadas","Si, la guitarra","Adios!!","No, estoy solo =|","Cual,el vende informacion?"};
		


	String preguntas[]= {"Hola","Como estas?","Cual es tu edad?","Donde estudias?","Que haces en tu tiempo libre?","Tienes hermanos?",
	"Cual es tu color favorito?","Que musica te gusta?","Cual es tu platillo favorito?","Cual es tu materia favorita?",
	"Sabes tocar un instrumento?","Adios","Tienes novia?","Que pinesas de Mark Zukaritas?"};

	public ObjRemoto() throws RemoteException{
		super();
	}

public String LeePregunta(String preg) throws java.rmi.RemoteException{

		boolean bandera=false;
		
		for (int i=0;i<14;i++)
				{		
					if(preg.equalsIgnoreCase(preguntas[i])){bandera=true;return respuestas[i];}		
				}
				
				if (bandera == false) return sin;
				
				return "";

	}


	public static void main(String[] args) {
		try{
			ObjRemoto servidor = new ObjRemoto();
			Naming.rebind("questions",servidor);
		}catch(RemoteException e){
			return;
		}catch(MalformedURLException e){
			return;
		}
	}

}