import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerService implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(CustomerService.class.getName());
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
		//serve each customer for timePerCustomer seconds
		try {
			Thread.sleep(1000 * timePerCustomer);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, String.format("Interruption occurred serving customer number : %s", customerId), e);
		}
		System.out.println(simpleDateFormat.format(new Date()) + " --> Cashier " + Thread.currentThread().getName() + ": Customer " + customerId + " Completed");
	}
}
