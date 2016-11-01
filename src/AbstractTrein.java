import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractTrein {

	private String aankomst;
	private String vertrek;
	private List<AbstractStation> stations;
	
	public AbstractTrein() {
		this.aankomst = "";
		this.vertrek = "";
		this.stations = new ArrayList<>();
	}
	
	public List<AbstractStation> getStations() {
		return this.stations;
	}
	
	public void addStation(AbstractStation station) {
		this.stations.add(station);
	}

	public String getAankomst() {
		return this.aankomst;
	}

	public void setAankomst(String aankomst) {
		this.aankomst = aankomst;
	}

	public String getVertrek() {
		return this.vertrek;
	}

	public void setVertrek(String vertrek) {
		this.vertrek = vertrek;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof AbstractTrein)) {
			return false;
		}
		
		AbstractTrein otherTrein = (AbstractTrein) other;
		
		System.out.println(this.getAankomst());
		System.out.println(otherTrein.getAankomst());
		if(!this.getAankomst().equals(otherTrein.getAankomst())) {
			return false;
		}
		
		if(!this.getVertrek().equals(otherTrein.getVertrek())) {
			return false;
		}
		
		List<AbstractStation> stations = this.getStations();
		List<AbstractStation> otherStations = otherTrein.getStations();
		
		if(stations.size() != otherStations.size()) {
			return false;
		}
		
        for (int i = 0; i < stations.size(); i++) {
            if(otherStations.get(i) == null) {
                return false;
            }

            if (!stations.get(i).equals(otherStations.get(i))) {
                return false;
            }
        }
		
		return true;
	}
	
	
	public AbstractTrein read(Scanner scanner) throws IllegalArgumentException {
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			NodeParser nodeParser = new NodeParser();
			
			if(!nodeParser.parse(line)) {
				break;
			}
			
			String nodeType = nodeParser.getNodeType().toLowerCase();

			String nodeValue = nodeParser.getNodeValue();

			switch (nodeType) {
				case "vertrek":
					this.setVertrek(nodeValue);
					break;
				case "aankomst":
					this.setAankomst(nodeValue);
					break;
				case "station":
					this.addStation(new Station(nodeValue));
					break;
				case "icstation":
					this.addStation(new ICStation(nodeValue));
					break;
				default:
					throw new IllegalArgumentException("Trein info kon niet worden gelezen. Controleer data.");
			}
		}
		
		return this;
	}
	
	public abstract String toString();
	
	public abstract String getType();
}
