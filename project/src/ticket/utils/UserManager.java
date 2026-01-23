// ticket/utils/UserManager.java
package ticket.utils;

import ticket.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final List<User> users = new ArrayList<>();
    private int nextID = 0;

    // 사용자 추가
    public User addUser(String name, String pwd, int initBalance) {
        User user = new User(name, nextID++, pwd, initBalance);
        users.add(user);
        return user;
    }

    // 비밀번호 검증
    public boolean verifyUser(int userNo, String pwd) {
        if (userNo < 0 || userNo >= users.size()) {
            System.out.println("error: user# is out of bound");
            return false;
        }
        User user = users.get(userNo);
        if (!user.verifyUser(pwd)) {
            System.out.println("error: pwd not matched");
            return false;
        }
        return true;
    }

    // ID로 사용자 찾기
    public User findUserById(int userNo) {
        if (userNo < 0 || userNo >= users.size()) {
            return null;
        }
        return users.get(userNo);
    }

    // 전체 사용자 목록 반환
    public List<User> getUsers() {
        return users;
    }
}
