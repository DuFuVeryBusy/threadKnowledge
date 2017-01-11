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
public class ConditionCommunication {

	public static void main(String[] args) {
		
		final Business bus = new Business();
		
		new Thread(
			new Runnable(){
				@Override
				public void run() {
					for (int i = 0; i < 50; i++) {
						bus.sub(i);
					}
				}
			}
		).start();
		
		for (int j = 0; j < 50; j++) {
			 bus.main(j);
		}
		// new Thread().start();
	}
	
	static class Business{
		
		/*
		 * ������boolean���жϣ��Ⱥ�˳��
		 */
		private boolean bShouldSub = true;
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		public void main(int i){
			lock.lock();
			try{
				// �ȴ�
				while(bShouldSub){
					try {
						//this.wait();
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int j=1;j<=100;j++){
					System.out.println("main thread sequence of " + j + ",loop of " + i);
				}
				
				// �ı�bShouldSubֵ
				bShouldSub = true;
				//this.notify();
				condition.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public void sub (int i){
			lock.lock();
			try{
				// �ȴ�
				while(!bShouldSub){
					try {
						//this.wait();
						condition.await();
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
					System.out.println("sub thread sequence of " + j + ",loop of " + i);
				}
				// �ı�bShouldSubֵ
				bShouldSub = false;
				//this.notify();
				condition.signal();
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


