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
				System.out.println(count+"초");
				count++;
			}
				}
		}).start();
	}

	// 시간 제어 함수: 이 함수를 사용하면 
		// 특정 시간 간격이 지났을 때만 어떠한 기능을 수행하게 할 수 있음
		// 매개변수: delay(기능을 실행할 시간 주기, 즉 딜레이. 단위: 초)
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
