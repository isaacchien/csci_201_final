import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class User extends Thread {
	private int id;
	private String username;
	private int money;
	private int wins;
	private int losses;
	private HashMap<String, Integer> items;
	
	public static final String DB_ADDRESS = "jdbc:mysql://localhost/";
	public static final String DB_NAME = "group_db";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	private static ReentrantLock lock = new ReentrantLock();
	
	public User(int id, String username, int money, int wins, int losses, HashMap<String, Integer> items){
		this.setUsername(username);
		this.setMoney(money);
		this.setWins(wins);
		this.setLosses(losses);
		this.setItems(items);
	}
	
	private Connection establishConnection() {
		Connection con = null;
		try {
			con =  DriverManager.getConnection(this.DB_ADDRESS + this.DB_NAME, this.USER, this.PASSWORD);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean update () {
		lock.lock();
		try {
			Connection con = establishConnection();
			if(con == null) {
				System.out.println("Unable to establish connection");
				return;
			}
			PreparedStatement stmt = con.prepareStatement("INSERT INTO users (wins, total_games, money, steroids, morphine, epinephrine) VALUES (?, ?, ?, ?, ?, ?) WHERE id = ?;");
			stmt.setInt(1, this.wins);
			stmt.setInt(2, this.wins + this.losses);
			stmt.setInt(3, this.money);
			stmt.setInt(4, this.items.get('steroids').intValue());
			stmt.setInt(5, this.items.get('morphine').intValue());
			stmt.setInt(6, this.items.get('epinephrine').intValue());
			stmt.setInt(7, this.id);
			stmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
			lock.unlock();
			return false;
		} finally {
			lock.unlock();
			return true;
		}
	}
	
	public boolean fetch() {
		lock.lock()
		try {
			Statement stmt = con.createStatement();
			if(con == null) {
				System.out.println("Unable to establish connection");
				return;
			}
			results = stmt.executeQuery("SELECT wins, total_games, money, steroids, morphine, epinephrine FROM users WHERE id = " + this.id + ";");
			if(results == null) {
				System.out.println("There were no results")
				return;
			}
			this.wins = results.getInt("wins");
			this.losses = results.getInt("total_games") - this.wins;
			this.money = results.getInt("money");
			this.items.put("steroids", results.getInt("steroids"));
			this.items.put("morphine", results.getInt("morphine"));
			this.items.put("epinephrine", results.getInt("epinephrine"));
		} catch(Exception e) {
			e.printStackTrace();
			lock.unlock();
			return false;
		} finally {
			lock.unlock();
			return false;
		}
	}
	
	public void run() {
		
	}
	
	public int getID() {
		return this.id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public HashMap<String, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<String, Integer> items) {
		this.items = items;
	}
	
}