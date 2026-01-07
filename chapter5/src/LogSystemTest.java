import java.util.ArrayList;
import java.util.Arrays;

public class LogSystemTest {
    public static void main(String[] args) {
        //문자열 로그를 담을 리스트 생성
        ArrayList<String> logs = new ArrayList<>();

        //순차적으로 데이터 추가 (add)
        logs.add("[INFO] 서버 시작");
        logs.add("[INFO] DB 연결 성공");
        logs.add("[ERROR] 널 포인터 예외 발생");

        System.out.println("=== 1. 초기 상태 ===");
        printLogs(logs);



        //값 수정 (set): 2번 인덱스(ERROR)를 수정
        logs.set(2, "[WARN] 연결 지연 발생 (수정됨)");

        System.out.println("\n=== 2. 수정 후 (set) ===");
        printLogs(logs);

        //중간 삽입 (add index): 1번 인덱스에 끼워넣기
        //뒤에 있는 애들은 자동으로 한 칸씩 밀려남 (Shift)
        logs.add(1, "[DEBUG] 설정 파일 로드 중...");

        System.out.println("\n=== 3. 중간 삽입 후 (add index) ===");
        printLogs(logs);

        //삭제 (remove): 0번 인덱스 삭제
        // 뒤에 있는 애들이 앞으로 당겨짐
        String removedLog = logs.remove(0);
        System.out.println("\n>> 삭제된 로그: " + removedLog);

        System.out.println("=== 4. 삭제 후 (remove) ===");
        printLogs(logs); // 인덱스가 당겨져서 [DEBUG]... 가 0번이 됨

        //배열로 변환 (toArray)
        //리스트 크기(size)만큼의 배열을 만들어서 데이터를 옮겨담음
        String[] logArray = new String[logs.size()];
        logs.toArray(logArray);

        System.out.println("\n=== 5. 배열로 변환 확인 ===");
        System.out.println(Arrays.toString(logArray));
    }

    // 리스트 출력 도우미 메서드 (향상된 for문 사용)
    private static void printLogs(ArrayList<String> list) {
        int index = 0;
        for (String log : list) {
            System.out.println(index++ + ": " + log);
        }
    }
}
