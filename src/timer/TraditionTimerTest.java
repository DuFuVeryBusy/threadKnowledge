package timer;

/**
 * 
 * @author zhouht
 *
 * ��ʱ��ģ�ⱬը��ÿ��2���ÿ��4��ըһ��
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
			//�߳�˯2��
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}
