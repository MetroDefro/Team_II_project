public class MainManager extends Manager{

    private final StudentManager studentManager;
    private final ScoreManager scoreManager;

    public MainManager(){
        studentManager = new StudentManager();
        scoreManager = new ScoreManager();
    }

    @Override
    public void displayView() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            try {
                int input = UserInputReader.getOption(3);
                switch (input) {
                    case 1 -> studentManager.displayView(); // 수강생 관리
                    case 2 -> scoreManager.displayView(); // 점수 관리
                    case 3 -> flag = false; // 프로그램 종료
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                flag = false;
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
