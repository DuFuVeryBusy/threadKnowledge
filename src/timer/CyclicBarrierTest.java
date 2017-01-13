package timer;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * ��ʾ��ұ˴˵ȴ�����Ҽ��Ϻú�ſ�ʼ��������ɢ�������ָ���ص㼯�����棬
 * ��ͺñ�������˾����Ա������ĩʱ�伯�彼��һ�����ȸ��ԴӼҳ�������˾���Ϻ�
 * ��ͬʱ��������԰���棬��ָ���ص㼯�Ϻ���ͬʱ��ʼ�Ͳͣ�����
 * @author zhouht
 * ����ʹ��4���߳���3���̵߳ȴ��Ľ��
 * CyclicBarrier���3����ִ�У�4���߳���ͬ�Ⱦ����Ĺ�ƽ�ԣ���һ��ʵ������
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		
		//java�̳߳�
		ExecutorService service = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�1����ǰ����" + (cb.getNumberWaiting()+1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));						
						cb.await();
						
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�2����ǰ����" + (cb.getNumberWaiting()+1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();	
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�3����ǰ����" + (cb.getNumberWaiting() + 1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));						
						cb.await();						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}