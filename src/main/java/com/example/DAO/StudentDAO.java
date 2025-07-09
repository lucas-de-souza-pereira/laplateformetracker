package com.example.DAO;

import com.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students ORDER BY id";

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


public static List<Student> searchStudents(String field, String keyword) {
    List<Student> students = new ArrayList<>();
    String sql;

    if (field.equals("id") || field.equals("age")) {
        sql = "SELECT * FROM students WHERE " + field + " = ?";
    } else {
        sql = "SELECT * FROM students WHERE " + field + " ILIKE ?";
    }

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        if (field.equals("id") || field.equals("age")) {
            stmt.setInt(1, Integer.parseInt(keyword));
        } else {
            stmt.setString(1, "%" + keyword + "%");
        }

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("class"),
                        rs.getDouble("average")
                ));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return students;
}


    

}
