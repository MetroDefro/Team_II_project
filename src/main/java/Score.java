public class Score {
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
