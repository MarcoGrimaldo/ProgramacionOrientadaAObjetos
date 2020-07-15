import java.net.*;
import java.io.*;

public class Servidor
{	
	public static void main(String args[])
	{
		final int puerto= 9999;
		int flag = 0;
		
		String respuestas []={"Todos vamos a morir.","Un robot.","Rock muuuuuy pesado.","Simon.","Tal vez.","Claro.","Depende de ti.",
		"Me sorprende que no se han dado cuenta.","Que le dijo una piedra a otra? La vida es dura.",
		"Claro que no!"};
		
		String preguntas[]= {"Voy a morir?","Quien eres?","Que musica te gusta?",
		"Eres de la nasa?","Voy a pasar POO?","Eres bueno en las matematicas?","Me voy a casar?","Mario es gay?",
		"Cuentame un chiste","Tienes facebook?"};
		
		ServerSocket socketServidor;
		Socket conexion;
		
		PrintStream salida;
		DataInputStream entrada;
		
		String s;
		
		try
		{
			socketServidor = new ServerSocket(puerto);
			
			while (true)
			{
				
			
				conexion = socketServidor.accept();
				
				entrada = new DataInputStream(conexion.getInputStream());
				salida = new PrintStream(conexion.getOutputStream());
				
				s = entrada.readLine();
				
				for (int i=0;i<10;i++)
				{
					
					if(s.equals(preguntas[i])){salida.println(respuestas[i]);}
					else{flag = 1;}
					
				}
				
				if(flag == 1){salida.println("No puedo contestar eso.");}
				conexion.close();
				
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}