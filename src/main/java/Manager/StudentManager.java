package Manager;

import java.util.Scanner;

public class StudentManager extends Manager{
    @Override
    public void displayView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 조회");
            System.out.println("4. 수강생 정보 수정");
            System.out.println("5. 수강생 삭제");
            System.out.println("6. 상태별 수강생 목록 조회");
            System.out.println("7. 메인 화면 이동");

            try {
                int input = UserInputReader.getOption(7);
                switch (input) {
                    case 1 -> Student.studentNew(); // 수강생 등록
                    case 2 -> searchAllStudent(); // 수강생 목록 조회
                    case 3 -> searchStudentById(); // 수강생 정보 조회
                    case 4 -> changeStudent(); // 수강생 정보 수정
                    case 5 -> removeStudent(); // 수강생 삭제
                    case 6 -> System.out.println("아직 개발 중인 기능입니다..."); // 상태별 수강생 목록 조회
                    case 7 -> flag = false; // 메인 화면 이동
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                flag = false;
            }
        }
    }

    // 수강생 목록 조회
    public static void searchAllStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        for (Student student : DataRegistry.getStudents()) {
            System.out.println("\n학생 ID: " + student.getStudentId());
            System.out.println("학생 이름: " + student.getStudentName());
            System.out.println();
        }
        System.out.println("조회 종료를 원하시면 아무 입력을 하세요.");
        sc.nextLine();
    }

    // 수강생 정보 조회
    public static void searchStudentById() {
        System.out.println();
        int choice;
        do {
            System.out.print("조회할 학생 ID : ");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            boolean found = false;
            for (Student student : DataRegistry.getStudents()) {
                if (student.getStudentId() == id) {
                    System.out.println("학생 이름: " + student.getStudentName());
                    System.out.println("과목 목록:");
                    for (Subject sub : student.getStudentSubjects()) {
                        System.out.println(sub.getSubjectName());
                    }
                    found = true;
                }
            }
            if (!found) {
                System.out.println("해당 학생을 찾을 수 없습니다.");
            }
            System.out.println("추가 조회를 원하시면 1 , 아니면 아무 입력을 하세요.");
            choice = sc.nextInt();
        } while (choice == 1);
    }

    // 수강생 정보 수정
    public static void changeStudent(){
        int choice;
        do {
            System.out.print("수정할 학생 ID: ");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            boolean studentFound = false;
            for (Student student : DataRegistry.getStudents()) {
                if (student.getStudentId() == id) {
                    System.out.println("기존 이름 : " + student.getStudentName());
                    System.out.println("이름 수정을 원하면 1, 아니면 아무 입력을 하세요.");
                    int choice1 = sc.nextInt();
                    if (choice1 == 1) {
                        System.out.print("수정할 이름 :");
                        String newName = sc.next();
                        student.setStudentName(newName);
                        studentFound = true;
                        break;
                    }else break;
                }
            }
            if (studentFound) {
                System.out.println("이름이 수정되었습니다.");
            } else {
                System.out.println("해당 ID를 가진 학생이 없거나, 수정하지 않았습니다.");
            }

            System.out.println("추가 수정를 원하시면 1 , 아니면 아무 입력을 하세요.");
            choice = sc.nextInt();
        }while (choice == 1);
    }


    // 수강생 삭제
    public static void removeStudent() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print("삭제할 학생 ID: ");
            int id = sc.nextInt();
            boolean studentFound = false;
            for (Student student : DataRegistry.getStudents()) {
                if (student.getStudentId() == id) {
                    DataRegistry.getStudents().remove(student); // 해당 학생을 삭제
                    studentFound = true;
                    break;
                }
            }

            if (studentFound) {
                System.out.println("학생이 삭제되었습니다.");
            } else {
                System.out.println("해당 ID를 가진 학생이 없습니다.");
            }
            System.out.println("추가 삭제를 원하시면 1 , 아니면 아무 입력을 하세요.");
            choice = sc.nextInt();
        }while (choice == 1);
    }
}
