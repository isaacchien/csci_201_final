import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientUser extends User implements Runnable{

	Socket mySocket;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public ClientUser(){
		super();
		
		try {
			//TODO: DO NOT HARDCODE LOCALHOST
			mySocket = new Socket("127.0.0.1", 1234);
			in = new ObjectInputStream(mySocket.getInputStream());
			out = new ObjectOutputStream(mySocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		
		
		while(true){
			
			System.out.println("Top of run in ClientUser");
			
			try{
				
				
				// send a message every second
				while(true){
					
					Object objectReceived = in.readObject();
					if(objectReceived instanceof Message){
						Message messageReceived = (Message)objectReceived;
						// do stuff with message
					}
						
				}
			}
			
			catch(IOException | ClassNotFoundException e){
			
			}		
		}
		
	}
	
	

}
