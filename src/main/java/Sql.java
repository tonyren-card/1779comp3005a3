import java.sql.Connection;
import java.sql.DriverManager;

public class Sql {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String pw = "cs19DB22sql!";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, pw);
            if (connection != null) {
                System.out.println("Connected to the database\n\n");
            } else {
                System.out.println("Failed to connect to the database");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
