package role;

public enum MenuCategory {

    // 열거형 좀 더 알아보기
    // 고유 번호(int)와 화면 출력용 한글 타이틀(String)을 결합하여 상수를 정의
    PASTA(1, "--- 파스타 메뉴 선택 ---"),
    STEAK(2, "--- 스테이크 메뉴 선택 ---"),
    NONE(-1, "오류"); // 입력 실패 대응용

    private final int number;
    private final String title;

    // Enum 생성자 (내부 은닉)
    MenuCategory(int number, String title) {
        this.number = number;
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    /**
     * 사용자가 입력한 정수형 번호(1 또는 2)를 매개변수로 받아서
     * 매칭되는 MenuCategory 객체를 찾아서 반환하는 도메인 로직 메서드
     */
    public static MenuCategory fromNumber(int number) {
        for (MenuCategory category : values()) {
            if (category.number == number) {
                return category;
            }
        }
        return NONE; // 매칭되는 번호가 없으면 NONE 객체 반환 (안전장치)
    }
}