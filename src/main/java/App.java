import manager.MainManager;
import data.DataRegistry;

public class App {
    public static void main(String[] args) {
        DataRegistry.setInitData(); // model.Student, model.Subject, model.Score 배열 생성 및 초기화 로직
        MainManager mainManager = new MainManager();
        try {
            mainManager.displayView();
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage() + "\n프로그램을 종료합니다.");
        }
    }
}