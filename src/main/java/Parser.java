import java.util.InputMismatchException;

// 입력 값의 예외 처리 및 파싱을 담당하는 클래스
public class Parser {

    // String을 int로 변환. 올바른Id(0 이상 정수)가 들어오지 않을 시 예외처리
    public static int parseId(String input) throws InputMismatchException { // int 파싱
        if(input.matches("^[0-9]+$")) { // 정규식 검사
            return Integer.parseInt(input);
        } else {
            throw new InputMismatchException("올바른 번호를 입력하시오...(0 이상 정수)"); // exception 만들어 던짐
        }
    }

    // String을 int로 변환. 올바른 회차(1~10 정수)가 들어오지 않을 시 예외처리
    public static int parseTurn(String input) throws InputMismatchException { // int 파싱
        if(input.matches("^[0-9]+$")) { // 정규식 검사
            int turn = Integer.parseInt(input);
            if (turn >= 1 && turn <= 10) {
                return turn;
            }
        }
        // if문에서 걸러져 나왔을 경우
        throw new InputMismatchException("올바른 번호를 입력하시오...(1 ~ 10 정수)"); // exception 만들어 던짐
    }

    // String을 int로 변환. 올바른 점수(0~100 정수)가 들어오지 않을 시 예외처리
    public static int parseScore(String input) throws InputMismatchException { // int 파싱
        if(input.matches("^[0-9]+$")) { // 정규식 검사
            int score = Integer.parseInt(input);
            if (score >= 0 && score <= 100) {
                return score;
            }
        }
        // if문에서 걸러져 나왔을 경우
        throw new InputMismatchException("올바른 번호를 입력하시오...(0 ~ 100 정수)"); // exception 만들어 던짐
    }
}
