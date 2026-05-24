package menu;

import option.Steak;
import restaurant.Food;

public class PorkSteak extends Steak {
    public PorkSteak() {
        super("포크 스테이크", 18000);
    }
    public PorkSteak(int styleChoice, int donenessChoice) {
        // 이름과 고정 가격은 기존 포크 스테이크의 정체성을 유지합니다.
        super("포크 스테이크", 18000, styleChoice, donenessChoice);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new PorkSteak(option1, option2);
    }
}