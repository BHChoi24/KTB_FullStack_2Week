package menu;

import option.Pasta;
import restaurant.Food;

public class TomatoPasta extends Pasta {
    public TomatoPasta() {
        super("토마토 파스타", 9500);
    }
    public TomatoPasta(int noodleChoice, int noodleDonenessChoice) {
        super("토마토 파스타", 9500, noodleChoice, noodleDonenessChoice);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new TomatoPasta(option1, option2);
    }
}
