import java.sql.*;

public class Sql {
    // User credentials for PostGreSQL
    static String url = "jdbc:postgresql://localhost:5432/Students";
    static String user = "postgres";
    static String pw = "cs19DB22sql!";
    // Connection and statement declaration
    static Connection connection;
    static Statement statement;

    /*
    getAllStudents(): Retrieves and displays all records from the students table.
    addStudent(first_name, last_name, email, enrollment_date): Inserts a new student record into the students table.
    updateStudentEmail(student_id, new_email): Updates the email address for a student with the specified student_id.
    deleteStudent(student_id): Deletes the record of the student with the specified student_id.
    * */

    public static void getAllStudents(){
        // receive results by ResultSet
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            // Query into results
            statement.executeQuery("SELECT * FROM Students");
            resultSet = statement.getResultSet();

            // Get column names
            ResultSetMetaData md = resultSet.getMetaData();
            int count = md.getColumnCount();
            for (int i=1; i<=count; i++){
                String column = md.getColumnName(i);
                System.out.print(column + "\t");
            }System.out.println();

            // Displays data for each tuple
            while (resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t\t\t");
                System.out.print(resultSet.getString("first_name") + "\t\t");
                System.out.print(resultSet.getString("last_name") + "\t\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getDate("enrollment_date"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            // Format inserting query from the parameter values
            String query = String.format("INSERT INTO students (first_name, last_name, email, enrollment_date)\nVALUES ('%s', '%s', '%s', '%s');",
                                            first_name,last_name,email,enrollment_date);
            // Perform insert to the database
            statement.executeUpdate(query);

            System.out.println("Successfully ran the query: \n\n" + query);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateStudentEmail(int student_id, String new_email){
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            // Format inserting query from the parameter values
            String query = String.format("UPDATE students\n"     +
                                        "SET email = '%s' \n"     +
                                        "WHERE student_id=%d;",
                                         new_email, student_id);
            // Perform insert to the database
            statement.executeUpdate(query);

            System.out.println("Successfully ran the query: \n\n" + query);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteStudent(int student_id){
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            // Format inserting query from the parameter values
            String query = String.format("DELETE FROM students\n"     +
                                        "WHERE student_id=%d;",
                                        student_id);
            // Perform insert to the database
            statement.executeUpdate(query);

            System.out.println("Successfully ran the query: \n\n" + query);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pw);

            getAllStudents();
//            addStudent("Mark", "Ren", "Mark.Ren@carleton.ca", "2014-09-03");
//            updateStudentEmail(7, "antonyren@cmail.carleton.ca");
//            deleteStudent(7);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
