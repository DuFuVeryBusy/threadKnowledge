package timer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * java �̳߳� Executor   Executors 
 * @author zhouht
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		// ͨ��Executors��ȡ����һ�����������̵߳��̳߳�
		// ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		// 
		//ExecutorService fixedThreadPool = Executors.newCachedThreadPool();
		// ���newSingleThreadExecutor()�ĺô��ǣ�ֻ��һ���̣߳������������߳������Ļ�������һ���µ��߳̽�����һ���̵߳�����
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
						// ����߳���Ϣ
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
