package interviewQuestion;
/**
 * 1)现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 * 这个线程问题通常会在第一轮或电话面试阶段被问到，目的是检测你对”join”方法是否熟悉。
 * 这个多线程问题比较简单，可以用join方法实现。
 * @author zhouht
 * join() :Waits for this thread to die.	void
 * interrupt() :Interrupts this thread.		void
 * interrupted() :Tests whether the current thread has been interrupted.	boolean
 */
public class Q001 {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new T1(),"T1");
		Thread t2 = new Thread(new T2(),"T2");
		Thread t3 = new Thread(new T3(),"T3");
		
		try {
			// 查看join底层实现，使用synchronized 和wait实现，是线程阻塞的，顾代码这个这样实现
			t1.start();
			t1.join();
			
			t2.start();
			t2.join();
			
			t3.start();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class T1 implements Runnable {
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"--"+i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class T2 implements Runnable {
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"--"+i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class T3 implements Runnable {
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"--"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * 
 *     public static void main(String[] args)
    {
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("t1");
            }
        });
        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //引用t1线程，等待t1线程执行完
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2");
            }
        });
        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //引用t2线程，等待t2线程执行完
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3");
            }
        });
        t3.start();
        t2.start();
        t1.start();
    }
 * 
 * 
 * 
 * 
 * 
 */

