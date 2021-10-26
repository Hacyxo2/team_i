package team_i;


public class Collision {
	private View view;
	private Item item;
	private Bullet bullet;
	public void collision(View view, Item item, Bullet bullet) {
		this.view = view;
		this.item = item;
		this.bullet = bullet;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					for(int j =0; j < item.imgList.size(); j++) {
						if(Crash(view.player[0].getX(), view.player[0].getY(), item.imgList.get(j).getX(), item.imgList.get(j).getY()
								, view.player[0].getImage().getWidth(view),view.player[0].getImage().getHeight(view),
								item.imgList.get(j).getImage().getHeight(view), item.imgList.get(j).getImage().getWidth(view))){
							item.imgList.remove(j);	
						}
					}
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}
	static boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2) {
		boolean result = false;
		if(Math.abs((x1 + w1 / 2) -(x2 + w2 / 2)) < (w2 / 2 + w1 / 2) && Math.abs((y1 + h1 / 2) - (y2 + h2 / 2 )) < (h2 / 2 + h1 / 2))
			result = true;
		else result = false;
		return result;
	}
	
	public void hit(int i) {
		int index = i;
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				item.imgList.remove(index);
			}
			}).start();
	}
}
