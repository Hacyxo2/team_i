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
					playerCollision();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
	}
	private void playerCollision() {
		for(int i =0; i < item.imgList.size(); i++) {
			//아이템이 플레이어에 닿으면 사라짐
			if(Crash(View.player[0].getX(), View.player[0].getY(),
					item.imgList.get(i).getX(), item.imgList.get(i).getY(), 
					View.player[0].getImage().getWidth(view), View.player[0].getImage().getHeight(view),
					item.imgList.get(i).getImage().getHeight(view), item.imgList.get(i).getImage().getWidth(view)))
				item.imgList.remove(i);
			//총알이 아이템을 맞추면 아이템이 사라짐, 총알도 사라짐
			for(int j = 0; j < bullet.getBullets().size(); j++) {
				if(Crash((int)bullet.getBullets().get(j).getX(), (int)bullet.getBullets().get(j).getY(), 
						item.imgList.get(i).getX(), item.imgList.get(i).getY(),
						bullet.getBullets().get(j).getW(), bullet.getBullets().get(j).getH(),
						item.imgList.get(i).getImage().getHeight(view), item.imgList.get(i).getImage().getWidth(view))) {
					item.imgList.remove(i);
					bullet.getBullets().remove(j);
				}
			}
		}
		
	}
	
	static boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2) {
		boolean result = false;
		if(Math.abs((x1 + w1 / 2) -(x2 + w2 / 2)) < (w2 / 2 + w1 / 2) && Math.abs((y1 + h1 / 2) - (y2 + h2 / 2 )) < (h2 / 2 + h1 / 2))
			result = true;
		else result = false;
		return result;
	}
}
