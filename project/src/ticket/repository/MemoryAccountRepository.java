package ticket.repository;

import ticket.domain.Account;
import ticket.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryAccountRepository implements Repository<Account, Long> {

    private final List<Account> store = new ArrayList<>();
    private static long sequence = 0L;

    @Override
    public void save(Account account) {
        // 신규 가입
        if(account.getAccountId() == null){
            account.setAccountId(++sequence);
            store.add(account);
        }
        else{ // 수정,업데이트
            store.removeIf(existing -> existing.getAccountId().equals(account.getAccountId()));
            store.add(account);
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        return store.stream()
                .filter(account -> account.getAccountId().equals(id))
                .findFirst();
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(store); // 원본 훼손 방지를 위해 복사본 반환
    }

    @Override
    public void delete(Long id) {
        store.removeIf(account -> account.getAccountId().equals(id));
    }

    // --- Account 전용 특화 조회 메서드 ---

    // 로그인용: loginId로 계정 찾기
    public Optional<Account> findByLoginId(String loginId) {
        return store.stream()
                .filter(account -> account.getLoginId().equals(loginId))
                .findFirst();
    }

    // 중복가입 방지용: 주민번호(ssn)로 계정 찾기
    public Optional<Account> findByOwnerSsn(String ssn) {
        return store.stream()
                .filter(account -> account.getOwnerSsn().equals(ssn))
                .findFirst();
    }
}