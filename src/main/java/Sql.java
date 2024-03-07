import java.sql.*;

public class Sql {
    // User credentials for PostGreSQL
    static String url = "jdbc:postgresql://localhost:5432/Students";
    static String user = "postgres";
    static String pw = "cs19DB22sql!";
    // Connection declaration
    static Connection connection;
    static Statement statement;

    /*
    getAllStudents(): Retrieves and displays all records from the students table.
    addStudent(first_name, last_name, email, enrollment_date): Inserts a new student record into the students table.
    updateStudentEmail(student_id, new_email): Updates the email address for a student with the specified student_id.
    deleteStudent(student_id): Deletes the record of the student with the specified student_id.
    * */

    public static void getAllStudents(){
        ResultSet resultSet;
        try {
            statement.executeQuery("SELECT * FROM Students");
            resultSet = statement.getResultSet();

//            ResultSetMetaData md = resultSet.getMetaData();
//            int count = md.getColumnCount();
//            for (int i=0; i<count; i++){
//            }

            while (resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getDate("enrollment_date"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pw);

            statement = connection.createStatement();

            getAllStudents();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
