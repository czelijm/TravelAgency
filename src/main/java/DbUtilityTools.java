import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtilityTools {
    public final String JDBC_Driver;
    public final String DB_URL;
    public final String USER_NAME;
    public final String USER_PASSWORD;
    public String dbName="TEST1";

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


}
