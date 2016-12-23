package timer;

/**
 * 
 * @author zhouht
 *
 * 定时器模拟爆炸，每隔2秒和每隔4秒炸一次
 */
public class TraditionTimerTest {

	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				
			}
		}).start();
		
	}
	
	public void outPut(String string){
		System.out.print(string);
		try {
			//线程睡2秒
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}
