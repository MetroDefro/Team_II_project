import java.util.Scanner;

public class App {
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Student, Subject, Score 배열 생성 및 초기화 로직 필요
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage() + "\n프로그램을 종료합니다.");
        }
    }

    private static void displayMainView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 조회");
            System.out.println("4. 수강생 정보 수정");
            System.out.println("5. 수강생 삭제");
            System.out.println("6. 상태별 수강생 목록 조회");
            System.out.println("7. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생 등록
                case 2 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생 목록 조회
                case 3 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생 정보 조회
                case 4 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생 정보 수정
                case 5 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생 삭제
                case 6 -> System.out.println("아직 개발 중인 기능입니다..."); // 상태별 수강생 목록 조회
                case 7 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생의 과목별 회차 점수 수정
                case 3 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> System.out.println("아직 개발 중인 기능입니다..."); // 수강생의 과목별 평균 등급 조회
                case 5 -> System.out.println("아직 개발 중인 기능입니다..."); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
