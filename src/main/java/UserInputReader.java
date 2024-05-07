import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputReader {
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static int getOption(int optionCount) {
        System.out.print("관리 항목을 선택하세요...");
        return Parser.parseOption(sc.next(), optionCount);
    }

    public static int getSubjectId() {
        System.out.print("\n관리할 과목의 번호를 입력하시오...");
        return Parser.parseId(sc.next());
    }

    public static int getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return Parser.parseId(sc.next());
    }

    public static String getSubjectName() throws InputMismatchException {
        System.out.print("\n과목 이름을 입력하시오...");
        // 과목 이름으로부터 List 조회해 Optional 객체 받음.
        return sc.next();
    }

    public static int getTurn() {
        System.out.print("\n회차를 입력하시오...");
        return Parser.parseTurn(sc.next());
    }

    public static int getScore() {
        System.out.print("\n점수를 입력하시오...");
        return Parser.parseScore(sc.next());
    }

    public static StateType getStudentState() {
        System.out.print("\n상태를 입력하시오...");
        return Parser.parseState(sc.next());
    }
}
