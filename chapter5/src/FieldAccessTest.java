import java.lang.reflect.Field;

class SecretBox {
    private String secret = "내 보물 1호";
    private int code = 1234;

    @Override
    public String toString() {
        return "SecretBox [secret=" + secret + ", code=" + code + "]";
    }
}

public class FieldAccessTest {
    public static void main(String[] args) {
        try {
            SecretBox box = new SecretBox();
            System.out.println("변경 전: " + box);

            Class<?> cl = box.getClass();

            // 1. private 필드 'secret' 가져오기
            Field secretField = cl.getDeclaredField("secret");

            // 2. 접근 권한 강제 획득 (이게 없으면 에러 발생!)
            secretField.setAccessible(true);

            // 3. 값 읽기
            System.out.println("해킹한 값: " + secretField.get(box));

            // 4. 값 수정하기 (private인데도 바뀜)
            secretField.set(box, "쓰레기");

            System.out.println("변경 후: " + box);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}