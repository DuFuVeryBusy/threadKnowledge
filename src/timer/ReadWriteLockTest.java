package timer;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * ��д�� ReadWriteLock 
 * ��Ϊ������д����������������⣬������д�����⣬д����д�����⣬������jvm�Լ����Ƶģ���ֻҪ�Ϻ���Ӧ�������ɡ�
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
	// �����ݣ���������������⣬������д������
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
	// д����д��������������
	public void put(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");					
			Thread.sleep((long)(Math.random()*1000));
			this.data = data;		
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		} catch (InterruptedException e) {
		}finally{
			// ������⿪��������⿪����ô���������
			rwl.writeLock().unlock();
		}
	}
	
}