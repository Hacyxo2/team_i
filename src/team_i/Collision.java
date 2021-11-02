package team_i;

public class Collision {
	Audio hitSound = new Audio("audio/hit.wav", false);// 총알과 구조물이 피격시
	Audio hitSound2 = new Audio("audio/playerhit.wav", false); // 플레어어와 구조들이 피격시
	Audio itemCollect = new Audio("audio/itemcollect.wav", false);
	BackMove bm;
	public void collision(BackMove bm) {
		this.bm = bm;
		new Thread(new Runnable() {
			@Override
			public void run() {	
				// TODO Auto-generated method stub
				try {
					playerCollision();
					bulletCollision();
					enemyCollision();
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	private void playerCollision() {
		for (int i = 0; i < Item.imgList.size(); i++) {
			// 아이템이 플레이어에 닿으면 사라짐
			if (hit(View.player[0].getX(), View.player[0].getY(), Item.imgList.get(i).getX(),
					 Item.imgList.get(i).getY(), View.player[0].getImage().getWidth(null),
					View.player[0].getImage().getHeight(null),  Item.imgList.get(i).getImage().getHeight(null),
					 Item.imgList.get(i).getImage().getWidth(null))) {
				System.out.println( Item.imgList.get(i).getType());
				Item.itemEffect(Item.imgList.get(i).getType());
				Item.initItem(i);
				itemCollect.start();
				View.player[0].setScore(50);
			}
		}
		for (int i = 0; i < Enemy.imgList.size(); i++) {
			// 적이 플레이어에 닿으면 사라짐
			if (hit(View.player[0].getX(), View.player[0].getY(), Enemy.imgList.get(i).getX(),
					Enemy.imgList.get(i).getY(), View.player[0].getImage().getWidth(null),
					View.player[0].getImage().getHeight(null), Enemy.imgList.get(i).getImage().getHeight(null),
					Enemy.imgList.get(i).getImage().getWidth(null))) {
				View.player[0].setHp(-10);
				Enemy.imgList.remove(i);
				hitSound2.start();

			}
		}
		
	}

	private void enemyCollision() {
		for (int j = 0; j < EBullet.bullets.size(); j++) {
			if (hit(View.player[0].getX(), View.player[0].getY(), (int) EBullet.bullets.get(j).getX(), 
					(int) EBullet.bullets.get(j).getY(), View.player[0].getImage().getWidth(null), View.player[0].getImage().getHeight(null),
					EBullet.bullets.get(j).getW(), EBullet.bullets.get(j).getH())) {
				View.player[0].setHp(-10);
				bm.fadeOutRed();
				EBullet.bullets.remove(j);
				hitSound2.start();
			}
		}
	}

	private void bulletCollision() {
		// 총알이 아이템을 맞추면 아이템이 사라짐, 총알도 사라짐
		for (int i = 0; i < Item.imgList.size(); i++) {
			for (int j = 0; j < Bullet.bullets.size(); j++) {
				if (hit((int) Bullet.bullets.get(j).getX(), (int) Bullet.bullets.get(j).getY(),
						Item.imgList.get(i).getX(), Item.imgList.get(i).getY(), Bullet.bullets.get(j).getW(),
						Bullet.bullets.get(j).getH(), Item.imgList.get(i).getImage().getHeight(null),
						Item.imgList.get(i).getImage().getWidth(null))) {
					Item.initItem(i);
					View.player[0].setScore(50);
					Item.itemEffect(Item.imgList.get(i).getType());
					Bullet.bullets.remove(j);
					itemCollect.start();

				}
			}
		}
		// 총알이 적을 맞추면 적이 사라짐, 총알도 사라짐
		for (int i = 0; i < Enemy.imgList.size(); i++) {
			for (int j = 0; j <Bullet.bullets.size(); j++) {
				if (hit((int)Bullet.bullets.get(j).getX(), (int)Bullet.bullets.get(j).getY(),
						Enemy.imgList.get(i).getX(), Enemy.imgList.get(i).getY(), 
						Bullet.bullets.get(j).getW(),Bullet.bullets.get(j).getH(),
						Enemy.imgList.get(i).getImage().getHeight(null), Enemy.imgList.get(i).getImage().getWidth(null))) {
					View.player[0].setScore(50);
					Enemy.imgList.remove(i);
					Bullet.bullets.remove(j);
					hitSound.start();

				}
			}
		}
	}
	static boolean hit(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2) {
		boolean result = false;
		if (Math.abs((x1 + w1 / 2) - (x2 + w2 / 2)) < (w2 / 2 + w1 / 2)
				&& Math.abs((y1 + h1 / 2) - (y2 + h2 / 2)) < (h2 / 2 + h1 / 2))
			result = true;
		else
			result = false;
		return result;
	}
}
