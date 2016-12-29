package timer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
 * ��ThreadLocal�д������
 * ��Ϊ����ģʽ����Ҫ����:����ʵ������
 * ������ʽ�����ֻ����һ��ʵ�����˲�������ʽ����ʵ���ĸ�����ʵ���Ĵ������߳�һһ��Ӧ
 */
public class ThreadLocalTest {



	private static Map<Thread,Integer> threadData = new HashMap<Thread,Integer>();
	private static ThreadLocal<MyThreadScopeData> myThreadScopeData = new ThreadLocal<MyThreadScopeData>();
	public static void main(String[] args) {
		
		for(int i = 0 ;i < 2 ; i++){
			new Thread(new Runnable(){
	
				@Override
				public void run() {
					int data = new Random().nextInt();
					
					System.out.println(Thread.currentThread().getName() + " has put data :" + data);
					threadData.put(Thread.currentThread(), data);
					
					
					
					//new A().getDate();
					//new B().getDate();
				}
				
			}).start();
		}
		
	}

	
	static class A{
		
		public void getDate(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A from  "+Thread.currentThread().getName() + " has put data :" + data);
			
		}
		
	}

	static class B{
		public void getDate(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("B from  "+Thread.currentThread().getName() + " has put data :" + data);
		}
	}

}
/*
 * ʵ��
 */
class MyThreadScopeData{
	
	private String name;
	private int age;
	
	private MyThreadScopeData(){}
	
	// ����ʽ
	public static synchronized MyThreadScopeData getThreadInstance(){
		
		if (instance==null) {
			instance = new MyThreadScopeData();
		}
		return instance;
		
	}
	// �ʹ���һ�����󣬶�������̵߳�ʱ��Ҳ��һ�������ֲ��ʺ�
//	private static MyThreadScopeData instance= new MyThreadScopeData();
	// �˸ĳ�����ʽ
	private static MyThreadScopeData instance= null;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

