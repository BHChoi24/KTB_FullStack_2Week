package menu;

import option.Steak;
import restaurant.Food;

public class BeefSteak extends Steak {
    public BeefSteak() {
        super("비프 스테이크", 35000);
    }
    public BeefSteak(int styleChoice, int donenessChoice) {
        // 이름과 고정 가격은 기존 비프 스테이크의 정체성을 유지합니다.
        super("비프 스테이크", 35000, styleChoice, donenessChoice);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new BeefSteak(option1, option2);
    }
}
