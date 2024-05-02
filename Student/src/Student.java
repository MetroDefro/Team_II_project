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

    // 이진 배열 검색
    private static boolean binarySearch(String[] array, String key) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = key.compareTo(array[mid]);
            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                return true; // 찾은 경우
            }
        }
        return false; // 찾지 못한 경우
    }




    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        students.add(new Student(10,"10번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(20,"20번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(30,"30번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(40,"40번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(50,"50번 가나다", List.of(new String[]{"밥"})));
        students.add(new Student(60,"60번 가나다", List.of(new String[]{"밥"})));


        while (true){
            System.out.print("학생ID을 입력해주세요 id: ");
            int studentId = scanner.nextInt();

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


            List<String> studentSubjects_list = new ArrayList<>();
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
                    System.out.println(count + "번째 과목 선택됨 = " + SubjectName);
                    System.out.println();
                    studentSubjects_list.add(SubjectName);

                    System.out.print("과목을 입력해주세요 : ");

                    if (count >= 3 && count<= 100){
                        System.out.println("3개 이상입니다. \n 선택과목을 선택하려면 (선택) 을 입력해 주세요");
                    }

                } else if (count >= 100 || SubjectName.equals("선택")) {
                    count+=100;
                    System.out.println("선택 과목을 입력해주세요 : ");
                    if (SubjectName.equals("디자인_패턴") || SubjectName.equals("spring_security") || SubjectName.equals("redis") || SubjectName.equals("mongodb")){
                        count_sub++;
                        System.out.println(count_sub+ " 번째 선택 과목 선택됨 " + SubjectName);
                        studentSubjects_list.add(SubjectName);
                        System.out.println();
                        if (count_sub >= 2) {
                            System.out.print("2개 이상입니다. 완료 하려면 (end) or (끝)을 입력해 주세요 : ");
                            System.out.println();
                        }
                    }else if (count >= 100){
                        System.out.println("제대로 입력해 주세요");
                    }
                } if (SubjectName.equals("end") || SubjectName.equals("끝")) {
                    break;
                } else{
                    System.out.println("제대로 된 과목을 입력해 주세요");
                }

            }
            students.add(new Student(studentId,studentName, studentSubjects_list));

            System.out.print(" 추가를 끝내시겠습니까?  y = (n) 추가 = (y) \n 입력해 주세요 : ");
            String next = scanner.next();
            if(next.equals("n")){
                break;
            }
        }






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