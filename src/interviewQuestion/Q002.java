package interviewQuestion;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 2)��Java��Lock�ӿڱ�synchronized���������ʲô��
 * ����Ҫʵ��һ����Ч�Ļ��棬���������û�������ֻ����һ���û�д���Դ����������������ԣ��������ȥʵ������
 * @author zhouht
 * �Լ��ĵ�һ�뷨��ʹ�ö�д����ReadOnWriteLock
 * ���棺�ڶ�ȡ�͸ı��ʱ�򣬶�ȡ��ʱ�򣬻����иö��󣬲���Ҫ��ѯ��
 * 		��û�л����ʱ�򣬲�ѯ�󷵻أ�һ�������ݴ���������
 */
public class Q002 {
	
	// ��������
	private static HashMap<Integer, Object> cache = new HashMap<Integer ,Object>();
	
	// �߳�ThreadLocal����Ż�������
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
	// ������ ����������д������Ч���ܲ����ķ��ʣ������̴߳�������
	// ���������ƴ�������ĸ����������̴߳�������
	// ��д�����ܹ����в�������
	final static class Cache {
		// ˽�й���
		//private Cache(){}
		// �ṩ��ö���ķ���
		public Cache getInstance(String key){
			// ����Ҫʹ������ʽ�����ʹ�ö���ʽ�������ж�cache���Ƿ��ж��󣬺������߳�ȥ��������
			// 1���ж��߳����Ƿ��ж����Ƿ�Ϊ��
			
			return null;
		}
		
		// ������д��
		ReadWriteLock rwl = new ReentrantReadWriteLock();
		
		public Object getCache(int key){
			Object object = cache.get(key);
			rwl.readLock().lock();
			Random r = new Random();
			try {
				// ����գ���ô�Ϳ���д��
				if (object == null) {
					// �ͷŶ���
					try {
						rwl.readLock().unlock();
						// ����д��
						rwl.writeLock().lock();
						// д������\
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


