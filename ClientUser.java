import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class ClientUser extends User implements Runnable{

	Socket mySocket;
	ObjectInputStream in;
	ObjectOutputStream out;
	PokemonFrame pk; 
	
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
	
	public void setPokemonFrame(PokemonFrame pk) {
		this.pk = pk;
	}
	
	public ObjectOutputStream getOutputStream(){
		return out;
	}
	
	public void purchaseSteroids() {
		try {
			out.writeObject(new PurchaseUpdate(this.getID(), 0, true, 1, 0, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void purchaseMorphine() {
		try {
			out.writeObject(new PurchaseUpdate(this.getID(), 0, true, 0, 1, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void purchaseEpinephrine() {
		try {
			out.writeObject(new PurchaseUpdate(this.getID(), 0, true, 0, 0, 1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void update(UserUpdate user) {
		
		this.setID(user.getID());
		this.setUsername(user.getUsername());
		this.setMoney(user.getMoney());
		this.setWins(user.getWins());
		this.setLosses(user.getLosses());
		this.setOpponentID(user.getOpponentID());
		if(this.getItems() == null) {
			this.setItems(new HashMap<String, Integer>());
		}
		this.updateItem("steroids", user.getSteroids());
		this.updateItem("morphine", user.getMorphine());
		this.updateItem("epinephrine", user.getEpinephrine());
	}
	
	public void run() {
		
		System.out.println("Client started listening for messages from server");
		
		while(true){
			
			
			try{
				
				
				// send a message every second
				while(true){
					
					Object objectReceived = in.readObject();
					if(objectReceived instanceof LoginAuthenticated){
						System.out.println("Verifying...");
						LoginAuthenticated messageReceived = (LoginAuthenticated)objectReceived;
						if(messageReceived.getAuthenticated()){
							System.out.println("Login verified");	
							this.pk.userHasLoggedIn();
						}
						else{
							
							if(messageReceived.triedToAuthenticateFromSignUp()){
								JOptionPane.showMessageDialog(pk,
									    "That username is already in use.",
									    "Error",
									    JOptionPane.ERROR_MESSAGE);
							}
							else{
								System.out.println("Login failed");
								JOptionPane.showMessageDialog(pk,
									    "Invalid username or password",
									    "Error",
									    JOptionPane.ERROR_MESSAGE);
							}
						}
		
						
					} else if(objectReceived instanceof UserUpdate) {
						this.update((UserUpdate)objectReceived);
					} else if(objectReceived instanceof PurchaseUpdate) {
						if(((PurchaseUpdate) objectReceived).isSuccessful()) {
							this.setMoney(((PurchaseUpdate) objectReceived).getMoney());
							this.updateItem("steroids", ((PurchaseUpdate) objectReceived).getSteroids());
							this.updateItem("morphine", ((PurchaseUpdate) objectReceived).getMorphine());
							this.updateItem("epinephrine", ((PurchaseUpdate) objectReceived).getEpinephrine());
							this.pk.storePanel.update();
						} else {
							//purchase failed stuff
						}
					}
						
				}
			}
			
			catch(IOException | ClassNotFoundException e){
			
			}		
		}
		
	}
	
	

}
