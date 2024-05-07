import java.util.*;

public class Student {
    private int  studentId ;
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
            default -> this.stateType = StateType.RED;
        };
    }

    public String setStudentName(String studentName) {
        this.studentName = studentName;
        return studentName;
    }

    public static void studentNew() {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> studentSubjects_list_test = new HashSet<>();
        StateType stateType = null;
        System.out.println();


        while (true){
            int studentId = DataRegistry.getStudentId();
            // 이름 생성
            System.out.print("학생이름을 입력해주세요 name: ");
            String studentName = scanner.next();
            System.out.println();

            // 과목 등록
            List<Subject> studentSubjects_list = new ArrayList<>();
            int count = 0;
            int count_sub = 0;
            System.out.println("등록할 과목을 입력해 주세요 \n 최소 3개 이상의 필수 과목, 2개 이상의 선택 과목");
            int stats_s = 0;
            while (true) {

                if (count < 90) {
                    System.out.print("필수 과목 : 1.Java , 2.객체지향 , 3.Spring , 4.JPA , 5.MySQL  \n 입력해주세요 : ");

                } else if (count_sub < 900  && count >= 100 ) {
                    System.out.print("선택 과목 : 1.디자인 패턴 , 2.Spring Security , 3.Redis , 4.MongoDB \n 입력해주세요 :  ");
                }

                String subjectName = scanner.next();
                String test_int = "^[0-9]*$";
                int subjectId = 0;
                if (subjectName.matches(test_int)) {
                    subjectId = Parser.parseId(subjectName);
                }

                if (count <= 99 && subjectId <= 5) {
                    if (studentSubjects_list_test.contains(subjectId)) {
                        System.out.println(" 이미 등록하신 과목입니다.");
                    } else {
                        studentSubjects_list_test.add(subjectId);
                        count++;
                        switch (subjectId) {
                            case 1 -> studentSubjects_list.add(DataRegistry.getSubjects().get(0));
                            case 2 -> studentSubjects_list.add(DataRegistry.getSubjects().get(1));
                            case 3 -> studentSubjects_list.add(DataRegistry.getSubjects().get(2));
                            case 4 -> studentSubjects_list.add(DataRegistry.getSubjects().get(3));
                            case 5 -> studentSubjects_list.add(DataRegistry.getSubjects().get(4));
                            default -> subjectId = 0;
                        }

                        System.out.println(count + "번째 과목 선택됨 = " + DataRegistry.getSubjects().get(subjectId -1).getSubjectName());
                        System.out.println();

                        if (count >= 3 && count <= 100) {
                            System.out.println("3개 이상입니다. \n 선택과목을 선택하려면 '99' 을 입력해 주세요");
                        }
                    }

                }
                else if (count_sub < 900  && count >= 100 || subjectId == 99) {
                    System.out.println();
                    count += 100;

                    if (subjectId < 5) {

                        if (studentSubjects_list_test.contains(subjectId + 10)) {
                            System.out.println(" 이미 등록하신 과목입니다.");
                        } else {
                            count_sub++;
                            studentSubjects_list_test.add(subjectId + 10);
                            switch (subjectId) {
                                case 1 -> studentSubjects_list.add(DataRegistry.getSubjects().get(5));
                                case 2 -> studentSubjects_list.add(DataRegistry.getSubjects().get(6));
                                case 3 -> studentSubjects_list.add(DataRegistry.getSubjects().get(7));
                                case 4 -> studentSubjects_list.add(DataRegistry.getSubjects().get(8));
                                default -> subjectId = 0;
                            }


                            if(subjectName.matches(test_int)){
                                System.out.println(count_sub + " 번째 선택 과목 선택됨 " + DataRegistry.getSubjects().get(subjectId + 4).getSubjectName());
                                System.out.println();
                            }

                        }
                        // 여기에 추가
                        if (count_sub >= 2 && subjectName.matches(test_int)) {
                            System.out.print("2개 이상입니다. 상태를 등록 하려면 '888' 을 입력해 주세요 : ");
                            System.out.println();
                        }
                    }else if (count_sub < 900  && count >= 1000 || subjectId == 888) {
                        count_sub += 999;
                        System.out.print("상태입력 1.red 2.yellow 3.green  : ");

                        stats_s = scanner.nextInt();
                        switch (stats_s) {
                            case 1 -> stateType = StateType.RED;
                            case 2 -> stateType = StateType.YELLOW;
                            case 3 -> stateType = StateType.GREEN;
                            default -> System.out.println("제대로 입력해주세요");
                        }
                        break;
                    }
                } else {
                    System.out.println("제대로 된 과목을 입력해 주세요");
                }
            }
            DataRegistry.addStudent( new Student(studentId ,studentName, studentSubjects_list,stateType));
            studentSubjects_list_test.clear();

            System.out.println();
            System.out.print(" 추가를 끝내시겠습니까?  끝내기 = (n) 추가 = (y) \n 입력해 주세요 : ");
            String next = scanner.next();
            if (next.equals("n")) {
                break;
            }
        }
    }

}