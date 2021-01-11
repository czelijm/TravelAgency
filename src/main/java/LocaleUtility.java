import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class LocaleUtility {

    public static HashMap<String,String> placePLtoEN;
    public static HashMap<String,String> placeENtoPL;
    public static HashMap<String,HashMap> placeLanguage;

    static {
        placePLtoEN = new HashMap<>();
        placePLtoEN.put("morze","sea");
        placePLtoEN.put("gory","mountains");
        placePLtoEN.put("jezioro","lake");
        placeENtoPL = new HashMap<>();
        placeENtoPL.put("sea","morze");
        placeENtoPL.put("mountains","gory");
        placeENtoPL.put("lake","jezioro");
        placeLanguage = new HashMap<>();
        placeLanguage.put("en",placePLtoEN);
        placeLanguage.put("pl",placeENtoPL);
    }

    public static  String getCountryTranslateFromLocaleToLocale(String countryName, Locale localeFromTranslate, Locale localeToTranslate) {
        if(localeFromTranslate.toLanguageTag().equalsIgnoreCase(localeToTranslate.toLanguageTag())) return countryName;
        for (Locale l : Locale.getAvailableLocales()) {
            if (l.getDisplayCountry(localeFromTranslate).equals(countryName)) {
                return l.getDisplayCountry(localeToTranslate);
            }
        }
        return null;
    }

    public static String convertToCurrencyFormat(String amount,Locale localeFromTranslate, Locale localeToTranslate) {

        if(localeFromTranslate.toLanguageTag().equalsIgnoreCase(localeToTranslate.toLanguageTag())) return amount;

//        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeToTranslate);
        NumberFormat numberFormat = NumberFormat.getInstance(localeToTranslate);

        String processedAmount = amount.contains(".")&&amount.contains(",")?amount.replaceAll(",",""):amount.replaceAll(",",".");

//        BigDecimal bigDecimal = new BigDecimal(processedAmount);

        return numberFormat.format(new BigDecimal(processedAmount));
    }

    public static String convertPlaceLanguage(String place,Locale localeFromTranslate, Locale localeToTranslate){
        if(localeFromTranslate.toLanguageTag().equalsIgnoreCase(localeToTranslate.toLanguageTag())) return place;
        return (String) placeLanguage.get(localeToTranslate.toLanguageTag()).get(place);
    }

    public static String formatDateNoHours(Date d, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.format(d);
        return simpleDateFormat.format(d);
    }


}
