package model;

import enumtype.*;

import java.util.List;

public class Score {
    private List<Score> scores;
    private int scoreId;
    private int studentId;
    private int subjectId;
    private int scoreTurn;
    private int score;
    private char scoreGrade;
    private int totalScore;

    public Score(int scoreId, int studentId, int subjectId, int scoreTurn, int score, char scoreGrade) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreTurn = scoreTurn;
        this.score = score;
        this.scoreGrade = scoreGrade;
    }

    public Score(int studentId, int subjectId, int totalScore, SubjectType subjectType) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.totalScore = totalScore;
    }

    public Score(int studentId, int subjectId, int scoreTurn, int score, char scoreGrade, SubjectType subjectType) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreTurn = scoreTurn;
        this.score = score;
        this.scoreGrade = scoreGrade;
    }

    public int getScoreId() {
        return scoreId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getScoreTurn() {
        return scoreTurn;
    }

    public int getScore() {
        return score;
    }

    public char getScoreGrade() {
        return scoreGrade;
    }

    public int getTotalScore() {
        return totalScore;
    }


    public void patchScore(int score, SubjectType subjectType) {
        this.score = score;
        this.scoreGrade = reteGrade(score, subjectType); // 등급 매기기
    }

    public static char reteGrade(int score, SubjectType subjectType) {
        return switch (subjectType) { // 과목 타입 별로 등급을 다르게 매긴다.
            case ESSENTIAL -> evaluateGrade(score, 95, 90, 80, 70, 60); // 필수 과목
            case SELECT -> evaluateGrade(score, 90, 80, 70, 60, 50); // 선택 과목
        };
    }

    private static char evaluateGrade(int score, int... thresholds) {
        char[] grades = {'A', 'B', 'C', 'D', 'F'}; // 등급들
        char grade = 'N'; // 기본 등급 N부터 시작
        for (int i = 0; i < thresholds.length; i++) {
            if (score >= thresholds[i]) { // 점수가 임계값보다 높을 경우
                grade = grades[i]; // 등급을 매긴다.
                break; // 등급이 매겨졌으니 빠져나온다.
            }
        }
        return grade; // 등급 반환
    }
}
