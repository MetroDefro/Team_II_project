import java.util.Arrays;
import java.util.InputMismatchException;

public class ScoreManager extends Manager{
    @Override
    public void displayView() {
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

            try {
                int input = UserInputReader.getOption(6);
                switch (input) {
                    case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                    case 2 -> updateTurnScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                    case 3 -> inquiryScoreGrade(); // 수강생의 특정 과목 회차별 등급 조회
                    case 4 -> averageOfScoreGrade(); // 수강생의 과목별 평균 등급 조회
                    case 5 -> System.out.println("아직 개발 중인 기능입니다..."); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                    case 6 -> flag = false; // 메인 화면 이동
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                flag = false;
            }
        }
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        Student studentIdInput = DataRegistry.searchStudent(UserInputReader.getStudentId());
        Subject subjectInput = DataRegistry.searchSubject(UserInputReader.getSubjectName());

        System.out.println("시험 점수 등록 ... ");
        // 회차 1 ~ 10
        for (int scoreTurn = 1; scoreTurn <= 10; scoreTurn++) {
            try {
                DataRegistry.addScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), scoreTurn
                        , UserInputReader.getScore(), subjectInput.getSubjectType());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage()); // 예외 메시지 출력
                break; // 반복문 종료
            }
        }
    }
    // 수강생의 과목별 회차 점수 수정
    private static void updateTurnScoreBySubject() {
        // 기능 구현 (수정할 특정 학생, 과목 및 회차, 점수 입력 받아 변환)
        Student studentIdInput = DataRegistry.searchStudent(UserInputReader.getStudentId());
        Subject subjectInput = DataRegistry.searchSubject(UserInputReader.getSubjectName());
        int turnInput = UserInputReader.getTurn();
        int scoreInput = UserInputReader.getScore();
        // 정보들을 바탕으로 점수 리스트를 조회해 점수를 받아온다.
        Score score = DataRegistry.searchScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), turnInput);
        System.out.println("시험 점수를 수정합니다...");
        // 해당하는 점수 객체의 점수를 변경한다.
        score.patchScore(scoreInput, subjectInput.subjectType); // 시험 점수 업데이트
        System.out.println("\n점수 수정 성공!");
    }

    /* 수강생의 특정 과목 회차별 등급 조회 */
    private static void inquiryScoreGrade() {
        //입력받기
        Student studentIdInput = DataRegistry.searchStudent(UserInputReader.getStudentId()); //수강생 ID
        Subject subjectInput = DataRegistry.searchSubject(UserInputReader.getSubjectName()); //과목 이름
        int turnInput = UserInputReader.getTurn(); //회차
        Score score = DataRegistry.searchScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), turnInput); //점수 조회

        System.out.println("[" + subjectInput.getSubjectName() + "]과목의 " + turnInput + "회차 등급을 조회합니다...");
        char grade = score.getScoreGrade();
        System.out.println("등급: " + grade);
    }

    /* 수강생의 과목별 평균 등급 조회 */
    private static void averageOfScoreGrade() {
        //입력받기
        Student studentIdInput = DataRegistry.searchStudent(UserInputReader.getStudentId()); //수강생 ID
        Subject subjectInput = DataRegistry.searchSubject(UserInputReader.getSubjectName()); //과목 이름
        //10회차 모든 점수를 배열에 저장
        int[] scores = new int[10];
        for (int scoreTurn = 1; scoreTurn <= 10; scoreTurn++) {
            Score score = DataRegistry.searchScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), scoreTurn); //점수 조회
            scores[scoreTurn - 1] = score.getScore();
        }
        //모든 점수의 평균
        double averageOfScore = Arrays.stream(scores).average().orElse(0);
        //평균 점수의 등급 구하기
        char aveGrade = Score.reteGrade((int)averageOfScore, subjectInput.getSubjectType());
        System.out.println("[" + subjectInput.getSubjectName() + "]과목의 평균 등급은 " + aveGrade);
    }
}
