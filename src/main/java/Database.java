import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Database {
    private DbUtilityTools dbUtilityTools;
    {
        try {
            dbUtilityTools = new DbUtilityTools();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private String url;
    private TravelData travelData;

    public Database(DbUtilityTools dbUtilityTools, String url, TravelData travelData) {
        this.dbUtilityTools = dbUtilityTools;
        this.url = url;
        this.travelData = travelData;
    }

    public Database(String url, TravelData travelData) {
        this.dbUtilityTools = dbUtilityTools;
        this.url = url;
    }



    public void create() {
        try {
            dbUtilityTools.createDataBase();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public void drop(){
        dbUtilityTools.dropDataBase();
    }
}
