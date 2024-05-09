package util;

import enumtype.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputReader {
    // 스캐너
    private static final Scanner sc = new Scanner(System.in);

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

    public static int getStudentId() {
        while (true) {
            System.out.print("\n수강생 아이디를 입력하시오...");
            try {
                return Parser.parseId(sc.next());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static SubjectName getSubjectName() throws InputMismatchException {
        while (true) {
            System.out.print("\n과목 이름을 입력하시오...");
            System.out.print("\n1. Java, 2. 객체지향, 3. Spring, 4. JPA, 5. MySQL");
            System.out.println("\n6. 디자인 패턴, 7. Spring Security, 8. Redis, 9. MongoDB");
            try {
                return Parser.parseSubjectName(sc.next());
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
            System.out.println("\n1. RED, 2. YELLOW, 3. GREEN");
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
