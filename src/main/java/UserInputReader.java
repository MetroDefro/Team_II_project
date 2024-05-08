import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputReader {
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static int getOption(int optionCount) {
        while (true) {
            System.out.print("관리 항목을 선택하세요...");
            try {
                return Parser.parseOption(sc.next(), optionCount);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getOption(int optionCount, String guide) {
        while (true) {
            System.out.print("\n" + guide);
            try {
                return Parser.parseOption(sc.next(), optionCount);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getSubjectId() {
        System.out.print("\n관리할 과목의 번호를 입력하시오...");
        while (true) {
            try {
                return Parser.parseId(sc.next());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getStudentId() {
        while (true) {
            System.out.print("\n관리할 수강생의 번호를 입력하시오...");
            try {
                return Parser.parseId(sc.next());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getSubjectName() throws InputMismatchException {
        while (true) {
            System.out.print("\n과목 이름을 입력하시오...");
            try {
                return sc.next();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getTurn() {
        while (true) {
            System.out.print("\n회차를 입력하시오...");
            try {
                return Parser.parseTurn(sc.next());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getScore() {
        while (true) {
            System.out.print("\n점수를 입력하시오...");
            try {
                return Parser.parseScore(sc.next());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static StateType getStudentState() {
        while (true) {
            System.out.print("\n상태를 입력하시오...");
            try {
                return Parser.parseState(sc.next());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getString(String guide) {
        while (true) {
            System.out.print("\n" + guide);
            try {
                return sc.next();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
