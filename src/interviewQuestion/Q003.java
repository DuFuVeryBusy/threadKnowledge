package interviewQuestion;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 3)在java中wait和sleep方法的不同？
 * 通常会在电话面试中经常被问到的Java线程面试问题。最大的不同是在等待时wait会释放锁，
 * 而sleep一直持有锁。Wait通常被用于线程间交互，sleep通常被用于暂停执行。
 * 
 * 4）用Java实现阻塞队列。
 * 这是一个相对艰难的多线程面试问题，它能达到很多的目的。
 * 第一，它可以检测侯选者是否能实际的用Java线程写程序；
 * 第二，可以检测侯选者对并发场景的理解，并且你可以根据这个问很多问题。
 * 如果他用wait()和notify()方法来实现阻塞队列，你可以要求他用最新的Java 5中的并发类来再写一次。
 * @author zhouht
 * 
 * blocking:阻塞
 * deque：双端队列
 * queue：队列
 * TransferQueue:A BlockingQueue in which producers may wait for consumers to receive elements.
 */
public class Q003 {
	/*
	 *  使用生产消费的模型，
	 *  生产：每一秒生产一个，sleep（1000）
	 *  消费：一直消费sleep（0）
	 */
	// 使用队列，对 数据进行存储
	
	// 构造传入boolean后的判断，默认为false  transferer = fair ? new TransferQueue() : new TransferStack();
	final BlockingQueue bq = new ArrayBlockingQueue(4);
	final static Q003 q = new Q003();
	
	// java 具有自带的阻塞队列
	public static void main(String[] args) {

		// 开启两个线程，分别存入，和读取
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i = 0 ; i<20; i++){
					q.setData();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(;;){
					q.getData();
				}
			}
		}).start();

	}
	// 向队列中存入数据
	public void setData(){
		Random r = new Random();
		int i = r.nextInt(1000);
		
		try {
			Thread.sleep(100);
			bq.put(i);
			System.out.println("set: " + i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	// 从bq中获取数据
	public void getData(){
		try {
			Integer take = (Integer) bq.take();
			System.out.println("take "+take);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
