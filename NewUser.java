
public class NewUser extends MenuAction{
	
	private String username;
	private String password;
	
	public NewUser(String u, String p) {
		this.password = p;
		this.username= u;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
