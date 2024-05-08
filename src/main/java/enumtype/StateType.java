package enumtype;

public enum StateType {
    RED(1),
    YELLOW(2),
    GREEN(3);

    private final int id;

    StateType(int id) {
        this.id = id;
    }

    public static StateType getStateType(int id) {
        for (StateType type : StateType.values()) {
            if(type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("해당하는 상태가 없습니다."); // 일치하는 게 없으면 예외처리
    }
}
