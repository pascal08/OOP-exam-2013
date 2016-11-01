import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Test;

public class IntercityTest extends Intercity {

	@Test
	public void test_read_intercity_trein_successful() {
        String input = 
			"<VERTREK>14:20</VERTREK>" + System.lineSeparator() +
			"<AANKOMST>15:00</AANKOMST>" + System.lineSeparator() +
			"<STATION>Rotterdam Centraal</STATION>" + System.lineSeparator() +
			"<ICSTATION>Schiedam Centrum</ICSTATION>" + System.lineSeparator() +
			"<STATION>Delft Zuid</STATION>" + System.lineSeparator() +
			"<ICSTATION>Delft</ICSTATION>" + System.lineSeparator() +
			"<STATION>Rijswijk</STATION>" + System.lineSeparator() +
			"<STATION>Den Haag Moerwijk</STATION>" + System.lineSeparator() +
			"<STATION>Den Haag HS</STATION>";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);

        Intercity intercityActual = new Intercity();
        intercityActual.read(scanner);
        
        Intercity intercityExpected = new Intercity();
        intercityExpected.setVertrek("14:20");
        intercityExpected.setAankomst("15:00");
        intercityExpected.addStation(new Station("Rotterdam Centraal"));
        intercityExpected.addStation(new ICStation("Schiedam Centrum"));
        intercityExpected.addStation(new Station("Delft Zuid"));
        intercityExpected.addStation(new ICStation("Delft"));
        intercityExpected.addStation(new Station("Rijswijk"));
        intercityExpected.addStation(new Station("Den Haag Moerwijk"));
        intercityExpected.addStation(new Station("Den Haag HS"));
        
        assertTrue(intercityActual.equals(intercityExpected)); 
	}

}
