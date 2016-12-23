package timer;

public class TraditionalThread {

	public static void main(String[] args) {
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
				
			}
		};
		thread.start();
		
		Thread thread1 = new Thread(new Runnable(){
			@Override
			public void run() {
				
				while(true){
					try {
						Thread.sleep(500L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
				
			}
		});
		thread1.start();
		
	//new Thread(runnable.run){run}.start();	
		new Thread( 
				new Runnable(){
					@Override
					public void run() {
						while(true){
							try {
								Thread.sleep(500L);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("runable:"+Thread.currentThread().getName());
						}
						
						
					}
				}
				){
			public void run() {
				while(true){
					try {
						Thread.sleep(500L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("thread:"+Thread.currentThread().getName());
				}
				
			};
		}.start();
		
	}

}
