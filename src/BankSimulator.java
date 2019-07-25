import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankSimulator {

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int numCashiers = Integer.valueOf(args[0]);
		int numCustomers = Integer.valueOf(args[1]);
		int timePerCustomer = Integer.valueOf(args[2]);

		CashierFactory cashierFactory = new CashierFactory();
		ExecutorService executorService = Executors.newFixedThreadPool(numCashiers, cashierFactory);

		System.out.println(simpleDateFormat.format(new Date()) + " --> Bank Simulation Started");
		for (int i = 1; i <= numCustomers; i++) {
			executorService.execute(new CustomerService(i, timePerCustomer));
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(simpleDateFormat.format(new Date()) + " --> Bank Simulation Ended");
	}
}
