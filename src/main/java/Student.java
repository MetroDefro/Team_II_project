import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private int  studentId ;
    private String  studentName ;
    private List<Subject> studentSubjects;


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



    public static void studentNew(List<Student> students) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("학생 ID를 입력해주세요 id: ");
            int studentId = Parser.parseId(scanner.next());

            // 아이디가 존제하는지 검사
            List<Student> st = students.stream()
                    .filter(student -> student.getStudentId() == studentId)
                    .toList();
            int id_check = 0;
            for (Student student : st) {
                id_check = student.getStudentId();
            }

            if (studentId == id_check){
                System.out.println("제대로된 값을 입력해 주세요");
                System.out.println();
                continue;
            }

            // 이름 생성
            System.out.print("학생이름을 입력해주세요 name: ");
            String studentName = scanner.next();
            System.out.println();

            // 과목 등록
            List<Subject> studentSubjects_list = new ArrayList<>();
            int count = 0;
            int count_sub = 0;
            System.out.print("등록할 과목을 입력해 주세요 ( 영문은 소문자로 )\n 최소 3개 이상의 필수 과목, 2개 이상의 선택 과목을");
            while (true) {

                if(count < 90){
                    System.out.print("필수 과목 : java , 객체지향 , spring , jpa , mysql  \n 입력해주세요 : ");

                }else if(count >= 90){
                    System.out.print("선택 과목 : 디자인_패턴 , spring_security , redis , mongodb \n 입력해주세요 :  ");
                }

                String SubjectName = scanner.next();
                if (count <= 99 && SubjectName.equals("java") || SubjectName.equals("객체지향") || SubjectName.equals("spring") || SubjectName.equals("jpa") || SubjectName.equals("mysql")) {
                    count++;

                    switch (SubjectName) {
                        case "java" -> studentSubjects_list.add(DataManager.getSubjects().get(0));
                        case "객체지향" -> studentSubjects_list.add(DataManager.getSubjects().get(1));
                        case "spring" -> studentSubjects_list.add(DataManager.getSubjects().get(2));
                        case "jpa" -> studentSubjects_list.add(DataManager.getSubjects().get(3));
                        case "mysql" -> studentSubjects_list.add(DataManager.getSubjects().get(4));
                        default -> System.out.println("오류");
                    }
                    System.out.println(count + "번째 과목 선택됨 = " + SubjectName);
                    System.out.println();

                    if (count >= 3 && count<= 100){
                        System.out.println("3개 이상입니다. \n 선택과목을 선택하려면 (선택) 을 입력해 주세요");
                    }

                } else if (count >= 100 || SubjectName.equals("선택")) {
                    System.out.println();
                    count+=100;

                    if (SubjectName.equals("디자인_패턴") || SubjectName.equals("spring_security") || SubjectName.equals("redis") || SubjectName.equals("mongodb")){
                        count_sub++;

                        switch (SubjectName) {
                            case "디자인_패턴" -> studentSubjects_list.add(DataManager.getSubjects().get(5));
                            case "spring_security" -> studentSubjects_list.add(DataManager.getSubjects().get(6));
                            case "redis" -> studentSubjects_list.add(DataManager.getSubjects().get(7));
                            case "mongodb" -> studentSubjects_list.add(DataManager.getSubjects().get(8));
                            default -> System.out.println("오류");
                        }

                        System.out.println(count_sub+ " 번째 선택 과목 선택됨 " + SubjectName);
                        System.out.println();

                        // 여기에 추가
                        if (count_sub >= 2) {
                            System.out.print("2개 이상입니다. 완료 하려면 (end) or (끝)을 입력해 주세요 : ");
                            System.out.println();
                        }
                    }
                } else{
                    System.out.println("제대로 된 과목을 입력해 주세요");
                }
                if (SubjectName.equals("end") || SubjectName.equals("끝")) {
                    break;
                }

            }
            students.add(new Student(studentId,studentName, studentSubjects_list));

            System.out.println();
            System.out.print(" 추가를 끝내시겠습니까?  끝내기 = (n) 추가 = (y) \n 입력해 주세요 : ");
            String next = scanner.next();
            if(next.equals("n")){
                break;
            }
        }
    }

}