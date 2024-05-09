package model;

import enumtype.*;

public class Subject {
    private final int subjectId;
    private final SubjectName subjectName;
    private final SubjectType subjectType;

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
