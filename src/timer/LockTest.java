package timer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	public static void main(String[] args) {
		
		final Outputer1 outputer = new Outputer1();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					outputer.output("abcdefghigklmn11111");
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					outputer.output("abcdefghigklmn");
				}
			}
		}).start();
		
		
	}
	
	
	static class Outputer1 {
		Lock lock = new ReentrantLock();

		public void output(String name) {
			lock.lock();
			try {
				for (int i = 0; i < name.length(); i++) {

					System.out.print(name.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();
			}
		}
	}

}
