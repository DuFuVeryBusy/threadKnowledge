package timer;

/**
 * 设计四个线程，其中两个每次对j加1，另外两个线程对j每次减去1。
 * @author zhouht
 *
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		
		/*	
		 * 方案二
		 * 将共享数据封装在另外一个对象中，然后将这个对象逐一传递给各个Runnable对象。
		 * 每个线程对共享数据的操作方法也分配到那个对象身上去完成，这样容易实现针对该数据进行的各个操作的互斥和通信
		 * 将共享数据，传递给你两个runnbale对象
		 */
		ShareData1 data2 = new ShareData1();
		new Thread(new MyRunnable1(data2)).start();
		new Thread(new MyRunnable2(data2)).start();
		
		
		/*
		 * 两个runnable去取这个共享对象的数据
		 */
		final ShareData1 data1 = new ShareData1();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.increment();
			}}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.decrement();
			}}).start();
		
		
	}
}
//操作共享数据
class ShareData1 {
	private int j;
	// 对数据进行加操作
	public synchronized void increment(){
		j++;
	}
	
	
	//	对数据进行减操作
	public synchronized void decrement(){
		j--;
	}
}

/*	
 * 方案二
 * 将共享数据封装在另外一个对象中，然后将这个对象逐一传递给各个Runnable对象。
 * 每个线程对共享数据的操作方法也分配到那个对象身上去完成，这样容易实现针对该数据进行的各个操作的互斥和通信
 */
class MyRunnable1 implements Runnable {
	private ShareData1 data;
	public MyRunnable1(ShareData1 data) {
		this.data = data;
	}
	@Override
	public void run() {
		data.increment();
	}
}
class MyRunnable2 implements Runnable {
	private ShareData1 data;
	public MyRunnable2(ShareData1 data) {
		this.data = data;
	}
	@Override
	public void run() {
		data.decrement();
	}
}
	
