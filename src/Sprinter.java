import java.util.List;
import java.util.Scanner;

public class Sprinter extends AbstractTrein {

	public Sprinter read(Scanner scanner) {		
		return (Sprinter) super.read(scanner);
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		List<AbstractStation> stations = this.getStations();
		
		stringBuilder.append(String.format("Intercity van %s naar %s", 
				stations.get(0), 
				stations.get(stations.size() - 1)));
		
		stringBuilder.append(System.lineSeparator());
		
		stringBuilder.append("\t");
		stringBuilder.append(String.format("Vertrek: %s", this.getVertrek()));
		stringBuilder.append(System.lineSeparator());
		
		stringBuilder.append("\t");
		stringBuilder.append(String.format("Aankomst: %s", this.getAankomst()));
		stringBuilder.append(System.lineSeparator());

		for(int i = 0; i < stations.size(); i++) {
			AbstractStation station = stations.get(i);
			
			stringBuilder.append("\t");
			stringBuilder.append(String.format("---%s", station.toString()));
			stringBuilder.append(System.lineSeparator());
		}
		
		return stringBuilder.toString();
	}

	@Override
	public String getType() {
		return "Sprinter";
	}
}
