import java.util.*;

public class DataRegistry {
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
    private static int stateIndex;
    private static final String RED = "RED";


    // getter
    public static List<Student> getStudents() {
        return students;
    }

    public static List<Subject> getSubjects() {
        return subjects;
    }

    public static List<Score> getScores() {
        return scores;
    }

    // 임시
    public static int getStudentIndex() { return studentIndex;}


    // 초기 데이터 생성
    public static void setInitData() {
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
    public static int sequence(String type) {
        return switch (type) {
            case INDEX_TYPE_STUDENT -> ++studentIndex;
            case INDEX_TYPE_SUBJECT -> ++subjectIndex;
            default -> ++scoreIndex;
        };
    }

    public static int getStudentId() {
        return sequence(INDEX_TYPE_STUDENT);
    }

    public static int getScoreId() {
        return sequence(INDEX_TYPE_SCORE);
    }

    // 추가 기능
    public static void addStudent(Student student) {
        students.add(student);
    }

    public static void addScore(Score score) {
        scores.add(score);
    }

    // 삭제 기능
    public static void removeStudent(int studentId) {
        students.remove(searchStudent(studentId));
    }

    public static void removeStudent(String studentName) {
        students.remove(searchStudent(studentName));
    }

    public static void removeScore(int scoreId) {
        scores.remove(searchScore(scoreId));
    }

    public static void removeScore(int studentId, int subjectId, int turn) {
        scores.remove(searchScore(studentId, subjectId, turn));
    }

    // 수강생 Id로 원하는 수강생 찾기
    public static Student searchStudent(int studentId) throws InputMismatchException {
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

    // 수강생 이름으로 원하는 수강생 찾기
    public static Student searchStudent(String studentName) throws InputMismatchException{
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

    // 과목 Id로 원하는 과목 찾기
    public static Subject searchSubject(int subjectId) throws InputMismatchException{
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

    // 과목 이름으로 원하는 과목 찾기
    public static Subject searchSubject(String subjectName) throws InputMismatchException{
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
    
    // 수강생Id, 과목Id, 회차로 원하는 점수 찾기
    public static Score searchScore(int studentId, int subjectId, int turn) throws InputMismatchException {
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

    // 점수Id로 원하는 점수 찾기
    public static Score searchScore(int scoreId) throws InputMismatchException {
        // scores 조회하여 수강생id, 과목id, 회차가 일치하는 객체 찾기
        Optional<Score> scoreObj = scores.stream().filter(o -> o.getScoreId() == scoreId)
                .findFirst();
        // 해당하는 데이터가 없을 겨우 예외처리
        if(scoreObj.isPresent()) {
            return scoreObj.get();
        } else {
            throw new InputMismatchException("일치하는 데이터가 없습니다.\n선택 화면 이동...");
        }
    }

    // 기존 addScore 메서드 수정 : 회차별 점수 추가
    public static void addScore(int studentId, int subjectId, int turn, int score, SubjectType subjectType) {
        // 중복 검사
        if(isScoreAlreadyExist(studentId,subjectId,turn)) {
            throw new RuntimeException("이미 해당 회차에 점수가 등록되어 있습니다.");
        }

        char scoreGrade = generateGrade(score);
        Score newScore = new Score(studentId, subjectId, turn, score, scoreGrade, subjectType);
        scores.add(newScore);
    }
    // 중복 검사
    private static boolean isScoreAlreadyExist(int studentId, int subjectId, int turn) {
        for (Score existingScore : scores) {
            if (existingScore.getStudentId() == studentId
                    && existingScore.getSubjectId() == subjectId
                    && existingScore.getScoreTurn() == turn) {
                return true;
            }
        }
        return false;
    }
    // 등급 산출
    private static char generateGrade(int score) {
        if (score >= 95) {
            return 'A';
        } else if (score >= 90) {
            return 'B';
        } else if (score >= 80) {
            return 'C';
        } else if (score >= 70) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
