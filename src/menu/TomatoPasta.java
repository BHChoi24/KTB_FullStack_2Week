package menu;

import option.Pasta;
import restaurant.Food;

public class TomatoPasta extends Pasta {
    public TomatoPasta(int noodleChoice, int noodleDonenessChoice) {
        super("토마토 파스타", 9500, 2000, noodleChoice, noodleDonenessChoice);
    }
    public TomatoPasta() { //자기자신 호출해서
        this(0, 0);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new TomatoPasta(option1, option2);
    }
}
