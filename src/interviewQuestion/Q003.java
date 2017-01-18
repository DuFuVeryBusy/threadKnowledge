package interviewQuestion;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 3)��java��wait��sleep�����Ĳ�ͬ��
 * ͨ�����ڵ绰�����о������ʵ���Java�߳��������⡣���Ĳ�ͬ���ڵȴ�ʱwait���ͷ�����
 * ��sleepһֱ��������Waitͨ���������̼߳佻����sleepͨ����������ִͣ�С�
 * 
 * 4����Javaʵ���������С�
 * ����һ����Լ��ѵĶ��߳��������⣬���ܴﵽ�ܶ��Ŀ�ġ�
 * ��һ�������Լ���ѡ���Ƿ���ʵ�ʵ���Java�߳�д����
 * �ڶ������Լ���ѡ�߶Բ�����������⣬��������Ը�������ʺܶ����⡣
 * �������wait()��notify()������ʵ���������У������Ҫ���������µ�Java 5�еĲ���������дһ�Ρ�
 * @author zhouht
 * 
 * blocking:����
 * deque��˫�˶���
 * queue������
 * TransferQueue:A BlockingQueue in which producers may wait for consumers to receive elements.
 */
public class Q003 {
	/*
	 *  ʹ���������ѵ�ģ�ͣ�
	 *  ������ÿһ������һ����sleep��1000��
	 *  ���ѣ�һֱ����sleep��0��
	 */
	// ʹ�ö��У��� ���ݽ��д洢
	
	// ���촫��boolean����жϣ�Ĭ��Ϊfalse  transferer = fair ? new TransferQueue() : new TransferStack();
	final BlockingQueue bq = new ArrayBlockingQueue(4);
	final static Q003 q = new Q003();
	
	// java �����Դ�����������
	public static void main(String[] args) {

		// ���������̣߳��ֱ���룬�Ͷ�ȡ
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
	// ������д�������
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
	// ��bq�л�ȡ����
	public void getData(){
		try {
			Integer take = (Integer) bq.take();
			System.out.println("take "+take);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
