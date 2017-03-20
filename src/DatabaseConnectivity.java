import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This example demonstrates the key classes and programming style
 * for accessing an SQL database
 * this applies equally well to a local as well as remote database.
 * NOTICE: do not import com.mysql.jdbc.Driver
 * get a reference to it through the class loader Class.forName("a class name")
 */
public class DatabaseConnectivity {
    public static void main(String[] args){
        //before using the class loader make sure you include the appropriate JAR file (see notes)
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Did you forget to include the JAR file in the project?");
        } finally {
            System.out.println("Loading completed...");
        }
        //after this point, we have access to the com.mysql.jdbc.Driver class

        /*
        in order to establish a connection to the database we need several pieces of information

        In my example I'll connect to the sql database daemon running on the local host
        for you all, you can replace localhost with zenit.senecac.on.ca
        ex. jdbc:mysql://zenit.senecac.on.ca/your_database_name
         */
        String databaseURI = "jdbc:mysql://localhost/biftest";
        //replace this info with your username and password as appropriate!
        String username = "bif";
        String password = "biftest";

        //SQL queries are represented as Strings in Java
        //note: you can compose these strings dynamically using the + operator

        //ex. a select query against the "patients" table
        String query1 = "SELECT id, name, address FROM patients";
        //ex. a query to insert a record into the "patients" table.
        String insertQuery = "INSERT INTO patients VALUES (NULL, 'Marek', '1 Seneca way')";

        //let's connect to the database and execute queries

        //declare references to our key objects that we are going to use

        //represents the database connection
        java.sql.Connection connection = null;
        //represents a SQL query
        Statement statement = null;
        //represents rows returned by a query
        ResultSet resultSet = null;
        //a boolean flag to remember whether we have a connection or not
        boolean connected = false;
        System.out.println("Attempting to connect to database: " + databaseURI + " with username: " + username + " password: " + password);
        try{
            connection = DriverManager.getConnection(databaseURI, username, password);
            connected = true;
        }catch (SQLException e){
            e.printStackTrace();
            connected = false;
        }
        if(connected == true){
            System.out.println("succesfully connected to the database");
        }

    }
}
