public class Score {
    private int scoreId;
    private int studentId;
    private int scoreTurn;
    private int score;
    private char scoreGrade;

    public Score(int scoreId, int studentId, int scoreTurn, int score, char scoreGrade) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.scoreTurn = scoreTurn;
        this.score = score;
        this.scoreGrade = scoreGrade;
    }
}
