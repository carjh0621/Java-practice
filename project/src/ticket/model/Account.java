package ticket.model;

import ticket.exception.InsufficientBalanceException;
import ticket.exception.PaymentException;

public class Account {
    private int balance;

    public Account() {
        this.balance = 0;
    }

    public Account(int initMoney) {
        this.balance = initMoney;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdrawal(int amount) throws PaymentException {
        if(balance < amount){
            throw new InsufficientBalanceException("잔액이 부족합니다. (잔액: " + balance + "원)");
        }
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
