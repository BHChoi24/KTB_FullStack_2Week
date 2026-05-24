package restaurant;

public abstract class Food {
    private final String foodName;
    private final int price;
    private final int cookTime;

    public Food(String foodName, int price, int cookTime){
        this.foodName = foodName;
        this.price = price;
        this.cookTime = cookTime; //스레드 조리시간으로 사용할 예정
    }

    //주문 목록으로 사용함(기존의 instanceof 조건문 해결)
    public abstract Food createOrder(int option1, int option2);

    public void cook() {
        System.out.println("요리 접수");
    }


    public String getFoodName(){
        return foodName;
    }
    public int getPrice(){
        return price;
    }
    public int getCookTime() {
        return cookTime;
    }
}