package timer;

/**
 * 
 * @author zhouht 现成通讯问题
 * 子线程循环10次，接着主线程循环100次，
 * 接着又到子线程循环10次，接着再回到主线程循环100次，
 * 如此循环50次
 */
public class TraditionalThreadCommunication {

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
}
/*
 *  因为处理main线程和sub线程的锁对象要 用一个，
 *  还有他们以一个小组的形式组成，是每个循环的单元
 */

class Business{
	
	/*
	 * 这里用boolean的判断，先后顺序
	 */
	
	private boolean bShouldSub = true;
	
	public synchronized void main(int i){
		
		// 等待
		while(bShouldSub){
			try {
				this.wait();
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
		
		// 改变bShouldSub值
		bShouldSub = true;
		this.notify();
	
	}
	
	public synchronized void sub (int i){
		// 等待
		while(!bShouldSub){
			try {
				this.wait();
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
		// 改变bShouldSub值
		bShouldSub = false;
		this.notify();
	}
	
	
	
	
}

