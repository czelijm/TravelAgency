import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class Database {
    private Gui gui;
    private String url="";
    private TravelData travelData;
    private List records;


    private DbUtilityTools dbUtilityTools;
//    {
//        try {
//            dbUtilityTools = new DbUtilityTools(url);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }


    public Database(DbUtilityTools dbUtilityTools, String url, TravelData travelData) {
        this.dbUtilityTools = dbUtilityTools;
        this.url = url;
        this.travelData = travelData;
    }

    public Database(String url, TravelData travelData) {
//        this.dbUtilityTools = dbUtilityTools;
        try {
            dbUtilityTools = new DbUtilityTools(url);
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
        this.url = url;
    }

    public void create() {
        drop();
        createNew();
    }

    public void createNew() {
        try {
            dbUtilityTools.createDataBase();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        readCSVFilesAndCommitToDb();
    }

    public void drop(){
        dbUtilityTools.dropDataBase();
    }

    public void readCSVFilesAndCommitToDb(){
        this.dbUtilityTools.readCSVFilesAndCommitToDb();
    }

    public List readDataFromDb()
    {
        records = this.dbUtilityTools.readDataFromDB();
        return records;
    }

    public void showGui() {
        readDataFromDb();
        gui = new Gui(records);
        gui.run();
    }
}

