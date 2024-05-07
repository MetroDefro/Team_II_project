import java.util.*;

public class Student {
    private  int  studentId ;
    private String  studentName ;
    private List<Subject> studentSubjects;
    StateType stateType;

    public Student(int studentId, String studentName, List<Subject> studentSubjects , StateType stateType) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSubjects = studentSubjects;
        this.stateType = stateType;
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

    public StateType getStateType() {return stateType;}

    public StateType setStateType(int stateType) {
         return switch (stateType) {
            case 1 -> this.stateType = StateType.RED;
            case 2 -> this.stateType = StateType.YELLOW;
            case 3 -> this.stateType = StateType.GREEN;
            default -> getStateType();
        };
    }

    public String setStudentName(String studentName) {
        this.studentName = studentName;
        return studentName;
    }



}