package com.example.DAO;

import com.example.model.Student;

import java.sql.*;

public class DeleteStudentDAO {
    
   public boolean deleteStudent(Student student) {
    String sql = "DELETE FROM students WHERE id = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, student.getId());
        stmt.executeUpdate();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}



