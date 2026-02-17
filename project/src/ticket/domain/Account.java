package ticket.domain;

//티켓 시스템 내부에 등록된 계정을 의미
public class Account {
    private Long accountId; // 티켓 시스템 내부 DB 기본키 역할
    private String loginId; // Person의 로그인 ID
    private String password;
    private String ownerSsn; //Person의 주민등록번호

    public Account(Long accountId, String loginId, String password, String ownerSsn){
        this.accountId = accountId;
        this.loginId = loginId;
        this.password = password;
        this.ownerSsn = ownerSsn;
    }

    public  boolean verifyPassword(String inputPwd){
        return this.password.equals(inputPwd);
    }

    //getters
    public Long getAccountId(){
        return accountId;
    }
    public String getLoginId(){
        return  loginId;
    }
    public String getOwnerSsn(){
        return ownerSsn;
    }

    //setter
    public void setAccountId(Long accountId){
        this.accountId = accountId;
    }
}
