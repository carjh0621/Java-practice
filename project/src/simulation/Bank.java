package simulation;

import java.util.HashMap;
import java.util.Map;

// 티켓 시스템과는 별도의 외부 은행
public class Bank {
    // 주민번호를 키로, 잔액을 값으로 가짐 (현실의 계좌번호 역할 축약)
    private final Map<String, Integer> balances = new HashMap<>();

    public void createBankAccount(String ssn, int initialBalance) {
        balances.put(ssn, initialBalance);
    }

    public boolean withdraw(String ssn, int amount) {
        int current = balances.getOrDefault(ssn, 0);
        if (current >= amount) {
            balances.put(ssn, current - amount);
            return true;
        }
        return false; // 잔액 부족
    }

    public void deposit(String ssn, int amount) {
        balances.put(ssn, balances.getOrDefault(ssn, 0) + amount);
    }

    public int getBalance(String ssn) {
        return balances.getOrDefault(ssn, 0);
    }
}