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
			mySocket = new Socket("127.0.0.1", 1234);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		
		
		while(true){
			System.out.println("Top of run in ClientUser");
			try{
				ObjectOutputStream in = new ObjectOutputStream(mySocket.getOutputStream());
				
				// send a message every second
				while(true){
					
					AttackData testMessageToSend = new AttackData();
					out.writeObject(testMessageToSend);
					Thread.sleep(1000);
						
				}
			}
			
			catch(InterruptedException | IOException e){
			
			}		
		}
		
	}
	
	

}
