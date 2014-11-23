import java.util.HashMap;

public class User{
	private String username;
	private int money;
	private int wins;
	private int losses;
	private HashMap<String, Integer> items;
	
	public User(String username, int money, int wins, int losses, HashMap<String, Integer> items){
		this.username = username;
		this.money = money;
		this.wins = wins;
		this.losses = losses;
		this.items = items;
	}
	
}