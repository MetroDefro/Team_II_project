package model;

import enumtype.*;

import java.util.*;

public class Student {
    private final int  studentId ;
    private String  studentName ;
    private final List<Subject> studentSubjects;
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

    public void setStateType(StateType instateType) {
        this.stateType = instateType;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }



}