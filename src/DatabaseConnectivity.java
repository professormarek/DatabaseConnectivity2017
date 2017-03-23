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
        String password = "bifsecret";

        //SQL queries are represented as Strings in Java
        //note: you can compose these strings dynamically using the + operator

        /*
        ex. a query to create a table (in this case the table we're using in the example)
        try executing the table creation and deletion queires as an exercise
        String createTable = "CREATE TABLE patients (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), address VARCHAR(30)";
        String dropTable = "DROP TABLE patients";
         */

        //ex. a select query against the "patients" table
        String selectQuery = "SELECT id, name, address FROM patients";
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
            //connect to database
            connection = DriverManager.getConnection(databaseURI, username, password);
            //create a Statement that we will use to interact with the database
            statement = connection.createStatement();
            connected = true;
        }catch (SQLException e){
            e.printStackTrace();
            connected = false;
        }
        if(connected == true){
            System.out.println("succesfully connected to the database");


            //perform a SELECT query to access rows out of our patients table
            System.out.println("Attempting to query records from the database");
            try{
                resultSet = statement.executeQuery(selectQuery);
                /*
                notice that next() will take us to the first record
                and then each subsequent call will take us to the next record (as the name suggests)
                will return 0 or false if there are no more records
                 */
                while(resultSet.next()){
                    //demonstrate pulling individual fields out of the row
                    //here we use primitive variables to represent each field
                    //we could use a "Patient" class instead. (left as an exercise)
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    //try to get the address yourself

                    //use System.out.println to display the row
                    System.out.println("ID: " + id + " NAME: " + name);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            //test to see if the connection is working
            //ex. insert a record into the patients table
            System.out.println("inserting a row into the patients table");
            try{
                //recall we use executeUpdate (Rather than executeQuery) to modify the table
                statement.executeUpdate(insertQuery);
            } catch (SQLException e){
                e.printStackTrace();
            }

        }




    }
}
