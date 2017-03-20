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


    }
}
