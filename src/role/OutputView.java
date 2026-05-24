package role;

import option.Pasta;
import option.Steak;
import restaurant.Food;
import java.util.List;

public class OutputView {

    //실행 첫화면 카테고리
    public void categoryMenu(){
        System.out.println("=== 레스토랑 키오스크 시스템 ===");
        System.out.println("1. 파스타 라인업");
        System.out.println("2. 스테이크 라인업");
        System.out.print("원하시는 카테고리 번호를 누르세요: ");
    }
    //카테고리의 메뉴
    public void menuBoard(List<Food> chosenList) {
        System.out.println("\n--- 메뉴 선택 ---");
        // 1. 전달받은 리스트의 크기만큼 반복하며 메뉴명과 가격 출력
        for (int i = 0; i < chosenList.size(); i++) {
            System.out.println((i + 1) + ". " + chosenList.get(i).getFoodName() + " (" + chosenList.get(i).getPrice() + "원)");
        }
        System.out.print("메뉴 번호를 고르세요: ");
    }
    //파스타 옵션1,2
    public int pastaOption1(){
        System.out.println("\n--- [옵션 1] 면 종류 선택 ---");
        for (int i = 0; i < Pasta.NOODLE_TYPES.length; i++) {
            System.out.print((i + 1) + ". " + Pasta.NOODLE_TYPES[i]); //메뉴 양만큼 출력
            if (i < Pasta.NOODLE_TYPES.length - 1) System.out.print(" | "); //마지막 부분은 |출력 안되도록
        }
        System.out.print("\n번호 입력: ");
        return Pasta.NOODLE_TYPES.length;
    }
    public int pastaOption2() {
        System.out.println("\n--- [옵션 2] 면 익힘 정도 선택 ---");
        for (int i = 0; i < Pasta.NOODLE_DONENESS.length; i++) {
            System.out.print((i + 1) + ". " + Pasta.NOODLE_DONENESS[i]);
            if (i < Pasta.NOODLE_DONENESS.length - 1) System.out.print(" | ");
        }
        System.out.print("\n번호 입력: ");
        return Pasta.NOODLE_DONENESS.length;
    }

    //스테이크 옵션 1,2
    public int steakOption1() {
        System.out.println("\n--- [옵션 1] 스테이크 스타일 선택 ---");
        for (int i = 0; i < Steak.STEAK_STYLES.length; i++) {
            System.out.print((i + 1) + ". " + Steak.STEAK_STYLES[i]);
            if (i < Steak.STEAK_STYLES.length - 1) System.out.print(" | ");
        }
        System.out.print("\n번호 입력: ");
        return Steak.STEAK_STYLES.length;
    }

    public int steakOption2() {
        System.out.println("\n--- [옵션 2] 고기 굽기 정도 선택 ---");
        for (int i = 0; i < Steak.STEAK_DONENESS.length; i++) {
            System.out.print((i + 1) + ". " + Steak.STEAK_DONENESS[i]);
            if (i < Steak.STEAK_DONENESS.length - 1) System.out.print(" | ");
        }
        System.out.print("\n번호 입력: ");
        return Steak.STEAK_DONENESS.length;
    }

    public void reInput() {
        System.out.println("\n잘못된 값입니다. 다시 입력해주세요\n");
    }

    public void askMoreOrder() {
        System.out.print("\n추가 주문을 하시겠습니까? (1. 더하기 / 2. 종료 및 요리요청): ");
    }

    public void printReceipt(List<Food> orderCart) {
        System.out.println("\n==============================");
        System.out.println("       최 종 주 문 영 수 증     ");
        System.out.println("==============================");
        int totalSum = 0;

        for (Food food : orderCart) {
            System.out.printf("- %-15s : %d원\n", food.getFoodName(), food.getPrice());

            food.cook();
            totalSum += food.getPrice(); //총 가격
        }
        System.out.println("------------------------------");
        System.out.printf("총 결제 금액          : %d원\n", totalSum);
        System.out.println("==============================");
    }

    public void startCookingAlert() {
        System.out.println("\n [시스템] 주방에 요리 주문을 동시 비동기 전달합니다. 잠시만 기다려주세요...\n");
    }
}