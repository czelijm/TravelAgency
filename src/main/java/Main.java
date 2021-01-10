import java.io.*;
        import java.util.*;

public class Main {

    public static void main(String[] args) {
        File dataDir = new File("data");
        TravelData travelData = new TravelData(dataDir);
        String url = "";/*<-- tu należy wpisać URL bazy danych */
        Database db = new Database(url, travelData);
        db.drop();
        db.create();

        db.readCSVFilesAndCommitToDb();
        List result = db.readDataFromDb();

        db.showGui();


//        var result = CSVReader.readAllCSVFilesInDirectory(dataDir.getPath());

//        result.stream().forEach(System.out::println);
//        result.stream().forEach(
//                m->{
//                    Arrays.stream(m).forEach(System.out::print);
//                    System.out.println();
//                })
//        ;
//        CSVReader.showReadedFiles(result);
//
//        TravelOffer offer = new TravelOffer(result.get(0));


//        //Getting country names in other languages
//        //ok
//        Locale locale = new Locale("en","US");
//        Locale localePl = new Locale("pl");
//        System.out.println(locale.getDisplayCountry(localePl));
//        System.out.println();
    }

//    public static void main(String[] args) {
//        File dataDir = new File("data");
//        TravelData travelData = new TravelData(dataDir);
//        String dateFormat = "yyyy-MM-dd";
//        for (String locale : Arrays.asList("pl_PL", "en_GB")) {
//            List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
//            for (String od : odlist) System.out.println(od);
//        }
//        // --- część bazodanowa
//        String url = "";/*<-- tu należy wpisać URL bazy danych */
//                Database db = new Database(url, travelData);
//        db.create();
//        db.showGui();
//    }

}
