import java.util.List;

public class Student {
    private int studentId;
    private String studentName;
    List<Subject> studentSubjects;

    public Student(int studentId, String studentName, List<Subject> studentSubjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSubjects = studentSubjects;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getStudentSubjects() {
        return studentSubjects;
    }
}
