package timer;

/**
 * 
 * @author zhouht �ֳ�ͨѶ����
 * ���߳�ѭ��10�Σ��������߳�ѭ��100�Σ������ֵ����߳�ѭ��10�Σ������ٻص����߳�ѭ��100�Σ����ѭ��50��
 */
public class TraditionalThreadCommunication {

	public static void main(String[] args) {
		
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						for (int j = 0; j < 50; j++) {
							
							for (int i = 0; i < 100; i++) {
								System.out.println("sub thread sequece " +i);
							}
						}
						
					}
				}
				).start();
		new Thread().start();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		
		
		
	}
}
