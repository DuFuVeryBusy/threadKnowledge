package timer;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableAndFuture {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		
		Future<String> future = threadPool.submit(
			new Callable<String>() {

				@Override
				public String call() throws Exception {

					return "hello world";
				}
				
			}
		);
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		// 使用CompletionService监控一组callback对象，查看结果
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		
		for (int i = 0; i < 10; i++) {
			final int sqe = i;
			completionService.submit(new Callable<Integer>() {
	
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return sqe;
				}
			});
		}
		
		for(int i=0;i<10;i++){
			try {
				System.out.println(
						completionService.take().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}
		
	
	
}
