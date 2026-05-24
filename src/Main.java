import java.util.*;
import menu.*;
import restaurant.Food;
import role.*;

public class Main {
  public static void main(String[] args) {
    Food selectedFood = null;

    //기존에 main이 했던 역할 나누기 위해
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    MenuRepository menuRepository = new MenuRepository();
    Validator validator = new Validator();
    OrderService orderService = new OrderService();

    MenuCategory category = MenuCategory.NONE;
    List<Food> chosenList = null;

    //1. 카테고리 선택
    while (true) {
      outputView.categoryMenu(); // 카테고리 메뉴 출력
      int inputNum = inputView.readCategoryNumber();// 카테고리 번호 입력
      // 입력 에러(-1)가 감지되면 루프 상단으로 복귀
      if (inputNum == -1) {
        outputView.reInput(); // 다시입력 출력
        continue;
      }

      //정수를 Enum 객체로 즉시 치환 변환 수행
      category = MenuCategory.fromNumber(inputNum);
      // 입력값이 1, 2인지 판단을 Validator확인
      if (validator.isValidCategory(category)) {
        chosenList = menuRepository.getMenusByCategory(category);
        // Enum 객체가 내포한 타이틀 데이터를 직접 꺼내어 출력 (Main의 문자열 제거)
        System.out.println("\n" + category.getTitle());
        break;
      }
      outputView.reInput();
    }


    //2. 카테로그-> 메뉴에서 음식 선택
    int menuChoice = 0;
    while (true) {
      // 카테고리 선택후 메뉴 출력
      outputView.menuBoard(chosenList);
      // 메뉴 음식 입력
      menuChoice = inputView.readMenuChoice();
      // 숫자가 아닌 입력 예외(-1) 검사
      if (menuChoice == -1) {
        outputView.reInput();
        continue;
      }
      //숫자 범위 검사 (고른 숫자가 1이상, 메뉴크기 이하)
      if (validator.isValidChoice(menuChoice, chosenList.size())) {
        break;
      }
      outputView.reInput();
    }

    Food baseFood = chosenList.get(menuChoice - 1); //메뉴고른거 넣기, 인덱스때문에 -1
    int option1 = 0;
    int option2 = 0;

    //3. 옵션 부분
    //3-1. 파스타 옵션의 음식
    if (category == MenuCategory.PASTA) {
      while (true) {
        int maxRange = outputView.pastaOption1();//파스파 옵션보여주고 맥스값 넣기
        option1 = inputView.readOptionNumber();
        if (option1 == -1) { //방어막1 오류값 -1 반환했을때
          outputView.reInput();
          continue;
        }
        if (validator.isValidChoice(option1, maxRange)) {//방어막2 값이 범위안에 있는지
          break;
        }
        outputView.reInput();
      }
      while (true) {
        int maxRange = outputView.pastaOption2();
        option2 = inputView.readOptionNumber();
        if (option2 == -1) {
          outputView.reInput();
          continue;
        }
        if (validator.isValidChoice(option2, maxRange)) {
          break;
        }
        outputView.reInput();
      }

      // 3-2. 스테이크 옵션 선택 영역
    } else if (category == MenuCategory.STEAK) {
      while (true) {
        int maxRange = outputView.steakOption1();
        option1 = inputView.readOptionNumber();
        if (option1 == -1) {
          outputView.reInput();
          continue;
        }
        if (validator.isValidChoice(option1, maxRange)) {
          break;
        }
        outputView.reInput();
      }
      while (true) {
        int maxRange = outputView.steakOption2();
        option2 = inputView.readOptionNumber();

        if (option2 == -1) {
          outputView.reInput();
          continue;
        }
        if (validator.isValidChoice(option2, maxRange)) {
          break;
        }
        outputView.reInput();
      }
    }

    //다양성으로 해결
    selectedFood = orderService.createSelectedFood(baseFood, option1, option2);

    // 4. 최종 주문 확인
    if (selectedFood != null) {
      outputView.printReceipt(selectedFood);
    }
  }
}