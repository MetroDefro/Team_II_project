import java.util.*;

public class App {
    // 리스트 -> DataManager 클래스에 리스트 관리 / 리스트 생성 / 조회하는 기능 이동

    // 관리자 클래스
    private final static StudentManager studentManager = new StudentManager();
    private final static ScoreManager scoreManager = new ScoreManager();

    public static void main(String[] args) {
        // Student, Subject, Score 배열 생성 및 초기화 로직
        DataRegistry.setInitData();

        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage() + "\n프로그램을 종료합니다.");
        }
    }

    private static void displayMainView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            try {
                int input = UserInputReader.getOption(3);
                switch (input) {
                    case 1 -> studentManager.displayView(); // 수강생 관리
                    case 2 -> scoreManager.displayView(); // 점수 관리
                    case 3 -> flag = false; // 프로그램 종료
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                flag = false;
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    /* 수강생의 특정 과목 회차별 등급 조회 */
    private static void inquiryScoreGrade() {
        //입력받기
        Student studentIdInput = DataManager.searchStudent(getStudentId()); //수강생 ID
        Subject subjectInput = DataManager.searchSubject(getSubjectName()); //과목 이름
        int turnInput = getTurn(); //회차
        Score score = DataManager.searchScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), turnInput); //점수 조회

        System.out.println(subjectInput.getSubjectName() + "과목의 " + turnInput + "회차 등급을 조회합니다...");
        char grade = score.getScoreGrade();
        System.out.println("등급: " + grade);
    }
}
