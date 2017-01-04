package timer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * java 线程池 Executor   Executors 
 * @author zhouht
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		// 通过Executors获取到的一个含有三个线程的线程池
		// ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		// 
		//ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
		// 这个newSingleThreadExecutor()的好处是，只有一个线程，但是如果这个线程死掉的话，会有一个新的线程接替上一个线程的任务
		ExecutorService fixedThreadPool = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 10; i++) {
			final int task = i ;
			fixedThreadPool.execute(new Runnable(){
				@Override
				public void run() {
					
					for (int j = 0; j < 10; j++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// 输出线程信息
						System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
					}
					
				}
			});
		}
			
		
		System.out.println("all of 10 tasks have committed! ");
		fixedThreadPool.shutdown();
		//fixedThreadPool.shutdownNow();
		/**/
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+ "  boming!");

			}
		}, 4, TimeUnit.SECONDS);
		
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+"  boming~!");
			}
		}, 3, 3, TimeUnit.SECONDS);
	}

}
