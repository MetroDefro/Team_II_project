package model;

import enumtype.*;

public class Subject {
    private int subjectId;
    private SubjectName subjectName;
    private SubjectType subjectType;

    public Subject(SubjectName subjectName) {
        this.subjectId = subjectName.getId();
        this.subjectName = subjectName;
        this.subjectType = subjectName.getSubjectType();
    }

    public int getSubjectId() {
        return subjectId;
    }

    public SubjectName getSubjectName() {
        return subjectName;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }
}
