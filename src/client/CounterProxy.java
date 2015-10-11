package client;

import java.io.*;
import java.net.*;

public class CounterProxy implements Counter{
	private String ind = null;
	private int port = 0;
	private Socket client;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	
	
	public CounterProxy(String s, int p){
		
		ind = s;
		port = p;
		//connetti();
		
	}
	
	private void connetti() {
		
		try{
			
			client = new Socket(ind,port);
			inStream = new DataInputStream(client.getInputStream());
			outStream = new DataOutputStream(client.getOutputStream());
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void incrementa(int x) {
		connetti();
		
		try{
			outStream.writeUTF("Incrementa");
			outStream.writeInt(x);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		chiudi();
	}

	
	public int stampa() {
		int x = 0;
		connetti();
		
		try{
			
			outStream.writeUTF("Stampa");
			x = inStream.readInt();
		
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		chiudi();
		
		return x;
	}
	
	private void chiudi(){
		
		try{
			
			outStream.writeUTF("Ciao");
			inStream.close();
			outStream.close();
			client.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
