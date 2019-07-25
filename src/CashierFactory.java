import java.util.concurrent.ThreadFactory;

public class CashierFactory implements ThreadFactory {
	private static int cashierCount = 0;

	public Thread newThread(Runnable r) {
		//rename the threads to user as cashier id
		return new Thread(r, ++cashierCount + "");
	}
}
