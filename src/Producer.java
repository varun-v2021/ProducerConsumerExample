import java.util.List;

public class Producer implements Runnable {

	private final List<Integer> sharedResource;
	private final int size;

	Producer(List<Integer> sharedResource, int size) {
		this.sharedResource = sharedResource;
		this.size = size;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 7; i++) {
			System.out.println("Produced: " + i);
			try {
				produce(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void produce(int i) throws InterruptedException {
		while (sharedResource.size() == size) {
			synchronized (sharedResource) {
				System.out.println("List is full " + Thread.currentThread().getName() + " is waiting , size "+sharedResource.size());
				sharedResource.wait();
			}
		}

		// producing element and notify consumers
		synchronized (sharedResource) {
			sharedResource.add(i);
			sharedResource.notifyAll();
		}
	}

}
