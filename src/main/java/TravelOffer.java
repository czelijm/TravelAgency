import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "TravelOffer")
public class TravelOffer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false,nullable = false)
    private String id;
    @Column(name = "localisationInformation")
    private String locale;
    @Column(name = "destinationCountry")
    private String destinationCountry;
    @Column(name = "departue")
    private Date departue;
    @Column(name = "arrive")
    private Date arrive;
    @Enumerated(EnumType.STRING)
    @Column(name = "palce")
    private Place place;
    @Column(name = "price")
    private String price;
    @Column(name ="currency")
    private String currency;

    public TravelOffer() {
    }

//    public TravelOffer(String locale, String destinationCountry, Date departue, Date arrive, Place place, String price, String currency) {
//        this.locale = locale;
//        this.destinationCountry = destinationCountry;
//        this.departue = departue;
//        this.arrive = arrive;
//        this.place = place;
//        this.price = price;
//        this.currency = currency;
//    }

    public TravelOffer(String[] args) {
        String pattern = "yyyy-MM-dd";
        Date dateD = null;
        Date dateA = null;
        try {
            dateD = new SimpleDateFormat(pattern).parse(args[2]);
            dateA = new SimpleDateFormat(pattern).parse(args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.locale = args[0];
        this.destinationCountry = args[1];
        this.departue = dateD;
        this.arrive = dateA;
//        this.place = args[0].substring(0,2).toLowerCase().equals("pl")?
//                PlacePL.valueOf(args[4]):PlaceEN.valueOf(args[4]);
        this.place = Place.valueOf(args[4]);
        this.price = args[5];
        this.currency = args[6];
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public Date getDepartue() {
        return departue;
    }

    public void setDepartue(Date departue) {
        this.departue = departue;
    }

    public Date getArrive() {
        return arrive;
    }

    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}