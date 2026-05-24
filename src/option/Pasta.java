package option;

import restaurant.Food;

public abstract class Pasta extends Food {

    public static final String[] NOODLE_TYPES = {"스파게티 면", "링귀니 면", "부카티니 면"};
    public static final String[] NOODLE_DONENESS = {"단단하게 (알단테)", "보통 (단테)", "부드럽게 (벤코토)"};

    private final String noodleType;
    private final String noodleDoneness;

    //메뉴판에 보이게
    public Pasta(String foodName, int price) {
        super(foodName, price);
        this.noodleType = "선택 안함";
        this.noodleDoneness = "선택 안함";
    }
    //주문용
    public Pasta(String foodName, int price, int noodleTypeChoice, int noodleDonenessChoice) {
        super(foodName, price);

        if (noodleTypeChoice >= 1 && noodleTypeChoice <= NOODLE_TYPES.length) {
            this.noodleType = NOODLE_TYPES[noodleTypeChoice - 1];
        } else {
            this.noodleType = "기본 면 스파게티"; //나올일 없음
        }

        if (noodleDonenessChoice >= 1 && noodleDonenessChoice <= NOODLE_DONENESS.length) {
            this.noodleDoneness = NOODLE_DONENESS[noodleDonenessChoice - 1];
        } else {
            this.noodleDoneness = "기본 익힘(보통)"; //나올일 없음
        }
    }

    @Override
    public void cook() {
        super.cook();
        if (!noodleType.equals("선택 안함")) { //혹시 모를 에러 대비
            System.out.println("[옵션] 면 종류 : " + noodleType + " | 익힘 정도 : " + noodleDoneness);
        }
    }
}