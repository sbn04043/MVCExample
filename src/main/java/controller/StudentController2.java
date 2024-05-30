package controller;

import model.StudentDTO;

import java.sql.*;
import java.util.ArrayList;

public class StudentController2 {
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/board";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public StudentController2() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //1. selectAll()
    public ArrayList<StudentDTO> selectAll() {
        ArrayList<StudentDTO> list = new ArrayList<>();
        String query = "SELECT * FROM student";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StudentDTO student = new StudentDTO();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setKorean(resultSet.getInt("korean"));
                student.setEnglish(resultSet.getInt("english"));
                student.setMath(resultSet.getInt("math"));

                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //2. selectOne(id)
    public StudentDTO selectOne(int id) {
        StudentDTO student = null;
        String query = "SELECT * FROM student WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                student = new StudentDTO();

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setKorean(resultSet.getInt("korean"));
                student.setEnglish(resultSet.getInt("english"));
                student.setMath(resultSet.getInt("math"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    //3. insert(StudentDTO)
    public void insert(StudentDTO student) {
        String query = "INSERT INTO student(name, korean, english, math) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getKorean());
            preparedStatement.setInt(3, student.getEnglish());
            preparedStatement.setInt(4, student.getMath());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4. update(StudentDTO)
    public void update(StudentDTO student) {
        String query = "UPDATE student SET name = ?, korean = ?, english = ?, math = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getKorean());
            preparedStatement.setInt(3, student.getEnglish());
            preparedStatement.setInt(4, student.getMath());
            preparedStatement.setInt(5, student.getId());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM student WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidateId(int id) {
        if (id == 0) return true;
        StudentDTO student = selectOne(id);
        return student != null;
    }
}
