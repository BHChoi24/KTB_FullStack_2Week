package role;

//Validator 뜻 : 확인자, 검증자 (validation 확인 + er)
public class Validator {

    //카테고리 1,2번 확인하고반환 -> 열거형으로 변환
    public boolean isValidCategory(MenuCategory category) {
        return category != MenuCategory.NONE;
    }

    //1부터 맥스값까지 있는지 확인 (메뉴 선택, 파스타 옵션, 스테이크 옵션)
    //매개변수 choice 사용자가 입력한 번호, maxRange 해당 메뉴판 리스트의 최대 크기(size)
    public boolean isValidChoice(int choice, int maxRange) {
        return choice >= 1 && choice <= maxRange;
    }
}