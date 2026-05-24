package role;

import restaurant.Food;

public class ChefThread implements Runnable {
    // 각 스레드(요리사) 요리
    private final Food food;

    public ChefThread(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        try {
            System.out.println("[주방 안내] 요리사가 " + food.getFoodName() + " 조리를 시작합니다.");
            // 기본적으로 초는 밀리초
            int cookTimeMs = food.getCookTime();
            //슬립을 조리중으로 표현
            Thread.sleep(cookTimeMs);
            //1초->1분으로 표기하기 위해
            double cookTimeSec = cookTimeMs / 100.0;

            System.out.printf("[주방 안내] %s 요리가 완성되었습니다! (조리시간: %.1f초)\n", food.getFoodName(), cookTimeSec);

        } catch (InterruptedException e) {
            System.out.println("[주방 에러] 조리 중단 오류 발생");
        }
    }
}