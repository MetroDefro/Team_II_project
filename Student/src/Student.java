import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private int  studentId ;
    private String  studentName ;
    private List<String> studentSubjects;

    public Student(int studentId, String studentName, List<String> studentSubjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSubjects = studentSubjects;
    }

    public int getStudentId() {
        return studentId;
    }

    public List<String> getStudentSubjects() {
        return studentSubjects;
    }

    public String getStudentName() {
        return studentName;
    }


    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("학생ID을 입력해주세요 id: ");
            int studentId = scanner.nextInt();
            System.out.print("학생이름을 입력해주세요 name: ");
            String studentName = scanner.next();
            System.out.println();
            students.add(new Student(studentId,studentName, List.of(new String[]{"밥"})));

            System.out.print(" 추가를 끝내시겠습니까?  n = (끝냄) 추가 = (아무거나) \n 입력해 주세요 : ");
            String next = scanner.next();
            if(next.equals("n")){
                break;
            }
        }

        students.add(new Student(10,"10번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(20,"20번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(30,"30번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(40,"40번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(50,"50번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(60,"60번 가나다", List.of(new String[]{"밥"})));



        System.out.println();
        System.out.print("학생 이름 찾기 ID : ");
        int studentId = scanner.nextInt();
        List<Student> st = students.stream()
                .filter(student -> student.getStudentId() == studentId)
                .toList();

        for (Student student : st) {
            System.out.println("학생 이름: " + student.getStudentId());
            System.out.println("학생 이름: " + student.getStudentName());
        }


    }
}