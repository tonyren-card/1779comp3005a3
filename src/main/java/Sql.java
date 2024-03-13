import java.sql.*;
import java.util.Scanner;

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
                System.out.print(column + "\t\t");
            }System.out.println();

            // Displays data for each tuple
            while (resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t\t\t\t");
                System.out.print(resultSet.getString("first_name") + "\t\t\t");
                System.out.print(resultSet.getString("last_name") + "\t\t");
                System.out.print(resultSet.getString("email") + "\t\t");
                System.out.println(resultSet.getDate("enrollment_date"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
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
        try {
            statement = connection.createStatement();
            // Format inserting query from the parameter values
            String query = String.format("UPDATE students\n"     +
                                        "SET email = '%s' \n"     +
                                        "WHERE student_id=%d;",
                                         new_email, student_id);
            // Perform update to the database
            statement.executeUpdate(query);

            System.out.println("Successfully ran the query: \n\n" + query);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteStudent(int student_id){
        try {
            statement = connection.createStatement();
            // Format inserting query from the parameter values
            String query = String.format("DELETE FROM students\n"     +
                                        "WHERE student_id=%d;",
                                        student_id);
            // Perform deletion to the database
            statement.executeUpdate(query);

            System.out.println("Successfully ran the query: \n\n" + query);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    // Command line interface
    public static void runInterface(){
        String input;
        Scanner scanner = new Scanner(System.in);
        // runs the CLI in a loop, until the user quits
        do{
            System.out.println("\nSelect an option (type a number for selection, 'q' to quit):");
            System.out.println("\t1: List all students' records");
            System.out.println("\t2: Add a student");
            System.out.println("\t3: Update student's email address");
            System.out.println("\t4: Delete student");
            // Gets user's input
            input = scanner.nextLine();

            // Executes the following scenario based on user's input
            switch (input) {
                case "1":
                    System.out.println();
                    getAllStudents();
                    break;
                case "2":
                    System.out.println();
                    executeCase2();
                    break;
                case "3":
                    System.out.println();
                    executeCase3();
                    break;
                case "4":
                    System.out.println();
                    executeCase4();
                    break;
            }
        }while (!input.equals("q"));
    }

    // private helper function for "case 2" (addStudent())
    private static void executeCase2() {
        //Gets user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first name:");
        String fn = scanner.nextLine();
        System.out.println("Enter the last name:");
        String ln = scanner.nextLine();
        System.out.println("Enter the email address:");
        String email = scanner.nextLine();
        System.out.println("Enter the enrollment date (YYYY-MM-DD):");
        String eDate = scanner.nextLine();
        System.out.println();
        //Performs add query
        addStudent(fn,ln,email,eDate);
    }

    // private helper function for "case 3" (updateStudent())
    private static void executeCase3() {
        //Gets user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the student id number to update:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new email address for this student:");
        String email = scanner.nextLine();
        System.out.println();
        //Performs update query
        updateStudentEmail(id, email);
    }

    // private helper function for "case 4" (deleteStudent())
    private static void executeCase4() {
        //Gets user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the student id number to remove:");
        int id = scanner.nextInt();
        System.out.println();
        //Performs deletion query
        deleteStudent(id);
    }

    public static void main(String[] args) {
        try {
            // connect to PostgreSQL server
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pw);
            if (connection != null){
                System.out.println("Connected to the database\n");
            }
            else{
                System.out.println("Failed to connect to the database");
            }
            runInterface();

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
