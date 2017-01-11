package timer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author zhouht 现成通讯问题
 * 子线程循环10次，接着主线程循环100次，
 * 接着又到子线程循环10次，接着再回到主线程循环100次，
 * 如此循环50次
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
		 * 这里用boolean的判断，先后顺序
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
				// 等待
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
				
				// 改变bShouldSub值
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
				// 等待
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
				// 改变bShouldSub值
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
				// 等待
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
				// 改变bShouldSub值
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
 *  因为处理main线程和sub线程的锁对象要 用一个，
 *  还有他们以一个小组的形式组成，是每个循环的单元
 */


