import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount userAccount = new BankAccount("user123", "1234");

        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        if (userAccount.authenticate(userId, userPin)) {
            System.out.println("Login successful! Welcome to the ATM.");
            int choice;
            do {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        userAccount.showTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        userAccount.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        userAccount.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's user ID: ");
                        String recipientId = scanner.next();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        userAccount.transfer(transferAmount, recipientId);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Incorrect user ID or PIN.");
        }
        scanner.close();
    }
}

// BankAccount class to manage user account and transactions
class BankAccount {
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String userId, String pin) {
        return this.userId.equals(userId) && this.pin.equals(pin);
    }

    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions to show.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            System.out.println("Withdrawal successful. Current balance: $" + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposit successful. Current balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(double amount, String recipientId) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Transferred: $" + amount + " to " + recipientId);
            System.out.println("Transfer successful to " + recipientId + ". Current balance: $" + balance);
        } else {
            System.out.println("Insufficient balance or invalid transfer amount.");
        }
    }
}
