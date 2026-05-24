package role;

public enum OrderStep {
    CONTINUE(1), // 추가 주문 진행
    COOK_START(2), // 주문 종료 및 조리 시작
    INVALID(-1); // 잘못된 입력 처리용

    private final int number;

    OrderStep(int number) {
        this.number = number;
    }

    public static OrderStep fromNumber(int number) {
        for (OrderStep step : values()) {
            if (step.number == number) {
                return step;
            }
        }
        return INVALID;
    }
}