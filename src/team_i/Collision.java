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
					while (true) {
						double face = bullet.getX() + bullet.getW();
						double foot = bullet.getY() + bullet.getH();
						//player의 총알이 item에 맞으면
						if(item.imgList.size()> 0) {
							for(int i = 0; i < item.imgList.size(); i++) {
								
								if(
										item.imgList.get(i).getX() + item.imgList.get(i).getImage().getWidth(view) >= bullet.getX()
										&& item.imgList.get(i).getX() + item.imgList.get(i).getImage().getWidth(view) <= face
										&& item.imgList.get(i).getY() + item.imgList.get(i).getImage().getWidth(view) >= bullet.getY()
										&& item.imgList.get(i).getY() + item.imgList.get(i).getImage().getWidth(view) <= foot) {
									System.out.println("hit");
								}
							}
						}
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();
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
