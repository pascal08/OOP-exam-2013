import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreinCatalog {
	
	private List<AbstractTrein> treinen;
	
	public TreinCatalog() {
		this.treinen = new ArrayList<>();
	}
	
	public synchronized void addTrein(AbstractTrein trein) {
		this.treinen.add(trein);
	}
	
	public synchronized List<AbstractTrein> getTreinen() {
		return this.treinen;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(AbstractTrein trein : this.treinen) {
			stringBuilder.append(trein.toString());
			stringBuilder.append(System.lineSeparator());
		}
		
		return stringBuilder.toString();
	}
		
	public static TreinCatalog read(Scanner scanner) {
		TreinCatalog treinCatalog = new TreinCatalog();
		
		String rootNode = scanner.nextLine();
		if(! rootNode.toLowerCase().contains("treinen")) {
			System.out.println("treinen.txt niet in juiste format.");
		}
		
		while(scanner.hasNextLine()) {
			String node = scanner.nextLine();

			String treinType = node.toLowerCase();
						
			try {
				AbstractTrein trein = null;
				
				switch(treinType) {
					case "<sprinter>":
						Sprinter sprinter = new Sprinter();
						trein = sprinter.read(scanner);
						treinCatalog.addTrein(trein);
						break;
					case "<intercity>":
						Intercity intercity = new Intercity();
						trein = intercity.read(scanner);
						treinCatalog.addTrein(trein);
						break;
					case "<goederentrein>":
						GoederenTrein goederenTrein = new GoederenTrein();
						trein = goederenTrein.read(scanner);
						treinCatalog.addTrein(trein);
						break;
					default: 
						break;
				}
				
			} catch (IllegalArgumentException e) {
				e.getMessage();
			}
			
		}
		
		scanner.close();
		
		return treinCatalog;
	}

}
