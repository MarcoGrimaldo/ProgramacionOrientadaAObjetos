import java.rmi.*;


public interface IPregunta extends Remote{

	String LeePregunta(String pregunta)throws java.rmi.RemoteException;

}