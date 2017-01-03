package timer;

/**
 * ����ĸ��̣߳���������ÿ�ζ�j��1�����������̶߳�jÿ�μ�ȥ1��
 * @author zhouht
 *
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		
		/*	
		 * ������
		 * ���������ݷ�װ������һ�������У�Ȼ�����������һ���ݸ�����Runnable����
		 * ÿ���̶߳Թ������ݵĲ�������Ҳ���䵽�Ǹ���������ȥ��ɣ���������ʵ����Ը����ݽ��еĸ��������Ļ����ͨ��
		 * ���������ݣ����ݸ�������runnbale����
		 */
		ShareData1 data2 = new ShareData1();
		new Thread(new MyRunnable1(data2)).start();
		new Thread(new MyRunnable2(data2)).start();
		
		
		/*
		 * ����runnableȥȡ���������������
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
//������������
class ShareData1 {
	private int j;
	// �����ݽ��мӲ���
	public synchronized void increment(){
		j++;
	}
	
	
	//	�����ݽ��м�����
	public synchronized void decrement(){
		j--;
	}
}

/*	
 * ������
 * ���������ݷ�װ������һ�������У�Ȼ�����������һ���ݸ�����Runnable����
 * ÿ���̶߳Թ������ݵĲ�������Ҳ���䵽�Ǹ���������ȥ��ɣ���������ʵ����Ը����ݽ��еĸ��������Ļ����ͨ��
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
	
