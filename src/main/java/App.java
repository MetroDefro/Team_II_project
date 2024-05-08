import manager.MainManager;
import util.DataRegistry;

public class App {
    // 리스트 -> DataManager 클래스에 리스트 관리 / 리스트 생성 / 조회하는 기능 이동

    public static void main(String[] args) {
        // model.Student, model.Subject, model.Score 배열 생성 및 초기화 로직
        DataRegistry.setInitData();
        MainManager mainManager = new MainManager();
        try {
            mainManager.displayView();
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage() + "\n프로그램을 종료합니다.");
        }
    }
}