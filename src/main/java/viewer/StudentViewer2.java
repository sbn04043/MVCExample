package viewer;

import controller.StudentController2;
import lombok.Setter;
import model.StudentDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

@Setter
public class StudentViewer2 {
    private StudentController2 studentController;
    private Scanner scanner;

    public void showList() {
        String message = "1. 학생들 보기 2. 학생 입력 3. 프로그램 종료";
        while (true) {
            int choice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (choice == 1) {
                showStudentList();
            } else if (choice == 2) {
                insertStudent();
            } else if (choice == 3) {
                System.out.println("프로그램을 종료합니다");
                return;
            }
        }
    }

    public void showStudentList() {
        ArrayList<StudentDTO> studentList = studentController.selectAll();
        for (StudentDTO s : studentList) {
            System.out.printf("%d. %s\n", s.getId(), s.getName());
        }

        String message = "상세히 볼 학생을 입력하세요(뒤로가기 0)";
        int choice = ScannerUtil.nextInt(scanner, message);

        while (!studentController.isValidateId(choice)) {
            message = "올바른 번호가 아닙니다 다시 눌러주세요(뒤로가기 0)";
            choice = ScannerUtil.nextInt(scanner, message);
        }

        if (choice == 0) {
            return;
        } else {
            showOneStudent(choice);
        }
    }

    public void showOneStudent(int id) {
        StudentDTO student = studentController.selectOne(id);
        System.out.printf("%d. %s\n", student.getId(), student.getName());
        System.out.printf("국어: %3d, 영어: %3d, 수학%3d\n", student.getKorean(), student.getEnglish(), student.getMath());

        String message = "1. 수정 2. 제거 3. 뒤로가기";
        int choice = ScannerUtil.nextInt(scanner, message, 1, 3);
        if (choice == 1) {
            update(id);
        } else if (choice == 2) {
            delete(id);
        } else if (choice == 3) {
            return;
        }
    }

    public void update(int id) {
        StudentDTO student = new StudentDTO();
        student.setId(id);

        String message = "변경할 이름을 입력하세요";
        student.setName(ScannerUtil.nextLine(scanner, message));

        message = "변경할 국어 점수를 입력하세요";
        student.setKorean(ScannerUtil.nextInt(scanner, message, 0, 100));

        message = "변경할 영어 점수를 입력하세요";
        student.setEnglish(ScannerUtil.nextInt(scanner, message, 0, 100));

        message = "변경할 수학 점수를 입력하세요";
        student.setMath(ScannerUtil.nextInt(scanner, message, 0, 100));

        studentController.update(student);
        showOneStudent(id);
    }

    public void delete(int id) {
        String message = "정말로 삭제하시겠습니까? (Y/N)";
        String answer = ScannerUtil.nextLine(scanner, message);

        while (!(answer.equalsIgnoreCase("Y")
                || answer.equalsIgnoreCase("N"))) {
            message = "잘못 입력하셨습니다. (Y/N)";
            answer = ScannerUtil.nextLine(scanner, message);
        }

        if (answer.equalsIgnoreCase("Y")) {
            studentController.delete(id);
            showStudentList();
        } else {
            showStudentList();
        }
    }

    public void insertStudent() {
        StudentDTO student = new StudentDTO();

        String message = "새로운 학생의 이름을 입력하세요";
        student.setName(ScannerUtil.nextLine(scanner, message));

        message = "학생의 국어 점수를 입력하세요";
        student.setKorean(ScannerUtil.nextInt(scanner, message, 0, 100));

        message = "학생의 영어 점수를 입력하세요";
        student.setEnglish(ScannerUtil.nextInt(scanner, message, 0, 100));

        message = "학생의 수학 점수를 입력하세요";
        student.setMath(ScannerUtil.nextInt(scanner, message, 0, 100));

        studentController.insert(student);
    }
}
