

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException() {
        super("Insufficient Balance in account");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
