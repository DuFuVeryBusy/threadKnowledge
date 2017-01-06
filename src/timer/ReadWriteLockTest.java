package timer;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 读写锁 ReadWriteLock 
 * 分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，写锁与写锁互斥，这是由jvm自己控制的，你只要上好相应的锁即可。
 * @author zhouht
 *
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for(int i=0;i<3;i++)
		{
			new Thread(){
				public void run(){
					while(true){
						q3.get();						
					}
				}
				
			}.start();

			new Thread(){
				public void run(){
					while(true){
						q3.put(new Random().nextInt(10000));
					}
				}			
				
			}.start();
		}
		
	}

}
class Queue3{
	
	Object data = null;
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	// 读数据，读锁与读锁不互斥，读锁和写锁互斥
	public void get(){
		rwl.readLock().lock();
		try{
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + " have read data :" + data);
		}catch(Exception e){
		}finally {
			rwl.readLock().unlock();
		}
	}
	// 写锁，写锁与其他都互斥
	public void put(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");					
			Thread.sleep((long)(Math.random()*1000));
			this.data = data;		
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		} catch (InterruptedException e) {
		}finally{
			// 锁必须解开，如果不解开，那么会造成阻塞
			rwl.writeLock().unlock();
		}
	}
	
}