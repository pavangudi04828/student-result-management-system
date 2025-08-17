# Student Result Management System

A comprehensive console-based Student Result Management System built with Java and MySQL. This application allows educational institutions to manage student records, enter marks, calculate grades, and generate result summaries efficiently.

## 🌟 Features

- **Student Management**: Add and manage student records with personal details
- **Marks Entry**: Enter subject-wise marks for students
- **Result Calculation**: Automatic calculation of total marks, average, and grades
- **Grade System**: Intelligent grading system (A+, A, B, C, D, F)
- **CRUD Operations**: Complete Create, Read, Update, Delete functionality for marks
- **Result Summary**: Generate and store comprehensive result summaries
- **Data Persistence**: All data stored in MySQL database
- **Console Interface**: User-friendly menu-driven console application

## 🛠️ Technologies Used

- **Programming Language**: Java (JDK 8 or higher)
- **Database**: MySQL
- **JDBC Driver**: MySQL Connector/J
- **Development Environment**: Any Java IDE (Eclipse, IntelliJ IDEA, VS Code)

## 📊 Database Schema

### Tables Structure:

#### 1. **students** table
```sql
- student_id (INT, AUTO_INCREMENT, PRIMARY KEY)
- name (VARCHAR(100))
- student_class (VARCHAR(20))
- gender (VARCHAR(10))
```

#### 2. **marks** table
```sql
- mark_id (INT, AUTO_INCREMENT, PRIMARY KEY)
- student_id (INT, FOREIGN KEY)
- subject (VARCHAR(50))
- marks (INT)
```

#### 3. **result_summary** table
```sql
- Name (VARCHAR(100))
- Student_ID (INT, PRIMARY KEY)
- Grade (VARCHAR(5))
- Maths (INT)
- Science (INT)
- English (INT)
- Social (INT)
- Total (INT)
- Average (DECIMAL(5,2))
```

## 📂 Project Structure

```
student-result-management-system/
├── src/
│   └── project.java           # Main application file
├── database/
│   └── database_setup.sql     # Database creation scripts
└── README.md                  # Project documentation
```

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)** - Version 8 or higher
- **MySQL Server** - Version 5.7 or higher
- **MySQL Connector/J** - JDBC driver for MySQL

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/pavangudi04828/student-result-management-system.git
   cd student-result-management-system
   ```

2. **Setup MySQL Database**
   ```sql
   -- Create database
   CREATE DATABASE student_db;
   USE student_db;
   
   -- Run the database_setup.sql file or create tables manually
   ```

3. **Configure Database Connection**
   
   Update the database credentials in `src/project.java`:
   ```java
   String url = "jdbc:mysql://localhost:3306/student_db";
   String user = "your_mysql_username";
   String password = "your_mysql_password";
   ```

4. **Add MySQL JDBC Driver**
   - Download MySQL Connector/J from [MySQL official website](https://dev.mysql.com/downloads/connector/j/)
   - Add the JAR file to your project classpath

5. **Compile and Run**
   ```bash
   javac -cp ".:mysql-connector-java.jar" src/project.java
   java -cp ".:mysql-connector-java.jar" project
   ```

## 💻 Usage

### Main Menu Options:

1. **Add Student** - Register new students with name, class, and gender
2. **Enter Marks** - Add subject-wise marks for students
3. **View Result** - Display individual student results with grades
4. **Update Marks** - Modify existing marks using mark ID
5. **Delete Marks** - Remove specific marks entries
6. **Save Result to Summary** - Generate and save comprehensive results
7. **View All Results** - Display all students' final results in tabular format
8. **Exit** - Close the application

### Sample Workflow:

1. Start by adding students using option 1
2. Enter marks for different subjects using option 2
3. View individual results using option 3
4. Save final results to summary table using option 6
5. View all students' results using option 7

## 📊 Grading System

The system uses the following grading criteria:

| Average Score | Grade |
|---------------|-------|
| 90 - 100      | A+    |
| 80 - 89       | A     |
| 70 - 79       | B     |
| 60 - 69       | C     |
| 50 - 59       | D     |
| Below 50      | F     |

## ⭐ Key Functionalities

- **Automatic Grade Calculation**: Based on average marks
- **Data Validation**: Proper error handling for database operations
- **Flexible Subject Management**: Add marks for any subject
- **Result Summary Generation**: Compile results for institutional records
- **CRUD Operations**: Complete data management capabilities
- **Foreign Key Relationships**: Maintains data integrity

## 🔧 Technical Highlights

- **JDBC Integration**: Seamless database connectivity
- **Prepared Statements**: SQL injection prevention
- **Exception Handling**: Robust error management
- **Scanner Input**: User-friendly console interface
- **HashMap Usage**: Efficient data processing
- **Formatted Output**: Clean tabular data presentation

## 🚀 Future Enhancements

- **Web Interface**: Convert to web-based application
- **Authentication System**: Add login for teachers/administrators
- **Report Generation**: PDF/Excel export functionality
- **Attendance Management**: Track student attendance
- **Parent Portal**: Allow parents to view results
- **Backup/Restore**: Database backup functionality
- **Charts/Graphs**: Visual representation of results

## 📄 Database Commands Reference

```sql
-- View all data
SELECT * FROM students;
SELECT * FROM marks;
SELECT * FROM result_summary;

-- Reset database (if needed)
DROP TABLE marks;
DROP TABLE students;
DROP TABLE result_summary;
```

## 🤝 Contributing

Contributions are welcome! Please feel free to:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

## 📞 Contact

- **GitHub**: [@pavangudi04828](https://github.com/pavangudi04828)
- **Email**: pavankumar04828@gmail.com
- **LinkedIn**: [Pavan Gudi](https://linkedin.com/in/pavan-gudi)

---

⭐ **If you found this project helpful, please give it a star!** ⭐

---

*This project demonstrates database integration, CRUD operations, and console application development using Java and MySQL.*
