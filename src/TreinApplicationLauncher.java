import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreinApplicationLauncher {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Path to data file must be supplied.");
		}
		
		File file = new File(args[0]);

        TreinApplication treinApplication;
		try {
			treinApplication = new TreinApplication(file);
	        treinApplication.start();
		} catch (FileNotFoundException e) {
			System.out.println("Database kon niet worden gevonden. Controleer of het bestandspad klopt.");
		}		
	}
}
