package timer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ģ��cache���棬������
 * 
 * @author zhouht
 *
 */
public class CacheDemo {
	
	private Map<String, Object> cache  = new HashMap<String , Object>();
	// ʹ��volatile��ʹ��״̬��������ܱ�֤  ԭ���ԣ��ɼ��ԣ�������
	// volatile boolean cacheValid;

	
	public static void main(String[] args) {
		
		
	}
	// ��ȡ���ݣ��ڻ�����û������£���������
	// ʹ�ö�д��
	ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {

		rwl.readLock().lock();
		try {
			Object value = cache.get(key);

			if (value == null) {
				// �ж�Ϊ�յ�ʱ�򣬽���д����
				// Must release read lock before acquiring write lock
			    rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					// Recheck state because another thread might have
					// acquired write lock and changed state before we did.
					// �����if�жϣ�����Ϊ�������̶߳��������ˣ�������һ���Ѿ�������ֵ�ˣ�
					// ���⼸���Ͳ��ø���ֵ�ˣ���Ϊ��Ӧ����һ��key
					if(value == null){
						value = "asdfasd";
					}
					// Downgrade by acquiring read lock before releasing write lock
			        rwl.readLock().lock();
				}finally{
					rwl.writeLock().unlock();
				}
			}
			return value;
		} finally {
			rwl.readLock().unlock();
		}
	}
}
	/*
	 * ʹ��synchronized �����ƶ���д�������ڶ�ȡ���ݵĹ����У����������������״����
	 * ��ʹ�ö�д�����ʺ�Щ��ֻ����д������ʱ�򣬳����߳�����
	public synchronized Object getData(String key){
		Object value = cache.get(key);
		if (value == null) {
			value  = "asdfasd";
		}
		return null;
	}*/
	
/*
class CachedData {
   Object data;
   volatile boolean cacheValid;
   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

   void processCachedData() {
     rwl.readLock().lock();
     if (!cacheValid) {
       // Must release read lock before acquiring write lock
       rwl.readLock().unlock();
       rwl.writeLock().lock();
       try {
         // Recheck state because another thread might have
         // acquired write lock and changed state before we did.
         if (!cacheValid) {
           data = ...
           cacheValid = true;
         }
         // Downgrade by acquiring read lock before releasing write lock
         rwl.readLock().lock();
       } finally {
         rwl.writeLock().unlock(); // Unlock write, still hold read
       }
     }

     try {
       use(data);
     } finally {
       rwl.readLock().unlock();
     }
   }
 }
	 */

