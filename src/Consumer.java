import java.util.List;

public class Consumer implements Runnable {

	private final List<Integer> sharedResource;
	private final int size;

	Consumer(List<Integer> sharedResource, int size) {
		this.sharedResource = sharedResource;
		this.size = size;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				System.out.println("Consumed: " + consume());
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private int consume() throws InterruptedException {
		while (sharedResource.isEmpty()) {
			synchronized (sharedResource) {
				System.out.println("List is empty " + Thread.currentThread().getName() + " is waiting, size "+sharedResource.size());
				sharedResource.wait();
			}
			//sharedResource.wait(); //Illegal monitor state exception as wait and notify must always be inside synchronized block
		}

		// producing element and notify consumers
		synchronized (sharedResource) {
			sharedResource.notifyAll();
			return (Integer) sharedResource.remove(0);
		}
	}

}
