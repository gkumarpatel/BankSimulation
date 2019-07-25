import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankSimulator {
	private final static Logger LOGGER = Logger.getLogger(BankSimulator.class.getName());

	public static void main(String[] args) {
		//date format as required to print on console
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// get the cashier, customer and serving time details from arguments
		int numCashiers = Integer.valueOf(args[0]);
		int numCustomers = Integer.valueOf(args[1]);
		int timePerCustomer = Integer.valueOf(args[2]);

		//cashier factory that has customised thread name to be used as cashier name
		CashierFactory cashierFactory = new CashierFactory();

		//create a thread pool with number of threads equal to number of cashiers so that all the cashiers can serve in parallel
		ExecutorService executorService = Executors.newFixedThreadPool(numCashiers, cashierFactory);

		System.out.println(simpleDateFormat.format(new Date()) + " --> Bank Simulation Started");

		//enqueue the customers in the threadPoolExecutor queue and let cashiers server each of them in the sequence they come
		for (int i = 1; i <= numCustomers; i++) {
			executorService.execute(new CustomerService(i, timePerCustomer));
		}

		//wait for all the customers to be served before ending the bank simulation
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "Interruption occurred awaiting on shutdown signal", e);
		}

		System.out.println(simpleDateFormat.format(new Date()) + " --> Bank Simulation Ended");
	}
}
