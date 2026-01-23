package ticket.model;

import java.util.ArrayList;

public class User {
    private String name;
    private int UserID;
    private String pwd;
    private Account account;

    public User(String name, int UID, String pwd, int initBalance){
        this.name=name;
        this.UserID=UID;
        this.pwd=pwd;
        this.account = new Account(initBalance);
    }
    public String getName(){
        return this.name;
    }
    public int getUserID(){
        return this.UserID;
    }

    public boolean deposit(int amount){
        return account.deposit(amount);
    }

    public boolean pay(int amount){
        return account.withdrawal(amount);
    }

    public int getBalance(){
        return account.getBalance();
    }

    @Override
    public String toString(){
        return "User#"+UserID+"["+name+"]";
    }
    public boolean verifyUser(String pwd){
        if(this.pwd.equals(pwd)){
            return true;
        }
        else{
            return false;
        }
    }

}
