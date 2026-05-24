package menu;

import option.Pasta;
import restaurant.Food;

public class CreamPasta extends Pasta {
    public CreamPasta() {
        super("크림 까르보나라", 11000);
    }
    public CreamPasta(int noodleChoice, int noodleDonenessChoice) {
        super("크림 까르보나라", 11000, noodleChoice, noodleDonenessChoice);
    }
    @Override
    public Food createOrder(int option1, int option2) {
        return new CreamPasta(option1, option2);
    }
}
