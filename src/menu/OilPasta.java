package menu;

import option.Pasta;
import restaurant.Food;

public class OilPasta extends Pasta {
    public OilPasta() {
        super("알리오 올리오", 7000);
    }
    public OilPasta(int noodleChoice, int noodleDonenessChoice) {
        super("알리오 올리오", 7000, noodleChoice, noodleDonenessChoice);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new OilPasta(option1, option2);
    }
}
