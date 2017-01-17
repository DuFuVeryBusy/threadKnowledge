package interviewQuestion;
/**
 * 1)������T1��T2��T3�����̣߳���������֤T2��T1ִ�����ִ�У�T3��T2ִ�����ִ�У�
 * ����߳�����ͨ�����ڵ�һ�ֻ�绰���Խ׶α��ʵ���Ŀ���Ǽ����ԡ�join�������Ƿ���Ϥ��
 * ������߳�����Ƚϼ򵥣�������join����ʵ�֡�
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
			// �鿴join�ײ�ʵ�֣�ʹ��synchronized ��waitʵ�֣����߳������ģ��˴����������ʵ��
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
                    //����t1�̣߳��ȴ�t1�߳�ִ����
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
                    //����t2�̣߳��ȴ�t2�߳�ִ����
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

