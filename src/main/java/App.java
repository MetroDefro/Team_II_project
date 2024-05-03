import java.util.*;

public class App {
    // 데이터 저장소
    private static List<Student> students;
    private static List<Subject> subjects;
    private static List<Score> scores;

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Student, Subject, Score 배열 생성 및 초기화 로직
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage() + "\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        students = new ArrayList<>();
        subjects = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SubjectType.ESSENTIAL
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SubjectType.ESSENTIAL
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SubjectType.ESSENTIAL
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SubjectType.ESSENTIAL
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SubjectType.ESSENTIAL
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SubjectType.SELECT
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SubjectType.SELECT
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SubjectType.SELECT
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SubjectType.SELECT
                )
        );
        scores = new ArrayList<>();
    }

    // index 자동 증가
    private static int sequence(String type) {
        return switch (type) {
            case INDEX_TYPE_STUDENT -> ++studentIndex;
            case INDEX_TYPE_SUBJECT -> ++subjectIndex;
            default -> ++scoreIndex;
        };
    }

    private static void displayMainView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            try {
                switch (input) {
                    case 1 -> displayStudentView(); // 수강생 관리
                    case 2 -> displayScoreView(); // 점수 관리
                    case 3 -> flag = false; // 프로그램 종료
                    default -> {
                        System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
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
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            try {
                switch (input) {
                    case 1 -> Student.studentNew(sc,students); // 수강생 등록
                    case 2 -> searchAllStudent(); // 수강생 목록 조회
                    case 3 -> searchStudentById(); // 수강생 정보 조회
                    case 4 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생 정보 수정
                    case 5 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생 삭제
                    case 6 -> System.out.println("아직 개발 중인 기능입니다..."); // 상태별 수강생 목록 조회
                    case 7 -> flag = false; // 메인 화면 이동
                    default -> {
                        System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                        flag = false;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateTurnScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생의 과목별 평균 등급 조회
                case 5 -> System.out.println("아직 개발 중인 기능입니다..."); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static int getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return Parser.parseId(sc.next());
    }

    private static String getSubjectName() throws InputMismatchException{
        System.out.print("\n과목 이름을 입력하시오...");
        // 과목 이름으로부터 List 조회해 Optional 객체 받음.
        return sc.next();
    }

    private static int getTurn() {
        System.out.print("\n회차를 입력하시오...");
        return Parser.parseTurn(sc.next());
    }

    private static int getScore() {
        System.out.print("\n점수를 입력하시오...");
        return Parser.parseScore(sc.next());
    }

    private static Student searchStudent(int studentId) throws InputMismatchException{
        Optional<Student> studentObj = students.stream()
                .filter(o -> o.getStudentId() == studentId)
                .findFirst();
        // 해당하는 데이터가 없을 경우 예외처리
        if(studentObj.isPresent()) {
            return studentObj.get();
        } else {
            throw new InputMismatchException("일치하는 학생이 없습니다.\n선택 화면 이동...");
        }
    }

    private static Student searchStudent(String studentName) throws InputMismatchException{
        Optional<Student> studentObj = students.stream()
                .filter(o -> o.getStudentName().equals(studentName))
                .findFirst();
        // 해당하는 데이터가 없을 경우 예외처리
        if(studentObj.isPresent()) {
            return studentObj.get();
        } else {
            throw new InputMismatchException("일치하는 학생이 없습니다.\n선택 화면 이동...");
        }
    }

    private static Subject searchSubject(int subjectId) throws InputMismatchException{
        Optional<Subject> subjectObj = subjects.stream()
                .filter(o -> o.getSubjectId() == subjectId)
                .findFirst();
        // 해당하는 데이터가 없을 경우 예외처리
        if(subjectObj.isPresent()) {
            return subjectObj.get();
        } else {
            throw new InputMismatchException("일치하는 과목이 없습니다.\n선택 화면 이동...");
        }
    }

    private static Subject searchSubject(String subjectName) throws InputMismatchException{
        Optional<Subject> subjectObj = subjects.stream()
                .filter(o -> o.getSubjectName().equals(subjectName))
                .findFirst();
        // 해당하는 데이터가 없을 경우 예외처리
        if(subjectObj.isPresent()) {
            return subjectObj.get();
        } else {
            throw new InputMismatchException("일치하는 과목이 없습니다.\n선택 화면 이동...");
        }
    }

    private static Score searchScore(int studentId, int subjectId, int turn) throws InputMismatchException {
        // scores 조회하여 수강생id, 과목id, 회차가 일치하는 객체 찾기
        Optional<Score> scoreObj = scores.stream().filter(o -> o.getStudentId() == studentId &&
                        o.getSubjectId() == subjectId && o.getScoreTurn() == turn)
                .findFirst();
        // 해당하는 데이터가 없을 겨우 예외처리
        if(scoreObj.isPresent()) {
            return scoreObj.get();
        } else {
            throw new InputMismatchException("일치하는 데이터가 없습니다.\n선택 화면 이동...");
        }
    }

    // 수강생 목록 조회
    public static void searchAllStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println();
        for (Student student : students) {
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
            for (Student student : students) {
                if (student.getStudentId() == id) {
                    System.out.println("학생 이름: " + student.getStudentName());
                    System.out.println("과목 목록:");
                    for (Subject sub : student.getStudentSubjects()) {
                        System.out.println(sub);
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


    // 수강생의 과목별 회차 점수 수정
    private static void updateTurnScoreBySubject() {
        // 기능 구현 (수정할 특정 학생, 과목 및 회차, 점수 입력 받아 변환)
        Student studentIdInput = searchStudent(getStudentId());
        Subject subjectInput = searchSubject(getSubjectName());
        int turnInput = getTurn();
        int scoreInput = getScore();
        // 정보들을 바탕으로 점수 리스트를 조회해 점수를 받아온다.
        Score score = searchScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), turnInput);
        System.out.println("시험 점수를 수정합니다...");
        // 해당하는 점수 객체의 점수를 변경한다.
        score.patchScore(scoreInput, subjectInput.subjectType); // 시험 점수 업데이트
        System.out.println("\n점수 수정 성공!");
    }
}
