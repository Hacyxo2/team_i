package team_i;


public class Timer {
	
	long time;
	static long count;
	
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(count+"��");
				count++;
			}
				}
		}).start();
	}

	// �ð� ���� �Լ�: �� �Լ��� ����ϸ� 
		// Ư�� �ð� ������ ������ ���� ��� ����� �����ϰ� �� �� ����
		// �Ű�����: delay(����� ������ �ð� �ֱ�, �� ������. ����: ��)
		public boolean timeCtrl(long delay) {
			long systemTime =  System.currentTimeMillis();
			if (time + delay * 1000 <= systemTime) {
				time = System.currentTimeMillis();
				return true;
			}
			else {
				return false;
			}
		}
		private int[] emdwkd = {100, 2000, 60000, 65000, 2000000, 2000200};
		
		public boolean enemyTiming() {
			boolean a = true;
			long systemTime =  System.currentTimeMillis();
			if(time + 10 * 1000 <= systemTime) {
				System.out.println(count);
				count++;
				return a;
			}
			return a;
		}

}
