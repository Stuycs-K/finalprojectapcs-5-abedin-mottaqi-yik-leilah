public class PlayerSun {
    private int balance = 100;

    public int getBalance() {
        return balance;
    }

    public void addSun(int amount) {
        balance += amount;
    }

    public boolean spendSun(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
