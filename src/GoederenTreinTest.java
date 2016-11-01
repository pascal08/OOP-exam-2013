import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Test;

public class GoederenTreinTest extends GoederenTrein {

	@Test
	public void test_read_intercity_trein_successful() {
        String input = 
			"<VERTREK>7:30</VERTREK>" + System.lineSeparator() +
			"<AANKOMST>9:20</AANKOMST>" + System.lineSeparator() +
			"<STATION>Eindhoven</STATION>" + System.lineSeparator() +
			"<ICSTATION>Weert</ICSTATION>" + System.lineSeparator() +
			"<STATION>Roermond</STATION>" + System.lineSeparator() +
			"<ICSTATION>Sittard</ICSTATION>" + System.lineSeparator() +
			"<STATION>Maastricht</STATION>";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);

        GoederenTrein goederenTreinActual = new GoederenTrein();
        goederenTreinActual.read(scanner);
        
        GoederenTrein goederenTreinExpected = new GoederenTrein();
        goederenTreinExpected.setVertrek("7:30");
        goederenTreinExpected.setAankomst("9:20");
        goederenTreinExpected.addStation(new Station("Eindhoven"));
        goederenTreinExpected.addStation(new ICStation("Weert"));
        goederenTreinExpected.addStation(new Station("Roermond"));
        goederenTreinExpected.addStation(new ICStation("Sittard"));
        goederenTreinExpected.addStation(new Station("Maastricht"));
        
        assertTrue(goederenTreinActual.equals(goederenTreinExpected)); 
	}

}
