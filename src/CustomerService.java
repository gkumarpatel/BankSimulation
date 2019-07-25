import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerService implements Runnable {
	private int customerId;
	private int timePerCustomer;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public CustomerService(int customerId, int timePerCustomer) {
		this.customerId = customerId;
		this.timePerCustomer = timePerCustomer;
	}

	@Override
	public void run() {
		System.out.println(simpleDateFormat.format(new Date()) + " --> Cashier " + Thread.currentThread().getName() + ": Customer " + customerId + " Started");
		try {
			Thread.sleep(1000 * timePerCustomer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(simpleDateFormat.format(new Date()) + " --> Cashier " + Thread.currentThread().getName() + ": Customer " + customerId + " Completed");
	}
}
