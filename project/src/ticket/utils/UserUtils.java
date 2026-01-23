package ticket.utils;

import ticket.model.User;

import java.util.ArrayList;

public class UserUtils {
    public static void printUserList(ArrayList<User> users){
        for (User u : users) {
            System.out.print(u + " ");
        }
        System.out.println();
    }

    public static boolean verifyUserwithPwd(ArrayList<User> users, int userNo, String pwd){
        if(userNo<=0 || userNo>= users.size()){
            System.out.println("error: user# is out of bound");
            return false;
        }
        else{
            if(!users.get(userNo).verifyUser(pwd)){
                System.out.println("error: pwd not matched");
                return false;
            }
            return true;
        }
    }

    public static String UNametoRName(ArrayList<User> users,int userNo){
        return users.get(userNo).getName()+userNo;
    }
}

