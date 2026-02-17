package ticket.service;

import ticket.domain.Account;
import ticket.exception.AuthException;
import ticket.exception.DuplicateUserException;
import ticket.exception.InvalidPasswordException;
import ticket.exception.UserNotFoundException;
import ticket.repository.MemoryAccountRepository;

public class AuthService {
    private final MemoryAccountRepository accountRepository;
    //private long nextAccountId = 1L;

    public AuthService(MemoryAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // 계정 생성 (회원가입)
    public Account register(String loginId, String password, String ssn) throws DuplicateUserException{
        //이미 시스템에 계정이 있는지 (1인 1계정)
        if (accountRepository.findByOwnerSsn(ssn).isPresent()) {
            throw new DuplicateUserException("이미 가입된 사용자입니다. (주민번호 중복)");
        }
        //아이디 중복 확인
        if (accountRepository.findByLoginId(loginId).isPresent()) {
            throw new DuplicateUserException("이미 사용 중인 아이디입니다.");
        }

        Account newAccount = new Account(null, loginId, password, ssn);
        accountRepository.save(newAccount);
        return newAccount;
    }

    // 로그인
    public Account login(String loginId, String password) throws AuthException {
        Account account = accountRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 아이디입니다."));

        if (!account.verifyPassword(password)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        return account; // 세션 대신 로그인된 Account 객체를 반환하여 상태 증명에 사용
    }
}