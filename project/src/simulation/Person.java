package simulation;

//시스템 외부의 실제 유저를 의미
public class Person {
    private String name;
    private String ssn; // 주민등록번호

    public Person(String name, String ssn){
        this.name =name;
        this.ssn = ssn;
    }
    public String getName(){
        return name;
    }
    public String getSsn(){
        return ssn;
    }
}
