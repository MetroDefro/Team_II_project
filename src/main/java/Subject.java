public class Subject {
    private int subjectId;
    private String subjectName;
    SubjectType subjectType;

    public Subject(int subjectId, String subjectName, SubjectType subjectType) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }
}
