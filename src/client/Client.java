package client;

public class Client {
	private CounterProxy cp;	
	
	public Client(String s,int p){
		
		cp = new CounterProxy(s,p);
	
	}
	
	public void todo(){
		int x = 0;
		
		cp.incrementa(10);
		x = cp.stampa();
		System.out.println("Counter: "+x);
		
		cp.incrementa(10);
		cp.incrementa(10);
		cp.incrementa(10);
		cp.incrementa(10);
		x = cp.stampa();
		System.out.println("Counter: "+x);
	}

}
