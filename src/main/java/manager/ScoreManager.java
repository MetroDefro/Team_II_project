package manager;

import enumtype.*;
import model.*;
import util.*;

import java.util.Arrays;

public class ScoreManager extends Manager {
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

            switch (UserInputReader.getOption(6)) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateTurnScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquiryScoreGrade(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> averageOfScoreGrade(); // 수강생의 과목별 평균 등급 조회
                case 5 -> inquiryEssentialSubjectAvgGrade(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
            }
        }
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private void createScore() {
        Student studentIdInput = DataRegistry.searchStudent(UserInputReader.getStudentId());
        for(Subject subject : studentIdInput.getStudentSubjects()) {
            System.out.print(subject.getSubjectName() + " ") ;
        }
        Subject subjectInput = DataRegistry.searchSubject(UserInputReader.getSubjectName());
        int totalScore = 0;

        System.out.println("시험 점수 등록 ... ");
        // 회차 1 ~ 10
        for (int scoreTurn = 1; scoreTurn <= 10; scoreTurn++) {
            System.out.print(scoreTurn + " 회차 ");
            int inputScore = UserInputReader.getScore(); // 점수 입력
            int tempTotalScore = totalScore + inputScore;
            try {
                DataRegistry.addScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), scoreTurn
                        , inputScore, subjectInput.getSubjectType());
                totalScore = tempTotalScore;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage()); // 예외 메시지 출력
                System.out.println("다시 해당 회차의 점수를 입력하세요.");
                scoreTurn--; // 이전 회차값으로 돌리게 되면 다시 for문으로 돌아갈 경우 +1 값인 현재 회차
            }
        }
        // 총 점수
        DataRegistry.addScore(studentIdInput.getStudentId(),subjectInput.getSubjectId(),totalScore, subjectInput.getSubjectType());
    }

    // 수강생의 과목별 회차 점수 수정
    private void updateTurnScoreBySubject() {
        do {
            try {
                // 기능 구현 (수정할 특정 학생, 과목 및 회차, 점수 입력 받아 변환)
                Student studentIdInput = DataRegistry.searchStudent(UserInputReader.getStudentId());
                Subject subjectInput = DataRegistry.searchSubject(UserInputReader.getSubjectName());
                int turnInput = UserInputReader.getTurn();
                Score score = DataRegistry.searchScore(studentIdInput.getStudentId(), subjectInput.getSubjectId(), turnInput); // 점수 조회

                patchScore(UserInputReader.getScore(), score, subjectInput);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (UserInputReader.getOption(2,"계속 수정하시겠습니까?\n1. 네 2. 아니오") == 1);
    }

    private void patchScore(int newScore, Score score, Subject subject) {
        System.out.println("\n시험 점수를 수정합니다...");
        score.patchScore(newScore, subject.getSubjectType()); // 시험 점수 업데이트
        System.out.println("\n점수 수정 성공!");
    }

    /* 수강생의 특정 과목 회차별 등급 조회 */
    private void inquiryScoreGrade() {
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
    private void averageOfScoreGrade() {
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
        char aveGrade = Score.reteGrade((int) averageOfScore, subjectInput.getSubjectType());
        System.out.println("[" + subjectInput.getSubjectName() + "]과목의 평균 등급은 " + aveGrade);
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    private void inquiryEssentialSubjectAvgGrade() {
        StateType inputStudentStatus = UserInputReader.getStudentState();

        // 1-1. 상태에 따른 학생들을 먼저 조회한 뒤 해당 학생들의 과목에서 추려야 함
        for (Student studentInput : DataRegistry.searchStudent(inputStudentStatus)) {
            int totalScore = 0;
            // 1-2. 학생들의 과목에서 과목 타입으로 필수 과목 가져오기 (과목 여러개)
            for (Subject subject : studentInput.getStudentSubjects()) {
                if (DataRegistry.searchSubject(SubjectType.ESSENTIAL).contains(subject)) {
                    Score score = DataRegistry.searchTotalScore(studentInput.getStudentId(), subject.getSubjectId());
                    // 2. 각 과목을 통해 평균 조회
                    int avgScore = score.getTotalScore() / 10;
                    char avgGrade = Score.reteGrade(avgScore, SubjectType.ESSENTIAL);
                    System.out.println("이름 : " + studentInput.getStudentName());
                    System.out.println("과목 : " + subject.getSubjectName());
                    System.out.println("총점 : " + score.getTotalScore());
                    System.out.println("평균 등급 : " + avgGrade);
                    System.out.println("=============================================");
                }
            }
        }
    }
}
