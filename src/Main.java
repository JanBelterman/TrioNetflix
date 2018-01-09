import database.Database;
import userInterface.UserInterface;

public class Main {

    public static void main(String[] args) {

        Database database = new Database("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Netflix;integratedSecurity=true;");

        UserInterface userInterface = new UserInterface(database);

        userInterface.run();

    }

}