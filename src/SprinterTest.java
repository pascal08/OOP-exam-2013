import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Test;

public class SprinterTest extends Sprinter {

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

        Sprinter sprinterActual = new Sprinter();
        sprinterActual.read(scanner);
        
        Sprinter sprinterExpected = new Sprinter();
        sprinterExpected.setVertrek("14:20");
        sprinterExpected.setAankomst("15:00");
        sprinterExpected.addStation(new Station("Rotterdam Centraal"));
        sprinterExpected.addStation(new ICStation("Schiedam Centrum"));
        sprinterExpected.addStation(new Station("Delft Zuid"));
        sprinterExpected.addStation(new ICStation("Delft"));
        sprinterExpected.addStation(new Station("Rijswijk"));
        sprinterExpected.addStation(new Station("Den Haag Moerwijk"));
        sprinterExpected.addStation(new Station("Den Haag HS"));
        
        assertTrue(sprinterActual.equals(sprinterExpected)); 
	}

}
