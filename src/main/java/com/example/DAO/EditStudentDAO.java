package com.example.DAO;

import com.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class EditStudentDAO {
    
    public boolean updateStudent(Student student) {
    String sql = "UPDATE students SET first_name = ?, last_name = ?, age = ?, class = ?, average = ? WHERE id = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, student.getFirstName());
        stmt.setString(2, student.getLastName());
        stmt.setInt(3, student.getAge());
        stmt.setString(4, student.getClasse());
        stmt.setDouble(5, student.getAverage());
        stmt.setInt(6, student.getId());

        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
