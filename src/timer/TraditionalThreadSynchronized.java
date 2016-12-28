package timer;

public class TraditionalThreadSynchronized {

	public static void main(String[] args) {

		TraditionalThreadSynchronized t = new TraditionalThreadSynchronized();
		t.init();

	}

	private void init() {

		final Outputer o1 = new Outputer();
		final Outputer o2 = new Outputer();

		// 启动线程

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					o1.output1("01234567890123");
				}
			}

		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					o2.output2("01234567890123");
				}
			}

		}).start();

	}

	/*
	 * 输出类，打印输出
	 */
	class Outputer {
		// 线程不安全
		public void output(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}

		// thread safe
		public synchronized void output1(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}

		// thread safe
		public synchronized void output2(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}

	}

}
