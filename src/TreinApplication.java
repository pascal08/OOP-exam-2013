import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TreinApplication {

	private Scanner dataScanner;
	private TreinCatalog treinCatalog;
	private Scanner input;
	private File file;
	
	public TreinApplication(File file) throws FileNotFoundException {
		this.file = file;
		this.dataScanner = new Scanner(file);
		this.input = new Scanner(System.in);
	}
	
	public void start() throws FileNotFoundException {
		loadCatalog();
		goToMenu();
	}
	
	public void loadCatalog() {
        treinCatalog = TreinCatalog.read(this.dataScanner);
	}
	
	public void goToMenu() throws FileNotFoundException {       
		int choice = promptMenuOption();
		
		switch (choice) {
			case 1:
				show();
				break;
			case 2:
				search();
				break;
			case 3:
				add();
				break;
			case 4:
				save();
				break;
			case 5:
				exit();
				break;
			default:
				break;
		}
	}

	private int promptMenuOption() {
		int choice;
		
		printMenuOptions();

        boolean validInput = false;
	    do {            
            while (!this.input.hasNextInt()) {
            	input.next();
            }

            choice = input.nextInt();
            
	        if(choice >= 1 && choice <= 5) {
	        	validInput = true; 
	        } else {
	        	System.out.println("Kies een optie uit de lijst.");	        	
	        }
	    } while (!validInput);
		
		// Skip the newline
		input.nextLine();
		
		return choice;
	}

	public static void printMenuOptions() {
		System.out.println("*** PRORAIL APPLICATIE ***");
		System.out.println("1 - Geef alle treinen in de in-memory database weer op het scherm.");
		System.out.println("2 - Geef alle treinen van station A naar station B weer.");
		System.out.println("3 - Voeg een trein toe aan de database.");
		System.out.println("4 - Wegschrijven naar file.");
		System.out.println("5 - Stop het programma.");
		System.out.print("Maak uw keuze: ");
	}
	
	private void show() throws FileNotFoundException {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(System.lineSeparator() + "Treinen worden gesorteerd...");
				Collections.sort(treinCatalog.getTreinen(), new TreinComparator());
				System.out.println(treinCatalog.toString());
			}
		});
		t.start();
		
		goToMenu();
	}
	
	private void search() throws FileNotFoundException {		
		System.out.println("Voer naam van beginstation in:");

		String beginStationInput = input.nextLine();
				
		System.out.println("Voer naam van eindstation in:");
		
		String eindStationInput = input.nextLine();
		
		TreinCatalog filteredCatalog = new TreinCatalog();
		
		List<AbstractTrein> treinen = treinCatalog.getTreinen();
		for (int i = 0; i < treinen.size(); i++) {
			AbstractTrein trein = treinen.get(i);
			
			List<AbstractStation> stations = trein.getStations();
			
			String beginStation = stations.get(0).getName();
			String eindStation = stations.get(stations.size() - 1).getName();
			
			if(beginStation.equals(beginStationInput) && eindStation.equals(eindStationInput)) {
				filteredCatalog.addTrein(trein);
			}
		}		

		System.out.println(String.format("%d resultaten", filteredCatalog.getTreinen().size()));
		System.out.println(filteredCatalog.toString());
		
		goToMenu();
	}
	
	public void add() throws FileNotFoundException {
		AbstractTrein trein = promptTreinType();

		trein.addStation(promptStation("Wat is de naam van het vertrek station?"));

		System.out.println("Wat is het uur van vertrek?");
		trein.setVertrek(input.nextLine());

		System.out.println("Wat is het uur van aankomst?");
		trein.setAankomst(input.nextLine());
		
		boolean stopAction = false;
		do {
			int choice = promptYesOrNo("Wilt u nog een station toevoegen? (Het laatst ingevoerde station is het eindstation)");

			if(choice == 2) {
				stopAction = true;
			} else {
				trein.addStation(promptStation("Wat is de naam van het station op de route?"));
			}
		} while(!stopAction);
		
		System.out.println("De trein is toegevoegd aan de database.");
		
		treinCatalog.addTrein(trein);
		
		goToMenu();
	}
	
	private void save() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
		
		printWriter.println("<TREINEN>");
		
		for(AbstractTrein trein : treinCatalog.getTreinen()) {
			String treinType = trein.getType().toUpperCase();
			printWriter.println(String.format("<%s>", treinType));
			
			printWriter.println(String.format("<VERTREK>%s</VERTREK>", trein.getVertrek()));
			printWriter.println(String.format("<AANKOMST>%s</AANKOMST>", trein.getAankomst()));
			
			for(AbstractStation station : trein.getStations()) {
				printWriter.println(String.format("<%1$s>%2$s</%1$s>", station.getType().toUpperCase(), station.getName()));
			}

			printWriter.println(String.format("</%s>", treinType));
		}
		
		printWriter.println("</TREINEN>" + System.lineSeparator());
		
        printWriter.flush();
        
        printWriter.close();
        
        System.out.println("Data naar bestand weggeschreven.");
        
        goToMenu();
	}
	
	private int promptYesOrNo(String message) {
		String[] options = new String[] { "Ja", "Nee" };

		return promptOptions(message, options);
	}

	private AbstractStation promptStation(String message) {
		String[] options = new String[] { "Station", "ICStation" };

		System.out.println(message);

		String stationName = input.nextLine();

		int choice = promptOptions("Wat is het type station?", options);

		switch (choice) {
		case 1:
			return new Station(stationName);
		case 2:
			return new ICStation(stationName);
		default:
			throw new IllegalStateException(String.format("%s should not be accepted as a valid option", choice));
		}
	}
	
	private AbstractTrein promptTreinType() {
		String[] options = new String[] { "Sprinter", "Intercity", "Goederentrein" };

		int choice = promptOptions("Welk type trein wilt u toevoegen?", options);

		switch (choice) {
		case 1:
			return new Sprinter();
		case 2:
			return new Intercity();
		case 3:
			return new GoederenTrein();
		default:
			throw new IllegalStateException(String.format("%s should not be accepted as a valid option", choice));
		}
	}

	private int promptOptions(String toPrompt, String[] options) {
		int choice;

		boolean validInput = false;
		do {
			System.out.println(String.format("%s", toPrompt));

			printOptions(options);

			while (!input.hasNextInt()) {
				input.next();
				System.out.println("Ongeldige keuze. Gebruik het getal om aan te geven welke optie u wilt kiezen.");
			}

			choice = input.nextInt();
			
			//consume next line
			input.nextLine();

			if (choice > 0 || choice <= options.length) {
				validInput = true;
			} else {
				System.out.println("Ongeldige keuze. Kies een van de gegeven opties.");
			}

		} while (!validInput);

		return choice;
	}
	
	private static void printOptions(String[] options) {
		for(int i = 0; i < options.length; i++) {
			System.out.println(String.format("%d) %s", i + 1, options[i]));
		}
	}

	public void exit() {
		this.dataScanner.close();
		this.input.close();
		System.out.println("Programma wordt afgesloten...");
		System.exit(0);
	}

}
