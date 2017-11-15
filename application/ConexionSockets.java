package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ConexionSockets {
	private final int PORT = 1234;
	private final String HOST = "localhost";
	protected String mensaje;
	protected ServerSocket ss;
	protected Socket cs;
	protected List<Mesa> mesas;
	protected ObjectOutputStream os;
    protected ObjectInputStream is ;
	public ConexionSockets(String tipo) throws IOException {
		if(tipo.equals("Servidor"))
		{
			ss = new ServerSocket(PORT);
			cs = new Socket();
		}
		else
		{
			cs = new Socket(HOST,PORT);
		}
	}
}
