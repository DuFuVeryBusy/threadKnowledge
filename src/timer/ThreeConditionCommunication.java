package timer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author zhouht �ֳ�ͨѶ����
 * ���߳�ѭ��10�Σ��������߳�ѭ��100�Σ�
 * �����ֵ����߳�ѭ��10�Σ������ٻص����߳�ѭ��100�Σ�
 * ���ѭ��50��
 */
public class ThreeConditionCommunication {

	public static void main(String[] args) {
		
		final Business bus = new Business();
		
		new Thread(
				new Runnable(){
					@Override
					public void run() {
						for (int i = 1; i <= 10; i++) {
							bus.sub2(i);
						}
					}
				}
			).start();
		new Thread(
				new Runnable(){
					@Override
					public void run() {
						for (int i = 1; i <= 10; i++) {
							bus.sub3(i);
						}
					}
				}
			).start();
		
		for (int j = 1; j <= 10; j++) {
			 bus.main(j);
		}
		// new Thread().start();
	}
	
	static class Business{
		
		/*
		 * ������boolean���жϣ��Ⱥ�˳��
		 */
		// private boolean bShouldSub = true;
		private int bShouldSub = 1;
		
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		
		public void main(int i){
			lock.lock();
			try{
				// �ȴ�
				while(bShouldSub != 1){
					try {
						//this.wait();
						condition1.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int j=1;j<=10;j++){
					System.out.println("main thread sequence of " + j + ",loop of " + i);
				}
				
				// �ı�bShouldSubֵ
				bShouldSub = 2;
				//this.notify();
				condition2.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public void sub2 (int i){
			lock.lock();
			try{
				// �ȴ�
				while(bShouldSub != 2){
					try {
						//this.wait();
						condition2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int j=1;j<=10;j++){
					System.out.println("sub2 thread sequence of " + j + ",loop of " + i);
				}
				// �ı�bShouldSubֵ
				bShouldSub = 3;
				//this.notify();
				condition3.signal();
			}finally {
				lock.unlock();
			}
		}
		
		public void sub3 (int i){
			lock.lock();
			try{
				// �ȴ�
				while(bShouldSub != 3){
					try {
						//this.wait();
						condition3.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int j=1;j<=10;j++){
					System.out.println("sub3 thread sequence of " + j + ",loop of " + i);
				}
				// �ı�bShouldSubֵ
				bShouldSub = 1;
				//this.notify();
				condition1.signal();
			}finally {
				lock.unlock();
			}
		}
	}
}
/*
 *  ��Ϊ����main�̺߳�sub�̵߳�������Ҫ ��һ����
 *  ����������һ��С�����ʽ��ɣ���ÿ��ѭ���ĵ�Ԫ
 */


