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
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				for (int j = 0; j < 50; j++) {
					for (int i = 0; i < 10; i++) {
						System.out.println("sub thread squece of "+j + " loop of "+i);
					}
				}
			}
		}).start();
		
		
		for (int j = 0; j < 50; j++) {
			for (int i = 0; i < 10; i++) {
				System.out.println("main thread squece of "+j + " loop of "+i);
			}
		}
		
		
		new Thread().start();
		
		
	}
}
