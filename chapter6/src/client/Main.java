package client;

import serviceLoader.Cipher;
import serviceLoader.impl.CaesarCipher; // 타입을 확인하기 위해 임포트 (실제론 인터페이스만 아는게 좋음)
import java.util.ServiceLoader;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1. ServiceLoader 준비 (아직 객체 생성 안 됨) ---");
        // 이 시점에서는 설정 파일(META-INF)만 읽고 준비만 합니다.
        ServiceLoader<Cipher> loader = ServiceLoader.load(Cipher.class);

        System.out.println("--- 2. Stream으로 탐색 시작 ---");

        Optional<Cipher> cipherOptional = loader.stream()
                // (1) filter: 객체 생성 없이 '타입(이름표)'만 확인
                .filter(provider -> {
                    System.out.println("🔍 [검사] 발견된 클래스 타입: " + provider.type().getName());
                    // CaesarCipher 클래스가 맞는지 확인
                    return provider.type() == CaesarCipher.class;
                })
                // (2) findFirst: 조건에 맞는 첫 번째 녀석 선택 (아직 생성 안 됨)
                .findFirst()
                // (3) map: 여기서 get()을 호출하는 순간! 생성자(new)가 실행됨
                .map(provider -> {
                    System.out.println("🚀 [로딩] 이제 객체를 생성합니다...");
                    return provider.get();
                });

        System.out.println("--- 3. 결과 확인 ---");

        if (cipherOptional.isPresent()) {
            Cipher cipher = cipherOptional.get();
            System.out.println("✅ 서비스 획득 성공! 강도: " + cipher.strength());
        } else {
            System.out.println("❌ 서비스를 찾을 수 없습니다.");
        }
    }
}