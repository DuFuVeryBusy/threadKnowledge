package timer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
 * 像ThreadLocal中存入对象
 * 因为单例模式的主要功能:控制实例个数
 * 而饿汉式，大多只能有一个实例，顾采用懒汉式控制实例的个数，实例的创建和线程一一对应
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
 * 实例
 */
class MyThreadScopeData{
	
	private String name;
	private int age;
	
	private MyThreadScopeData(){}
	
	// 懒汉式
	// 因为在操作数据的时候，线程分离，没有操作共同数据，顾不需要synchronized
	public static /*synchronized*/ MyThreadScopeData getThreadInstance(){
		MyThreadScopeData instance= map.get();
		if (instance==null) {
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
		
	}
	// 就创建一个对象，而来多个线程的时候，也是一个，这种不适合
//	private static MyThreadScopeData instance= new MyThreadScopeData();
	// 顾改成懒汉式
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

