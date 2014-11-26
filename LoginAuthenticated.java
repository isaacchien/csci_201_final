public class LoginAuthenticated extends Message{
	
	private boolean isAuthenticated;
	
	private boolean triedToAuthenticateFromSignUp;
	
	public LoginAuthenticated(boolean isAuthenticated){
		this.isAuthenticated = isAuthenticated;
	}
	public LoginAuthenticated(boolean isAuthenticated, boolean triedToAuthenticateFromSignUp){
		this.isAuthenticated = isAuthenticated;
		this.triedToAuthenticateFromSignUp = triedToAuthenticateFromSignUp;
	}
	
	void setAuthenticated(boolean loginSucceded){
		this.isAuthenticated = loginSucceded;
	}
	boolean getAuthenticated(){
		return isAuthenticated;
	}

	public boolean triedToAuthenticateFromSignUp() {
		return triedToAuthenticateFromSignUp;
	}

	public void setTriedToAuthenticateFromSignUp(
			boolean triedToAuthenticateFromSignUp) {
		this.triedToAuthenticateFromSignUp = triedToAuthenticateFromSignUp;
	}






}