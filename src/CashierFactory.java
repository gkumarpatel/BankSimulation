import java.util.concurrent.ThreadFactory;

public class CashierFactory implements ThreadFactory {
	private static int cashierCount = 0;

	public Thread newThread(Runnable r) {
		return new Thread(r, ++cashierCount + "");
	}
}
