package ticket.utils;

import ticket.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {
    // 사용자 목록 출력
    public static void printUserList(ArrayList<User> users){
        if (users == null || users.isEmpty()) {
            System.out.println("등록된 사용자가 없습니다.");
            return;
        }
        for (User u : users) {
            System.out.print(u + " ");
        }
        System.out.println();
    }
    //예약에 사용할 이름(이름+번호) 출력
    public static String UNametoRName(List<User> users, int userNo){
        return users.get(userNo).getName()+userNo;
    }
}

