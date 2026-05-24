package role;

import menu.*;
import restaurant.Food;

public class OrderService {

    //기존에 instanceof으로 하나씩하는 문제 핵결
    public Food createSelectedFood(Food baseFood, int option1, int option2) {
        if (baseFood == null) {
            return null;
        }
        return baseFood.createOrder(option1, option2);
    }
}