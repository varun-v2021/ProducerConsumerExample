import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> sharedList = new ArrayList<>();
		int size = 4;
		Thread prodThread = new Thread(new Producer(sharedList, size), "Producer");
        Thread consThread = new Thread(new Consumer(sharedList, size), "Consumer");
        prodThread.start();
        consThread.start();

        //Read more: http://www.java67.com/2012/12/producer-consumer-problem-with-wait-and-notify-example.html#ixzz5UBBg7NHf
	}

}
