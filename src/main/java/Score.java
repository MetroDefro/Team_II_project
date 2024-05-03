import java.util.List;

public class Score {
    private List<Score> scores;
    private int scoreId;
    private int studentId;
    private int subjectId;
    private int scoreTurn;
    private int score;
    private char scoreGrade;

    public Score(int scoreId, int studentId, int subjectId, int scoreTurn, int score, char scoreGrade) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreTurn = scoreTurn;
        this.score = score;
        this.scoreGrade = scoreGrade;
    }

    public Score() {

    }

    public Score(List<Score> scores, int studentId, int subjectId, int scoreTurn, int score, char scoreGrade, SubjectType subjectType) {
        this.scores = scores;
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

    public void addScore(List<Score> scores, int studentId, int subjectId, int scoreTurn, int score, SubjectType subjectType) {
        this.scores = scores;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreTurn = scoreTurn;
        this.score = score;

        for (Score existingScore : scores) {
            if (existingScore.getStudentId() == studentId
                    && existingScore.getSubjectId() == subjectId
                    && existingScore.getScoreTurn() == scoreTurn) {
                System.out.println("이미 해당 회차에 점수가 등록되어 있습니다.");
                return;
            }
        }

        char scoreGrade = generateScore(score);

        Score newScore = new Score(scores, studentId, subjectId, scoreTurn, score, scoreGrade, subjectType);
        scores.add(newScore);
    }

    private char generateScore(int score) {
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


    public void patchScore(int score, SubjectType subjectType) {
        this.score = score;
        reteGrade(score, subjectType); // 등급 매기기
    }

    private void reteGrade(int score, SubjectType subjectType) {
        if (subjectType == SubjectType.ESSENTIAL) { // 과목 타입 별로 등급을 다르게 매긴다.
            scoreGrade = evaluateGrade(score, 95, 90, 80, 70, 60); // 필수 과목
        } else if (subjectType == SubjectType.SELECT) { // 선택 과목
            scoreGrade = evaluateGrade(score, 90, 80, 70, 60, 50);
        }
    }

    private char evaluateGrade(int score, int... thresholds) {
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