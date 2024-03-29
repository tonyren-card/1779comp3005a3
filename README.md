Antony Ren - 101151779 - COMP3005 A3

Demo video: https://mediaspace.carleton.ca/media/Antony+Ren+-+COMP3005+A3Q1+demo/1_pesnk0kl

Application type: Command line interface

Language: Java (Intellij IDEA)

How to run program:
  1. Set up the Students database in pgAdmin if not already

     a. Open the CREATE.sql file in Asmt3/src/sql, and run the query in pgAdmin

     b. Open the INSERT.sql file in Asmt3/src/sql, and run the query in pgAdmin

  2. Open the pom.xml file, in the Asmt3 folder, and ensure the dependencies for Postgresql are there (you may need to modify based on your version). If you're using Intellij IDEA, make sure you refresh Maven to ensure that it is connected to Postgresql database.
  3. Locate the Sql.java file, in Asmt3/src/main/java
  4. From lines 6-8, modify the user credentials based on your info for Postgresql
  5. Compile the program depending on what IDE you use (if you use Intellij IDEA, the program should be compiled automatically)
  6. Run the program
  7. Type a number to make a selection (1: getAllStudents(), 2: addStudent(), 3: updateStudentEmail(), 4: deleteStudent()) and follow the command-line prompts
  8. To quit program, type 'q' (at the 'Select an option' page)

Other notes:
- In case the program can't run, if you're using Intellij IDEA, rebuild project (Build > Rebuild Project)
- All source code was written in one Java file (Sql.java)
- Program was created using MacBook Pro M1 Pro model
