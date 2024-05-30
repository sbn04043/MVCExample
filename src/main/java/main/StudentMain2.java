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


//        System.out.println("===================================");
//        ArrayList<StudentDTO> studentList = studentController.selectAll();
//        for (StudentDTO s : studentList) {
//            System.out.println(s);
//        }
//
//        System.out.println("===================================");
//        StudentDTO student = studentController.selectOne(3);
//        System.out.println(student);
//
//        System.out.println("===================================");
//        StudentDTO student2 = new StudentDTO();
//
//        student2.setName("바바바");
//        student2.setKorean(50);
//        student2.setEnglish(50);
//        student2.setMath(50);
//
//        //studentController.insert(student2);
//
////        studentList = studentController.selectAll();
////        for (StudentDTO s : studentList) {
////            System.out.println(s);
////        }
//
//        System.out.println("===================================");
//        StudentDTO student3 = new StudentDTO();
//        student3.setId(3);
//        student3.setName("사사사");
//        student3.setKorean(100);
//        student3.setEnglish(100);
//        student3.setMath(100);
//
//        //studentController.update(student3);
//
////        studentList = studentController.selectAll();
////        for (StudentDTO s : studentList) {
////            System.out.println(s);
////        }
//
//        System.out.println("===================================");
//        studentController.delete(6);
//        studentList = studentController.selectAll();
//        for (StudentDTO s : studentList) {
//            System.out.println(s);
//        }
//
//        System.out.println("===================================");
    }
}
