package team_i;

public class Timer {

	long time;
	static long count;

	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						if(count % 3 == 0)
							Enemy.nomal = 3;
						if(count % 3 == 0)
							Enemy.hard  =5;
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(count + "√ ");
					count++;
				}
			}
		}).start();
	}
}
