package ticket.model;

public class Account {
    private int balance;

    public Account() {
        this.balance = 0;
    }

    public Account(int initMoney) {
        this.balance = initMoney;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean withdrawal(int amount) {
        if (amount <= 0) return false;
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }
}
