import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.*;

public class project {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String password = "12345678";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB!");

            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- Student Result System ---");
                System.out.println("1. Add Student");
                System.out.println("2. Enter Marks");
                System.out.println("3. View Result");
                System.out.println("4. Update Marks");
                System.out.println("5. Delete Marks");
                System.out.println("6. Save Result to Summary Table");
                System.out.println("7. View All Results (Summary)");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // flush newline

                switch (choice) {
                    case 1:
                        addStudent(conn);
                        break;
                    case 2:
                        enterMarks(conn);
                        break;
                    case 3:
                        viewResult(conn);
                        break;
                    case 4:
                        updateMarks(conn);
                        break;
                    case 5:
                        deleteMarks(conn);
                        break;
                    case 6:
                        saveResultToSummary(conn);
                        break;
                    case 7:
                        viewAllResults(conn);
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }

            } while (choice != 8);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter class: ");
            String studentClass = sc.nextLine();

            System.out.print("Enter gender: ");
            String gender = sc.nextLine();

            String sql = "INSERT INTO students (name, student_class, gender) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, studentClass);
            pstmt.setString(3, gender);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enterMarks(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter student ID: ");
            int studentId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter subject name: ");
            String subject = sc.nextLine();

            System.out.print("Enter marks: ");
            int marks = sc.nextInt();

            String sql = "INSERT INTO marks (student_id, subject, marks) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.setString(2, subject);
            pstmt.setInt(3, marks);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Marks added successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewResult(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter student ID to view result: ");
            int studentId = sc.nextInt();

            String studentQuery = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
            studentStmt.setInt(1, studentId);
            ResultSet studentRs = studentStmt.executeQuery();

            if (!studentRs.next()) {
                System.out.println("Student not found.");
                return;
            }

            String name = studentRs.getString("name");
            String studentClass = studentRs.getString("student_class");
            String gender = studentRs.getString("gender");

            String marksQuery = "SELECT mark_id, subject, marks FROM marks WHERE student_id = ?";
            PreparedStatement marksStmt = conn.prepareStatement(marksQuery);
            marksStmt.setInt(1, studentId);
            ResultSet marksRs = marksStmt.executeQuery();

            Map<String, Integer> subjectMarks = new HashMap<>();
            int total = 0, count = 0;

            System.out.println("\nStudent Details:");
            System.out.println("Name   : " + name);
            System.out.println("Class  : " + studentClass);
            System.out.println("Gender : " + gender);

            System.out.println("\nMarks:");
            System.out.printf("| %-8s | %-10s | %-5s |\n", "mark_id", "Subject", "Marks");
            System.out.println("--------------------------------------");

            while (marksRs.next()) {
                int markId = marksRs.getInt("mark_id");
                String subject = marksRs.getString("subject");
                int mark = marksRs.getInt("marks");
                subjectMarks.put(subject, mark);
                total += mark;
                count++;

                System.out.printf("| %-8d | %-10s | %-5d |\n", markId, subject, mark);
            }

            if (count == 0) {
                System.out.println("No marks found for this student.");
                return;
            }

            double avg = total / (double) count;
            String grade = getGrade(avg);

            System.out.println("\nTotal   : " + total);
            System.out.println("Average : " + String.format("%.2f", avg));
            System.out.println("Grade   : " + grade);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateMarks(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter mark_id to update: ");
            int markId = sc.nextInt();

            System.out.print("Enter new marks: ");
            int newMarks = sc.nextInt();

            String sql = "UPDATE marks SET marks = ? WHERE mark_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newMarks);
            pstmt.setInt(2, markId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Marks updated successfully!");
            } else {
                System.out.println(" mark_id not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteMarks(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter mark_id to delete: ");
            int markId = sc.nextInt();

            String sql = "DELETE FROM marks WHERE mark_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, markId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Mark deleted successfully!");
            } else {
                System.out.println("️ mark_id not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveResultToSummary(Connection conn) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter student ID to save result: ");
            int studentId = sc.nextInt();

            String studentQuery = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
            studentStmt.setInt(1, studentId);
            ResultSet studentRs = studentStmt.executeQuery();

            if (!studentRs.next()) {
                System.out.println(" Student not found.");
                return;
            }

            String name = studentRs.getString("name");

            String marksQuery = "SELECT subject, marks FROM marks WHERE student_id = ?";
            PreparedStatement marksStmt = conn.prepareStatement(marksQuery);
            marksStmt.setInt(1, studentId);
            ResultSet marksRs = marksStmt.executeQuery();

            Map<String, Integer> subjectMarks = new HashMap<>();
            int total = 0, count = 0;

            while (marksRs.next()) {
                String subject = marksRs.getString("subject");
                int mark = marksRs.getInt("marks");
                subjectMarks.put(subject, mark);
                total += mark;
                count++;
            }

            if (count == 0) {
                System.out.println(" No marks found for this student.");
                return;
            }

            double avg = total / (double) count;
            String grade = getGrade(avg);

            String insertSQL = "REPLACE INTO result_summary (Name, Student_ID, Grade, Maths, Science, English, Social, Total, Average) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
            insertStmt.setString(1, name);
            insertStmt.setInt(2, studentId);
            insertStmt.setString(3, grade);
            insertStmt.setInt(4, subjectMarks.getOrDefault("Maths", 0));
            insertStmt.setInt(5, subjectMarks.getOrDefault("Science", 0));
            insertStmt.setInt(6, subjectMarks.getOrDefault("English", 0));
            insertStmt.setInt(7, subjectMarks.getOrDefault("Social", 0));
            insertStmt.setInt(8, total);
            insertStmt.setDouble(9, avg);

            int rows = insertStmt.executeUpdate();
            if (rows > 0) {
                System.out.println(" Result saved to result_summary table!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewAllResults(Connection conn) {
        try {
            String sql = "SELECT * FROM result_summary";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- All Students Final Results ---\n");
            System.out.printf("| %-15s | %-10s | %-5s | %-6s | %-7s | %-7s | %-6s | %-5s | %-7s |\n",
                    "Name", "Student_ID", "Grade", "Maths", "Science", "English", "Social", "Total", "Average");
            System.out.println("-------------------------------------------------------------------------------------------");

            while (rs.next()) {
                String name = rs.getString("Name");
                int id = rs.getInt("Student_ID");
                String grade = rs.getString("Grade");
                int maths = rs.getInt("Maths");
                int science = rs.getInt("Science");
                int english = rs.getInt("English");
                int social = rs.getInt("Social");
                int total = rs.getInt("Total");
                double avg = rs.getDouble("Average");

                System.out.printf("| %-15s | %-10d | %-5s | %-6d | %-7d | %-7d | %-6d | %-5d | %-7.2f |\n",
                        name, id, grade, maths, science, english, social, total, avg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getGrade(double avg) {
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }
}

