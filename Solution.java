import java.util.Vector;

public class Solution implements Runnable {

	private int numberOfPhiloshpher;
	boolean lock = true;
	private final Vector<Integer> status;

	public Solution(int numberOfPhiloshpher) {
		super();
		status = new Vector<>();
		this.numberOfPhiloshpher = numberOfPhiloshpher;
		for (int i = 0; i < numberOfPhiloshpher; i++) {
			status.addElement(0);
		}
		this.lock = true;
	}

	@Override
	public void run() {

		try {
			while (true) {
				Thread.sleep(10);
				get_fork(Integer.parseInt(Thread.currentThread().getName()));
				Thread.sleep(10);
				release_fork(Integer.parseInt(Thread.currentThread().getName()));

			}
		} catch (Exception e1) {

		}
	}

	synchronized private void get_fork(int philospherNum) {

		try {

			synchronized (status) {
				status.set(philospherNum, PhilospherStatus.HUNGRY);
			}

			System.out.println("philospher " + (philospherNum + 1) + " is hungry");

			synchronized (this) {
				while (!test(philospherNum)) {
					System.out.println("waiting " + (philospherNum + 1));
					wait();
				}
			}

		}

		catch (Exception e) {

			System.out.println("get_fork_exception");
			e.printStackTrace();
		}

	}

	private boolean test(int philospherNum) {
		int left = (philospherNum + numberOfPhiloshpher - 1) % numberOfPhiloshpher;
		int right = (philospherNum + 1) % numberOfPhiloshpher;
		if (status.get(philospherNum) == PhilospherStatus.HUNGRY && status.get(left) != PhilospherStatus.EATING
				&& status.get(right) != PhilospherStatus.EATING) {
			synchronized (status) {
				status.set(philospherNum, PhilospherStatus.EATING);
			}
			try {
			} catch (Exception e1) {

			}

			System.out.println(
					"Philosopher " + (philospherNum + 1) + " takes fork " + (left + 1) + "and " + (right + 1) + " ");
			System.out.println("Philosopher " + (philospherNum + 1) + " is Eating");

			return true;
		}

		return false;
	}

	synchronized private void release_fork(int philospherNum) {

		try {
			synchronized (status) {

				status.set(philospherNum, PhilospherStatus.THINKING);
			}

			int left = (philospherNum + numberOfPhiloshpher - 1) % numberOfPhiloshpher;
			int right = (philospherNum + 1) % numberOfPhiloshpher;
			System.out.println("Philosopher " + (philospherNum + 1) + " putting fork " + (left + 1) + " and "
					+ (right + 1) + " down");
			System.out.println("Philosopher " + (philospherNum + 1) + " is thinking");
			synchronized (this) {
				notifyAll();
			}

		} catch (Exception e) {
			System.out.println(" release exception ");
			e.printStackTrace();
		}

	}

}
