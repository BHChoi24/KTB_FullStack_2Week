import java.util.*;
import menu.*;
import restaurant.Food;
import role.*;

public class Main {
  public static void main(String[] args) throws InterruptedException {

    //기존에 main이 했던 역할 나누기 위해
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    MenuRepository menuRepository = new MenuRepository();
    Validator validator = new Validator();
    OrderService orderService = new OrderService();


    List<Food> orderCart = new ArrayList<>(); //연속 주문할때 저장

    // 기존 주문1번을 포함시켜서 연속 주문으로 변경
    while (true) {
      MenuCategory category = MenuCategory.NONE;
      List<Food> chosenList = null;

      // 1. 카테고리 선택
      while (true) {
        outputView.categoryMenu(); // 카테고리 메뉴 출력
        int inputNum = inputView.readCategoryNumber();// 카테고리 번호 입력

        if (inputNum == -1) { // 정수가 아닌 글자/공백 입력 에러 처리
          outputView.reInput();// 다시입력 출력
          continue;
        }

        //정수를 Enum 객체로 즉시 치환 변환 수행
        category = MenuCategory.fromNumber(inputNum);
        // 입력값이 1, 2인지 판단을 Validator확인
        if (validator.isValidCategory(category)) {
          chosenList = menuRepository.getMenusByCategory(category);
          // Enum 객체가 내포한 타이틀 데이터를 직접 꺼내어 출력 (Main의 문자열 제거)
          System.out.println("\n" + category.getTitle());
          break; // 정상 입력 시 내부 루프 탈출
        }
        outputView.reInput();
      }

      // 2. 카테고리->메뉴에서 음식 선택
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
      //메뉴고른거 넣기, 인덱스때문에 -1
      Food baseFood = chosenList.get(menuChoice - 1);
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
          if (validator.isValidChoice(option1, maxRange)) { //방어막2 값이 범위안에 있는지
            break;
          }
          outputView.reInput();
        }
        while (true) {
          int maxRange = outputView.pastaOption2();
          option2 = inputView.readOptionNumber();
          if (option2 == -1) {
            outputView.reInput();
            continue; }
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
          if (option1 == -1) { outputView.reInput(); continue; }
          if (validator.isValidChoice(option1, maxRange)) break;
          outputView.reInput();
        }
        while (true) {
          int maxRange = outputView.steakOption2();
          option2 = inputView.readOptionNumber();
          if (option2 == -1) { outputView.reInput(); continue; }
          if (validator.isValidChoice(option2, maxRange)) break;
          outputView.reInput();
        }
      }

      // 다형성(Polymorphism) 연산: baseFood 내부의 createOrder() 호출을 유도하여
      // 완벽한 하위 세부 옵션 주문서 인스턴스를 힙 메모리에 독립 생성(new)함
      Food selectedFood = orderService.createSelectedFood(baseFood, option1, option2);
      if (selectedFood != null) {
        orderCart.add(selectedFood); // 생성 완료된 실체 주문서 주소를 장바구니 리스트에 적재
      }

      //5. anything else? 주문 묻기
      OrderStep step = OrderStep.INVALID;
      while (true) {
        outputView.askMoreOrder(); // 1.더하기 / 2.종료 출력 위임
        int stepNum = inputView.readOrderStepNumber();
        step = OrderStep.fromNumber(stepNum); // 입력 숫자를 안전한 흐름제어용 Enum 객체로 치환

        if (step != OrderStep.INVALID) {
          break;
        }
        outputView.reInput();
      }

      // 숫자가 아닌 명확한 자바 열거형 상태 상수를 대조하여, 대규모 주문 입력을 끝낼지 판별
      if (step == OrderStep.COOK_START) {
        break; // 장바구니 누적 무한루프를 탈출하고 비동기 조리 단계로 진입
      }
    }

    //5. 멀티스레드
    outputView.startCookingAlert();
    List<Thread> kitchenThreads = new ArrayList<>(); // 생성될 스레드 제어 리모컨들을 보관할 전용 컬렉션

    for (Food orderedFood : orderCart) {
      // 장바구니에 담긴 요리 하나당 요리사 한명
      Thread chef = new Thread(new ChefThread(orderedFood));
      kitchenThreads.add(chef); // 관리 명부에 저장
      chef.start(); //스레드 생성
    }

    for (Thread chef : kitchenThreads) {
      chef.join(); // 주방 스레드 기다리기
    }

    // 주방 스레드들 완료된 시점에 총합 영수증 최종 출력
    if (!orderCart.isEmpty()) {
      outputView.printReceipt(orderCart);
    }
  }
}