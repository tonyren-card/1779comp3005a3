import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sql {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String pw = "cs19DB22sql!";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, pw);

            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM Students");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
