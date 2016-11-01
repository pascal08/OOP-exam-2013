
public abstract class AbstractStation {

	private String name;

	public AbstractStation(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.length() == 0) {
			return;
		}
		
		this.name = name;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof AbstractStation)) {
			return false;
		}
		
		AbstractStation otherStation = (AbstractStation) other;
		
		return this.getName().toLowerCase().equals(otherStation.getName().toLowerCase());
	}
	
	public String toString() {
		return this.name;
	}
	
	public abstract String getType();

}