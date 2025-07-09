package com.example.DAO;

import com.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("age"),
                    rs.getString("class"),
                    rs.getDouble("average")
                    

                );
                students.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean addStudent(Student student){

        String psql =   "INSERT INTO students " +
                        "(first_name , last_name , age , class , average)" +
                        "VALUES (? , ? , ? , ? , ?)";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(psql)) {

                stmt.setString(1, student.getFirstName());
                stmt.setString(2, student.getLastName());
                stmt.setInt(3, student.getAge());
                stmt.setString(4, student.getClasse());
                stmt.setDouble(5, student.getAverage());

                stmt.executeUpdate();
                return true;
                }
        catch (SQLException e){
            e.printStackTrace();
            return false;
            }
    }


}
