package main;

import controller.StudentController2;
import model.StudentDTO;
import viewer.StudentViewer2;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentMain2 {
    public static void main(String[] args) {
        StudentController2 studentController = new StudentController2();
        StudentViewer2 studentViewer = new StudentViewer2();
        Scanner scanner = new Scanner(System.in);

        studentViewer.setStudentController(studentController);
        studentViewer.setScanner(scanner);

        studentViewer.showList();
    }
}
