package data;

import enumtype.*;
import model.*;

import java.util.*;

public class DataRegistry {
    // 데이터 저장소
    private static List<Student> students;
    private static List<Subject> subjects;
    private static List<Score> scores;

    // index 관리 필드
    private static int studentIndex;

    // getter
    public static List<Student> getStudents() {
        return students;
    }

    // 초기 데이터 생성
    public static void setInitData() {
        students = new ArrayList<>();
        subjects = List.of(
                new Subject(SubjectName.Java),
                new Subject(SubjectName.OOP),
                new Subject(SubjectName.Spring),
                new Subject(SubjectName.JPA),
                new Subject(SubjectName.MySQL),
                new Subject(SubjectName.DesignPattern),
                new Subject(SubjectName.SpringSecurity),
                new Subject(SubjectName.Redis),
                new Subject(SubjectName.MongoDB)
        );
        scores = new ArrayList<>();
    }

    // index 자동 증가
    public static int sequence() {
        return ++studentIndex;
    }

    public static int getStudentId() {
        return sequence();
    }

    // 추가 기능
    public static void addStudent(Student student) {
        students.add(student);
    }

    // 기존 addScore 메서드 수정 : 회차별 점수 추가
    public static void addScore(int studentId, int subjectId, int turn, int score, SubjectType subjectType) {
        // 중복 검사
        if(isScoreAlreadyExist(studentId,subjectId,turn)) {
            throw new RuntimeException("이미 해당 회차에 점수가 등록되어 있습니다.");
        }

        char scoreGrade = Score.reteGrade(score, subjectType);
        Score newScore = new Score(studentId, subjectId, turn, score, scoreGrade);
        scores.add(newScore);
    }

    public static void addScore(int studentId, int subjectId, int totalScore, SubjectType subjectType) {
        Score newTotalScore = new Score(studentId, subjectId, totalScore);
        scores.add(newTotalScore);
    }

    // 삭제 기능
    public static void removeStudent(int studentId) {
        students.remove(searchStudent(studentId));
    }

    public static void removeScore(int studentId, int subjectId, int turn) {
        scores.remove(searchScore(studentId, subjectId, turn));
    }

    // 수강생 Id로 원하는 수강생 찾기
    public static Student searchStudent(int studentId) throws InputMismatchException {
        Optional<Student> studentObj = students.stream()
                .filter(o -> o.getStudentId() == studentId)
                .findFirst();
        // 해당하는 데이터가 없을 경우 예외처리
        if(studentObj.isPresent()) {
            return studentObj.get();
        } else {
            throw new InputMismatchException("일치하는 학생이 없습니다.");
        }
    }

    // 수강생 상태로 원하는 수강생 리스트 찾기
    public static List<Student> searchStudentList(StateType stateType) throws InputMismatchException{
        List<Student> studentList = students.stream()
                .filter(o -> o.getStateType() == stateType).toList();
        // 해당하는 데이터가 없을 경우 예외처리
        if(!studentList.isEmpty()) {
            return studentList;
        } else {
            throw new InputMismatchException("일치하는 학생이 없습니다.");
        }
    }

    // 과목 Id로 원하는 과목 찾기
    public static Subject searchSubject(int subjectId) throws InputMismatchException{
        Optional<Subject> subjectObj = subjects.stream()
                .filter(o -> o.getSubjectId() == subjectId)
                .findFirst();
        // 해당하는 데이터가 없을 경우 예외처리
        if(subjectObj.isPresent()) {
            return subjectObj.get();
        } else {
            throw new InputMismatchException("일치하는 과목이 없습니다.");
        }
    }

    // 과목 이름으로 원하는 과목 찾기
    public static Subject searchSubject(SubjectName subjectName) throws InputMismatchException{
        Optional<Subject> subjectObj = subjects.stream()
                .filter(o -> o.getSubjectName().equals(subjectName))
                .findFirst();
        // 해당하는 데이터가 없을 경우 예외처리
        if(subjectObj.isPresent()) {
            return subjectObj.get();
        } else {
            throw new InputMismatchException("일치하는 과목이 없습니다.");
        }
    }
    
    // 수강생Id, 과목Id, 회차로 원하는 점수 찾기
    public static Score searchScore(int studentId, int subjectId, int turn) throws InputMismatchException {
        // scores 조회하여 수강생id, 과목id, 회차가 일치하는 객체 찾기
        Optional<Score> scoreObj = scores.stream().filter(o -> o.getStudentId() == studentId &&
                        o.getSubjectId() == subjectId && o.getScoreTurn() == turn)
                .findFirst();
        // 해당하는 데이터가 없을 겨우 예외처리
        if(scoreObj.isPresent()) {
            return scoreObj.get();
        } else {
            throw new InputMismatchException("일치하는 데이터가 없습니다.");
        }
    }

    // 수강생 상태를 입력받아 같은 상태의 수강생들을 찾기
    public static List<Student> searchStudent(StateType status) throws InputMismatchException {
        List<Student> statusMatchedStudents = new ArrayList<>();
        for(Student student : students) {
            if(student.getStateType() == status) {
                statusMatchedStudents.add(student);
            }
        }
        if(!statusMatchedStudents.isEmpty()) {
            return statusMatchedStudents;
        } else {
            throw new InputMismatchException("일치하는 학생이 없습니다.");
        }
    }

    // 필수 과목 찾기
    public static List<Subject> searchSubject(SubjectType subjectType) throws InputMismatchException {
        List<Subject> subjectMatchedType = new ArrayList<>();
        for(Subject subject : subjects) {
            if(subject.getSubjectType() == subjectType) {
                subjectMatchedType.add(subject);
            }
        }
        if(!subjectMatchedType.isEmpty()) {
            return subjectMatchedType;
        } else {
            throw new InputMismatchException("일치하는 과목이 없습니다.");
        }
    }

    // 수강생Id, 과목Id 만으로 totalScore찾기
    public static Score searchTotalScore(int studentId, int subjectId) throws InputMismatchException {
        Optional<Score> scoreObj = scores.stream()
                .filter(o -> o.getStudentId() == studentId && o.getSubjectId() == subjectId &&  o.getTotalScore() != 0)
                .findFirst();

        // 해당하는 데이터가 없을 경우 예외 처리
        if (scoreObj.isPresent()) {
            return scoreObj.get();
        } else {
            throw new InputMismatchException("일치하는 데이터가 없습니다.");
        }
    }

    // 중복 검사
    private static boolean isScoreAlreadyExist(int studentId, int subjectId, int turn) {
        for (Score existingScore : scores) {
            if (existingScore.getStudentId() == studentId
                    && existingScore.getSubjectId() == subjectId
                    && existingScore.getScoreTurn() == turn) {
                return true;
            }
        }
        return false;
    }

}
