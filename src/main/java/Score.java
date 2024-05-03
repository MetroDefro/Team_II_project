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

    public Score(List<Score> scores, int studentId, int subjectId, int scoreTurn, int score) {
        this.scores = scores;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.scoreTurn = scoreTurn;
        this.score = score;
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

    public void addScore(List<Score> scores, int studentId, int subjectId, int scoreTurn, int score) {
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
        // scoreGrade 수정 필요
        Score newScore = new Score(scores, studentId, subjectId, scoreTurn, score);
        scores.add(newScore);
        System.out.println(scores);
    }

    public void patchScore(int score, SubjectType subjectType) {
        this.score = score;
        reteGrade(score, subjectType);
    }

    private void reteGrade(int score, SubjectType subjectType) {
        if(subjectType == SubjectType.ESSENTIAL) {
            if(score >= 95)
                scoreGrade = 'A';
            else if (score >= 90)
                scoreGrade = 'B';
            else if (score >= 80)
                scoreGrade = 'C';
            else if (score >= 70)
                scoreGrade = 'D';
            else if (score >= 60)
                scoreGrade = 'F';
            else
                scoreGrade = 'N';
        } else if (subjectType == SubjectType.SELECT) {
            if(score >= 90)
                scoreGrade = 'A';
            else if (score >= 80)
                scoreGrade = 'B';
            else if (score >= 70)
                scoreGrade = 'C';
            else if (score >= 60)
                scoreGrade = 'D';
            else if (score >= 50)
                scoreGrade = 'F';
            else
                scoreGrade = 'N';
        }

    }
}
