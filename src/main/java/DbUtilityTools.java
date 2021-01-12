import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DbUtilityTools {
    public final String JDBC_Driver;
    public final String DB_URL;
    public final String USER_NAME;
    public final String USER_PASSWORD;
    public String dbName="TEST1";
    public final String PERSISTENCE_UNIT = "org.hibernate.tutorial.jpa";
    public final String dirDirectoryString = "data";

    public DbUtilityTools(String JDBC_Driver, String DB_URL, String user_name, String user_password) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.JDBC_Driver = JDBC_Driver;
        this.DB_URL = DB_URL;
        USER_NAME = user_name;
        USER_PASSWORD = user_password;
        Class.forName(this.JDBC_Driver).getDeclaredConstructor().newInstance();
    }

    public DbUtilityTools() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/","root","Buster");
    }
    public DbUtilityTools(String url) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this("com.mysql.cj.jdbc.Driver",url,"root","Buster");
    }

    public Connection connectToDb() throws SQLException {
       return DriverManager.getConnection(DB_URL,USER_NAME,USER_PASSWORD);
    }

    public void executeSQLQuerry(String sqlQuerry){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = connectToDb();
            statement = connection.createStatement();;
            statement.execute(sqlQuerry);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void createDataBase()  {
        executeSQLQuerry("CREATE DATABASE "+dbName);
    }

    public void dropDataBase()  {
        executeSQLQuerry("DROP DATABASE "+dbName);
    }


    public void readCSVFilesAndCommitToDb(){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);


        Map<String, String> persistenceMap = new HashMap<String, String>();
        persistenceMap.put("javax.persistence.jdbc.url", DB_URL+dbName);
//        persistenceMap.put("javax.persistence.jdbc.user", "<username>");
//        persistenceMap.put("javax.persistence.jdbc.password", "<password>");
//        persistenceMap.put("javax.persistence.jdbc.driver", "<driver>");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, persistenceMap);

        //Read csv files
        var result = CSVReader.readAllCSVFilesInDirectory(dirDirectoryString);

        List<String[]> processedList = new LinkedList<>();

//        result.stream().forEach(m->{
//           processedList.add(LocaleUtility.convertStringOfferByLocaleWithLocaleInfo(m,new Locale("en")) );
//        });

        List<TravelOffer> listToPersist = new LinkedList<>();
        for (var item: result) {
            listToPersist.add(new TravelOffer(item));
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //begin transaction
        entityManager.getTransaction().begin();

        //persisting instances to the table
        for (var item: listToPersist) {
            entityManager.persist(item);
        }
        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List readDataFromDB(){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

        Map<String, String> persistenceMap = new HashMap<String, String>();
        persistenceMap.put("javax.persistence.jdbc.url", DB_URL+dbName);
//        persistenceMap.put("javax.persistence.jdbc.user", "<username>");
//        persistenceMap.put("javax.persistence.jdbc.password", "<password>");
//        persistenceMap.put("javax.persistence.jdbc.driver", "<driver>");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, persistenceMap);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //begin transaction
        entityManager.getTransaction().begin();

        String querrString = "from TravelOffer";

        Query query = entityManager.createQuery(querrString);

        List result = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

//        System.out.println(result.size());
        return result;

    }

}


