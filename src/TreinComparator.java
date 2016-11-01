import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class TreinComparator implements Comparator<AbstractTrein> {
    @Override
    public int compare(AbstractTrein a, AbstractTrein b) {
		try {
			java.lang.Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AbstractStation aVertrekStation = a.getStations().get(0);
		AbstractStation bVertrekStation = b.getStations().get(0);
		int i = aVertrekStation.getName().compareToIgnoreCase(bVertrekStation.getName());
		if(i == 0) { 
			return i;
		}
		
		AbstractStation aEindStation = a.getStations().get(a.getStations().size() - 1);
		AbstractStation bEindStation = b.getStations().get(b.getStations().size() - 1);
		i = aEindStation.getName().compareToIgnoreCase(bEindStation.getName());
		if(i == 0) { 
			return i;
		}

		DateFormat sdf = new SimpleDateFormat("hh:mm");
		try {
			Date aVertrek = sdf.parse(a.getVertrek());
			Date bVertrek = sdf.parse(b.getVertrek());
			i = aVertrek.compareTo(bVertrek);
		} catch (ParseException e) {
			return i;
		}
		
		return i;
    }
}
