package role;

import java.util.*;
import menu.*;
import restaurant.Food;

//메뉴리스트 저장 클래스
public class MenuRepository {
    // 파스타와 스테이크 메뉴를 보관할 리스트 정의 (외부 변경 방지를 위해 private final)
    private final List<Food> pastaMenu = new ArrayList<>();
    private final List<Food> steakMenu = new ArrayList<>();

    // 메뉴판
    public MenuRepository() {
        //옵션값 0이였는데 뺌
        //파스타
        pastaMenu.add(new TomatoPasta());
        pastaMenu.add(new CreamPasta());
        pastaMenu.add(new OilPasta());
        //스테이크
        steakMenu.add(new PorkSteak());
        steakMenu.add(new BeefSteak());
    }
    //메뉴판 카테고리 1,2 -> 정수 대신 MenuCategory 객체 자체를 매개변수로 수령
    public List<Food> getMenusByCategory(MenuCategory category) {
        if (category == MenuCategory.PASTA) {
            return pastaMenu;
        } else if (category == MenuCategory.STEAK) {
            return steakMenu;
        }
        return new ArrayList<>(); // 에러 방지를 위한 빈 리스트 반환
    }
}