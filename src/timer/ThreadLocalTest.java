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
					// threadData.put(Thread.currentThread(), data);
					
					MyThreadScopeData instance = MyThreadScopeData.getThreadInstance();
					instance.setName("name"+data);
					instance.setAge(data);
					myThreadScopeData.set(instance);
					new A().getDate();
					new B().getDate();
				}
				
			}).start();
		}
		
	}

	
	static class A{
		
		public void getDate(){
			MyThreadScopeData myThreadScopeData2 = myThreadScopeData.get();
			String name = myThreadScopeData2.getName();
			int age = myThreadScopeData2.getAge();
			System.out.println("A from  "+Thread.currentThread().getName() + " has put data :" + name );
			
		}
		
	}

	static class B{
		public void getDate(){
			MyThreadScopeData myThreadScopeData2 = myThreadScopeData.get();
			String name = myThreadScopeData2.getName();
			int age = myThreadScopeData2.getAge();
			System.out.println("B from  "+Thread.currentThread().getName() + " has put data :" + name );
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
	// ��Ϊ�ڲ������ݵ�ʱ���̷߳��룬û�в�����ͬ���ݣ��˲���Ҫsynchronized
	public static /*synchronized*/ MyThreadScopeData getThreadInstance(){
		MyThreadScopeData instance= map.get();
		if (instance==null) {
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
		
	}
	// �ʹ���һ�����󣬶�������̵߳�ʱ��Ҳ��һ�������ֲ��ʺ�
//	private static MyThreadScopeData instance= new MyThreadScopeData();
	// �˸ĳ�����ʽ
	// private static MyThreadScopeData instance= null;
	
	private static ThreadLocal<MyThreadScopeData> map = new  ThreadLocal<MyThreadScopeData>();
	
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

