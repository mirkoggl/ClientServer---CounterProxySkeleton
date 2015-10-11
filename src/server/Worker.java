package server;

import java.io.*;
import java.net.*;

public class Worker extends Thread{
	private int counter;
	private Socket client;
	private DataOutputStream outStream;
	private DataInputStream inStream;
	
	public Worker(Socket c, int x){
		
		try{
			counter = x;
			client = c;
			inStream = new DataInputStream (client.getInputStream());
			outStream = new DataOutputStream (client.getOutputStream());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		todo();
		
	}
	
	public void todo() {
		String command = null;
		do{
			try {
				
				command = inStream.readUTF();
				System.out.println("Command: "+command);
				
				if ((command.compareTo("Incrementa")) == 0){
					counter = counter + inStream.readInt();
					System.out.println("Incremento eseguito!");
				}
				else if ((command.compareTo("Stampa")) == 0){
					outStream.writeInt(counter);
					System.out.println("Stampa inviata!");
				}
				else if((command.compareTo("Ciao")) == 0){
					System.out.println("Connessione Terminata!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while((command.compareTo("Ciao") != 0));		
		
		try{
			
			inStream.close();
			outStream.close();
			client.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
