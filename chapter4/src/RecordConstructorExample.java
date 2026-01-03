public class RecordConstructorExample {

    // 1. Range 레코드 정의
    record Range(int from, int to) {

        // [Compact Constructor]
        // 파라미터 선언 없음. this.from = from; 이런 거 안 씀.
        public Range {
            // 유효성 검사나 데이터 조정을 여기서 수행
            if (from > to) {
                // swap 로직: 여기서 파라미터 변수(from, to)를 바꾸면
                // 자동으로 바뀐 값이 인스턴스 필드에 저장됨
                int temp = from;
                from = to;
                to = temp;
            }
            // 블록이 끝나면 자동으로 this.from = from; this.to = to; 가 실행됨
        }

        // [Custom Constructor]
        // 인자가 하나인 생성자 추가
        public Range(int max) {
            // 반드시 canonical constructor(여기선 2개짜리)를 호출해야 함
            this(0, max);
        }
    }

    public static void main(String[] args) {
        // Compact Constructor 테스트 (Swap 동작 확인)
        Range r1 = new Range(10, 5);
        System.out.println(r1); // Range[from=5, to=10] 출력됨

        // Custom Constructor 테스트
        Range r2 = new Range(10);
        System.out.println(r2); // Range[from=0, to=10] 출력됨
    }
}