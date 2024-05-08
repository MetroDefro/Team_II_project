package manager;

import data.*;
import enumtype.*;
import model.*;
import util.*;

import java.util.*;

public class StudentManager extends Manager{
    private static final int SUBSUBJECTTUNING = 5;

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
                    case 1 -> studentNew(); // 수강생 등록
                    case 2 -> searchAllStudent(); // 수강생 목록 조회
                    case 3 -> searchStudentById(); // 수강생 정보 조회
                    case 4 -> changeStudent(); // 수강생 정보 수정
                    case 5 -> removeStudent(); // 수강생 삭제
                    case 6 -> searchStudentByState(); // 상태별 수강생 목록 조회
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
        String choice;
        do {
            System.out.print("조회할 학생 ID : ");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            boolean found = false;
            for (Student student : DataRegistry.getStudents()) {
                if (student.getStudentId() == id) {
                    System.out.println("학생 이름: " + student.getStudentName());
                    System.out.println("학생 상태: " + student.getStateType());
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
            choice = sc.next();
        } while (choice.equals("1"));
    }

    // 수강생 정보 수정
    public static void changeStudent(){
        String choice;
        do {
            System.out.print("수정할 학생 ID: ");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            boolean studentFoundName = false;
            boolean studentFoundStateType = false;
            for (Student student : DataRegistry.getStudents()) {
                if (student.getStudentId() == id) {
                    System.out.println("기존 이름 : " + student.getStudentName());
                    System.out.println("기존 상태 : " + student.getStateType());
                    System.out.println("이름 수정을 원하면 1, 상태 수정을 원하면 2, 아니면 아무 입력을 하세요.");
                    int choice1 = sc.nextInt();
                    if (choice1 == 1) {
                        System.out.print("수정할 이름 :");
                        String newName = sc.next();
                        student.setStudentName(newName);
                        studentFoundName = true;
                        break;
                    }else if (choice1 == 2) {
                        StateType changeStateType =  studentNewStateType();
                        student.setStateType(changeStateType);
                        studentFoundStateType = true;
                        break;
                    }else {
                        break;
                    }
                }
            }
            if (studentFoundName) {
                System.out.println("이름이 수정되었습니다.");
            } else if (studentFoundStateType) {
                System.out.println("상태가 수정되었습니다.");
            } else {
                System.out.println("해당 ID를 가진 학생이 없거나, 수정하지 않았습니다.");
            }

            System.out.println("추가 수정를 원하시면 1 , 아니면 아무 입력을 하세요.");
            choice = sc.next();
        }while (choice.equals("1"));
    }

    // 수강생 삭제
    public static void removeStudent() {
        Scanner sc = new Scanner(System.in);
        String choice;
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
            choice = sc.next();
        }while (choice.equals("1"));
    }

    // 상태별 수강생 목록 조회
    public static void searchStudentByState() {
        do {
            try {
                List<Student> studentList = DataRegistry.searchStudentList(UserInputReader.getStudentState());
                for (Student student : studentList) {
                    System.out.println("\n학생 ID: " + student.getStudentId());
                    System.out.println("학생 이름: " + student.getStudentName());
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (UserInputReader.getOption(2,"계속 조회하시겠습니까?\n1. 네 2. 아니오") == 1);
    }

    // 수강생 생성
    public static void studentNew() {
        do{
            // 이름 등록
            String studentName = UserInputReader.getString("\n학생이름을 입력해주세요 name: ");

            // 과목 등록
            List<Subject> studentMainSubjects = new ArrayList<>();
            List<Subject> studentSubSubjects = new ArrayList<>();
            boolean isMainSubjectDone = false;
            boolean isSubSubjectDone  = false;

            System.out.println("\n 등록할 과목을 입력해 주세요 \n 최소 3개 이상의 필수 과목, 2개 이상의 선택 과목");
            do {
                isMainSubjectDone = studentNewSubjectsMain(studentMainSubjects);
            } while (!isMainSubjectDone);

            do {
                isSubSubjectDone = studentNewSubjectsSub(studentSubSubjects);
            } while (!isSubSubjectDone);

            StateType stateType = studentNewStateType();

            List<Subject> studentSubjects = new ArrayList<>(); // 서브, 메인 과목 합치기
            studentSubjects.addAll(studentMainSubjects);
            studentSubjects.addAll(studentSubSubjects);

            // 학생 생성
            DataRegistry.addStudent(new Student(DataRegistry.getStudentId() ,studentName, studentSubjects, stateType));

        } while (!UserInputReader.getString("\n 추가를 끝내시겠습니까?  끝내기 = (n) \n 입력해 주세요 : ")
                .equals("n"));
    }


    // 매인 수업
    private static boolean studentNewSubjectsMain(List<Subject> studentSubjects) {
        int subjectId = 0;
        if(studentSubjects.size() >= 3) {
            subjectId = UserInputReader.getOption(6,
                                                  "필수 과목 : 1.Java , 2.객체지향 , 3.Spring , 4.JPA , 5.MySQL, 6.선택과목 선택으로 이동 \n 입력해주세요 : ");
        } else {
            subjectId = UserInputReader.getOption(5,
                                                  "필수 과목 : 1.Java , 2.객체지향 , 3.Spring , 4.JPA , 5.MySQL  \n 입력해주세요 : ");
        }

        if(subjectId == 6) {
            return true;
        }

        for(Subject subject : studentSubjects) { // 이미 등록되어 있는지 확인
            if (subject.getSubjectId() == subjectId) {
                System.out.println(" 이미 등록하신 과목입니다.");
                return false;
            }
        }

        Subject subject = DataRegistry.searchSubject(subjectId);
        studentSubjects.add(subject);
        System.out.println(studentSubjects.size() + "번째 필수 과목 선택됨 = " + subject.getSubjectName());

        return false;
    }

    // 서브 수업
    private static boolean studentNewSubjectsSub(List<Subject> studentSubjects) {
        int subjectId = 0;
        if(studentSubjects.size() >= 2) {
            subjectId = UserInputReader.getOption(5,
                                                  "선택 과목 : 1.디자인 패턴 , 2.Spring Security , 3.Redis , 4.MongoDB, 5.상태등록으로 이동 \n 입력해주세요 :  ");
        } else {
            subjectId = UserInputReader.getOption(4,
                                                  "선택 과목 : 1.디자인 패턴 , 2.Spring Security , 3.Redis , 4.MongoDB \n 입력해주세요 :  ");
        }

        if(subjectId == 5) {
            return true;
        }

        for(Subject subject : studentSubjects) { // 이미 등록되어 있는지 확인
            if (subject.getSubjectId() == subjectId  + SUBSUBJECTTUNING) {
                System.out.println(" 이미 등록하신 과목입니다.");
                return false;
            }
        }

        Subject subject = DataRegistry.searchSubject(subjectId + SUBSUBJECTTUNING);
        studentSubjects.add(subject);
        System.out.println(studentSubjects.size() + "번째 선택 과목 선택됨 = " + subject.getSubjectName());

        return false;
    }

    // 상태 삽입
    private static StateType studentNewStateType() {
        int subjectId;
        subjectId =UserInputReader.getOption(3,
                                             "\n 상태입력 1.RED, 2.YELLOW, 3.GREEN : ");
        StateType stateType = StateType.values()[subjectId - 1]; // 상태
        return stateType;
    }
}
