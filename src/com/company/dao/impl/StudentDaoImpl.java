package com.company.dao.impl;

import com.company.dao.StudentDao;
import com.company.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao {

    @Override
    public ArrayList<Student> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Student> students;

        try {
            connection = getConnection();

            String readQuery = "SELECT * FROM tb_students;";

            preparedStatement = connection.prepareStatement(readQuery);
            resultSet = preparedStatement.executeQuery();

            students = new ArrayList<>();

            for (int i = 0; i <= students.size() && resultSet.next(); i++) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhoneNumber(resultSet.getString("phone_number"));
                student.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());

                students.add(student);
            }

            return students;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }
        return null;
    }
}
