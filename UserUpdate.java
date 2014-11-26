import java.io.Serializable;


public class UserUpdate extends User implements Serializable{
	private int steroids;
	private int morphine;
	private int epinephrine;
	
	public UserUpdate(int id, String username, int money, int wins, int losses, int opponentID, int steroids, int morphine, int epinephrine) {
		super(id, username, money, wins, losses, null);
		this.setOpponentID(opponentID);
		this.steroids = steroids;
		this.morphine = morphine;
		this.epinephrine = epinephrine;
	}

	public int getSteroids() {
		return steroids;
	}

	public void setSteroids(int steroids) {
		this.steroids = steroids;
	}

	public int getMorphine() {
		return morphine;
	}

	public void setMorphine(int morphine) {
		this.morphine = morphine;
	}

	public int getEpinephrine() {
		return epinephrine;
	}

	public void setEpinephrine(int epinephrine) {
		this.epinephrine = epinephrine;
	}
	
	
}
