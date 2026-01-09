package sealedpayment;


// 결제 수단은 CreditCard, Cash, DigitalWallet 3가지만 존재한다고 정의
sealed interface PaymentMethod permits CreditCard, Cash, DigitalWallet {
    void process(double amount);
}

// final 서브클래스
// 신용카드는 더 이상 하위 타입으로 나눌 필요가 없으므로 final로 닫음
// (Record를 사용하여 간결하게 데이터 표현)
final record CreditCard(String cardNumber, String cvc) implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing credit card " + cardNumber + " for $" + amount);
    }
}

// final 서브클래스
final record Cash() implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing cash payment for $" + amount);
    }
}

//non-sealed 서브클래스
// 전자지갑은 미래에 새로운 페이 서비스가 추가될 수 있도록 봉인을 해제함
// 이제 누구나 DigitalWallet을 상속받을 수 있음
non-sealed abstract class DigitalWallet implements PaymentMethod {
    protected String walletId;

    public DigitalWallet(String walletId) {
        this.walletId = walletId;
    }
}

// DigitalWallet은 non-sealed이므로 외부에서 자유롭게 상속 가능
class ApplePay extends DigitalWallet {
    public ApplePay(String id) { super(id); }

    @Override
    public void process(double amount) {
        System.out.println("Authorizing Apple Pay (" + walletId + ") for $" + amount);
    }
}

class SamsungPay extends DigitalWallet {
    public SamsungPay(String id) { super(id); }

    @Override
    public void process(double amount) {
        System.out.println("Authorizing Samsung Pay (" + walletId + ") for $" + amount);
    }
}

// 메인 테스트 클래스
public class SealedExample {
    public static void main(String[] args) {
        PaymentMethod[] payments = {
                new CreditCard("1234-5678", "999"),
                new Cash(),
                new ApplePay("apple_user_01"),
                new SamsungPay("samsung_user_99")
        };

        for (PaymentMethod p : payments) {
            System.out.println(getDescription(p));
            p.process(100.0);
            System.out.println("---");
        }
    }

    // Pattern Matching for switch (Java 17+)
    // PaymentMethod의 직계 자식은 3개뿐이지만, DigitalWallet이 non-sealed이므로
    // 모든 케이스를 커버하려면 DigitalWallet의 하위 타입까지 고려하거나 default가 필요할 수 있음.
    // 하지만 여기서는 상위 타입인 DigitalWallet으로 퉁쳐서 처리함.
    public static String getDescription(PaymentMethod p) {
        return switch (p) {
            case CreditCard c -> "Card Payment: " + c.cardNumber();
            case Cash c -> "Cash Payment";
            case DigitalWallet d -> "Digital Wallet Payment (ID: " + d.walletId + ")";
            // PaymentMethod는 sealed이지만 DigitalWallet이 non-sealed라서
            // 이론적으로 알 수 없는 DigitalWallet의 서브클래스가 올 수 있음.
            // 하지만 상위 타입인 DigitalWallet 케이스가 존재하므로 완전성(Exhaustiveness) 만족.
        };
    }
}