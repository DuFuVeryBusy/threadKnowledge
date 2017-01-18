package interviewQuestion;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 2)在Java中Lock接口比synchronized块的优势是什么？
 * 你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它？
 * @author zhouht
 * 自己的第一想法：使用读写锁，ReadOnWriteLock
 * 缓存：在读取和改变的时候，读取的时候，缓存有该对象，不需要查询；
 * 		在没有缓存的时候，查询后返回，一并将数据存入容器中
 */
public class Q002 {
	
	// 缓存容器
	private static HashMap<Integer, Object> cache = new HashMap<Integer ,Object>();
	
	// 线程ThreadLocal，存放缓存数据
	// private ThreadLocal threadLocal = new ThreadLocal();
	
	public static void main(String[] args) {
		final Cache cache2 = new Cache();
		for(int i = 0 ; i<5; i++){
			Random r = new Random();
			final int c = r.nextInt(5);
			new Thread(
					new Runnable(){
						@Override
						public void run() {
							cache2.getCache(c);
						}
					}
				).start();
		}
		
		
		
	}
	// 缓存类 ，单例，读写锁，高效的能并发的访问，更具线程创建对象
	// 单例：控制创建对象的个数，根据线程创建对象
	// 读写锁：能够进行并发访问
	final static class Cache {
		// 私有构造
		//private Cache(){}
		// 提供获得对象的方法
		public Cache getInstance(String key){
			// 这里要使用懒汉式，如果使用饿汉式，不会判断cache中是否有对象，和依据线程去创建对象
			// 1先判断线程中是否含有对象，是否为空
			
			return null;
		}
		
		// 声明读写锁
		ReadWriteLock rwl = new ReentrantReadWriteLock();
		
		public Object getCache(int key){
			Object object = cache.get(key);
			rwl.readLock().lock();
			Random r = new Random();
			try {
				// 如果空，那么就开启写锁
				if (object == null) {
					// 释放读锁
					try {
						rwl.readLock().unlock();
						// 开启写锁
						rwl.writeLock().lock();
						// 写入数据\
						if(object == null ){
							rwl.readLock().lock();
							cache.put(key, r.nextInt(10));
							System.out.println(cache);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						rwl.writeLock().unlock();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				rwl.readLock().unlock();
			}
			
			return object;
		}
		
		
	}
}


