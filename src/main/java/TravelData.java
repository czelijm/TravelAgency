import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TravelData {
    private File dataDir;
    public TravelData(File dataDir) {
        this.dataDir = dataDir;
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        var x = CSVReader.readAllCSVFilesInDirectory(dataDir.getAbsolutePath());
        Locale tmpL = new Locale(locale.split("_")[0]);
        List<String> result = new ArrayList<>();
        x.stream().forEach(m->{
           var y = LocaleUtility.convertStringOfferByLocale(m,tmpL);
            result.add(String.join("",y));
        });
        return result;
    }
}
