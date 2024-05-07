import java.util.*;

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
        int choice;
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
                    System.out.println("기존 이름 : " + student.getStateType());
                    System.out.println("이름 수정을 원하면 1, 상태 수정을 원하면 2, 아니면 아무 입력을 하세요.");
                    int choice1 = sc.nextInt();
                    if (choice1 == 1) {
                        System.out.print("수정할 이름 :");
                        String newName = sc.next();
                        student.setStudentName(newName);
                        studentFound = true;
                        break;
                    }else if (choice1 == 2) {
                        System.out.print("상태 수정 1, RED 2, YELLOW 3,GREEN : ");
                        String changeStateType = sc.next();
                        String test_int = "^[0-9]*$";
                        int changeStateType_int;
                        if (changeStateType.matches(test_int)) {
                            changeStateType_int = Parser.parseId(changeStateType);
                            if (changeStateType_int <= 3) {
                                student.setStateType(changeStateType_int);
                            }else{
                                System.out.println("1부터 3까지중 선택해 주세요");
                            }
                        }
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

    // 상태별 수강생 목록 조회
    public static void searchStudentByState() {
        List<Student> studentList = DataRegistry.searchStudentList(UserInputReader.getStudentState());
        for (Student student : studentList) {
            System.out.println("\n학생 ID: " + student.getStudentId());
            System.out.println("학생 이름: " + student.getStudentName());
            System.out.println();
        }
    }

    // 수강생 생성
    public static void studentNew() {
        while (true){
            Scanner scanner = new Scanner(System.in);

            // ID값 등록
            int studentId = DataRegistry.getStudentId();

            // 이름 등록
            System.out.print("\n학생이름을 입력해주세요 name: ");
            String studentName = scanner.next();

            // 과목 등록
            Set<Integer> studentSubjects_list_test = new HashSet<>();
            List<Subject> studentSubjects_list = new ArrayList<>();
            boolean SubjectsMain = false;
            boolean SubjectsSub  = false;
            boolean studentEnd = false;
            String test_int = "^[0-9]*$";

            System.out.println("\n 등록할 과목을 입력해 주세요 \n 최소 3개 이상의 필수 과목, 2개 이상의 선택 과목");
            while (true) {
                if (!SubjectsMain) System.out.print("필수 과목 : 1.Java , 2.객체지향 , 3.Spring , 4.JPA , 5.MySQL  \n 입력해주세요 : ");
                else if (!SubjectsSub) System.out.print("선택 과목 : 1.디자인 패턴 , 2.Spring Security , 3.Redis , 4.MongoDB \n 입력해주세요 :  ");
                else System.out.print( "\n 상태입력 1, RED 2, YELLOW 3,GREEN : ");

                String subjectName = scanner.next();
                // 문자열을 감지해 => 정수값

                int subjectId = 0;
                if (subjectName.matches(test_int)) {
                    subjectId = Parser.parseId(subjectName);
                }

                if (!SubjectsMain) {
                    // 메인
                    SubjectsMain = studentNewSubjectsMain( studentSubjects_list_test, subjectId, studentSubjects_list );
                }else if (!SubjectsSub) {
                    //서브
                    SubjectsSub = studentNewSubjectsSub( studentSubjects_list_test, subjectId, studentSubjects_list );
                }else if(SubjectsSub && subjectId <= 3) {
                    // 상태
                    StateType stateType = studentNewStateType(subjectId);
                    // 생성 완료
                    DataRegistry.addStudent( new Student(studentId ,studentName, studentSubjects_list,stateType));
                    break;
                }else {
                    System.out.println("지정 범위내의 값을 입력해 주세요");
                }
            }

            System.out.print("\n 추가를 끝내시겠습니까?  끝내기 = (n) 추가 = (y) \n 입력해 주세요 : ");
            String next = scanner.next();
            if (next.equals("n")) {
                break;
            }
        }
    }

    // 매인 수업
    private static boolean studentNewSubjectsMain(Set<Integer> studentSubjects_list_test, int subjectId, List<Subject> studentSubjects_list) {
        boolean SubjectsMain = false;
        if (studentSubjects_list_test.contains( subjectId )) {
            System.out.println(" 이미 등록하신 과목입니다.");
        } else if (subjectId == 99) {
            SubjectsMain = true;
            studentSubjects_list_test.clear();
        }else {
            studentSubjects_list_test.add( subjectId );
            switch (subjectId) {
                case 1 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 0));
                case 2 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 1));
                case 3 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 2));
                case 4 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 3));
                case 5 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 4));
                default -> System.out.println("지정 범위내의 값을 입력해 주세요");
            }
            System.out.println( studentSubjects_list_test.size() + "번째 과목 선택됨 = " + DataRegistry.getSubjects().get( subjectId -1).getSubjectName());
            System.out.println();
            if (studentSubjects_list_test.size() >= 3) {
                System.out.println("3개 이상입니다. \n 선택과목을 선택하려면 '99' 을 입력해 주세요");
                System.out.println();
            }
        }
        return SubjectsMain;
    }
    // 서브 수업
    private static boolean studentNewSubjectsSub(Set<Integer> studentSubjects_list_test, int subjectId, List<Subject> studentSubjects_list) {

        boolean SubjectsSub = false;
        if (studentSubjects_list_test.contains( subjectId + 10 )) {
            System.out.println( " 이미 등록하신 과목입니다." );
        } else if (subjectId == 99 ) {
            SubjectsSub = true;
            studentSubjects_list_test.clear();
        } else {
            studentSubjects_list_test.add( subjectId + 10 );
            switch (subjectId) {
                case 1 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 5 ) );
                case 2 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 6 ) );
                case 3 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 7 ) );
                case 4 -> studentSubjects_list.add( DataRegistry.getSubjects().get( 8 ) );
                default -> System.out.println("지정 범위내의 값을 입력해 주세요");
            }
            System.out.println( studentSubjects_list_test.size() + " 번째 선택 과목 선택됨 " + DataRegistry.getSubjects().get( subjectId + 4 ).getSubjectName()+"\n");
        }
        if (studentSubjects_list_test.size() >= 2) {
            System.out.print( "2개 이상입니다. 상태를 등록 하려면 '99' 을 입력해 주세요 : " );
            System.out.println();
        }
        return SubjectsSub;
    }

    private static StateType studentNewStateType( int stats_set) {
        StateType stateType = null;
        switch (stats_set) {
            case 1 -> stateType = StateType.RED;
            case 2 -> stateType = StateType.YELLOW;
            case 3 -> stateType = StateType.GREEN;
            default -> System.out.println("지정 범위내의 값을 입력해 주세요");
        }
        return stateType;
    }
}

