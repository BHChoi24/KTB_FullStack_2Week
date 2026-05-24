package menu;

import option.Pasta;
import restaurant.Food;

public class CreamPasta extends Pasta {
    public CreamPasta(int noodleChoice, int noodleDonenessChoice) {
        super("크림 까르보나라", 11000, 2000, noodleChoice, noodleDonenessChoice);
    }
    public CreamPasta() {
        this(0, 0);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new CreamPasta(option1, option2);
    }
}
