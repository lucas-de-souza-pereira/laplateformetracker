package com.example.DAO;

import com.example.model.Student;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportExportDAO {
    


    public static boolean exportToCSV(String filePath, List<Student> students) {
    try (PrintWriter writer = new PrintWriter(new File(filePath))) {
        writer.println("id,first_name,last_name,age,class,average");

        for (Student s : students) {
            writer.printf("%d,%s,%s,%d,%s,%.2f%n",
                s.getId(),
                s.getFirstName(),
                s.getLastName(),
                s.getAge(),
                s.getClasse(),
                s.getAverage()
            );
        }

        return true;

    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

}
