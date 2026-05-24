package menu;

import option.Pasta;
import restaurant.Food;

public class OilPasta extends Pasta {
    public OilPasta(int noodleChoice, int noodleDonenessChoice) {
        super("알리오 올리오", 7000, 1000, noodleChoice, noodleDonenessChoice);
    }
    public OilPasta() {
        this(0, 0);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new OilPasta(option1, option2);
    }
}
