package server;

import java.net.*;
import java.io.*;

public class Server {
	private int port = 0;
	private int counter = 0;
	private ServerSocket server;
	private Socket client;
	private DataOutputStream outStream;
	private DataInputStream inStream;
	
	public Server(int porta){
		
		port = porta;
		
		try {
			
			System.out.println("Tento di aprire connessione su port "+port);
			server = new ServerSocket(port);
			System.out.println("In attesa di connessione su port "+port);
			
			} catch (IOException e) {
				System.err.println("Impossibile creare connessione su port: "+port);
				e.printStackTrace();
			}
	}
	
	public void attendi(){
		
		while(true){
			try {
				
				client = server.accept();
				Worker w = new Worker(client,counter);
				w.start();
				System.out.println("Connessione con client stabilita!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
