package timer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 模拟cache缓存，面试题
 * 
 * @author zhouht
 *
 */
public class CacheDemo {
	
	private Map<String, Object> cache  = new HashMap<String , Object>();
	// 使用volatile，使“状态标记量”能保证  原子性，可见性，有序性
	// volatile boolean cacheValid;

	
	public static void main(String[] args) {
		
		
	}
	// 获取数据，在缓存中没有情况下，存入数据
	// 使用读写锁
	ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {

		rwl.readLock().lock();
		try {
			Object value = cache.get(key);

			if (value == null) {
				// 判断为空的时候，进行写操作
				// Must release read lock before acquiring write lock
			    rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					// Recheck state because another thread might have
					// acquired write lock and changed state before we did.
					// 这里的if判断，是因为有三个线程都到这里了，但是有一个已经更改完值了，
					// 另外几个就不用更改值了，因为对应的是一个key
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
	 * 使用synchronized 会限制读和写，我们在读取数据的过程中，并不想出现阻塞的状况，
	 * 顾使用读写锁更适合些，只有在写操作的时候，出现线程阻塞
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

