package team_i;

public class Collision {
	private Item item;
	private Bullet bullet;
	private Enemy enemy;
	private BackMove bm;
	private Audio hitSound;
	private Audio hitSound2;
	public void collision( Item item, Bullet bullet, Enemy enemy, BackMove bm) {
		this.item = item;
		this.bm = bm;
		this.bullet = bullet;
		this.enemy = enemy;
		hitSound = new Audio("audio/hit.wav", false);// 총알과 구조물이 피격시
		hitSound2 = new Audio("audio/playerhit.wav", false); // 플레어어와 구조들이 피격시
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
		for (int i = 0; i < item.imgList.size(); i++) {
			// 아이템이 플레이어에 닿으면 사라짐
			if (hit(View.player[0].getX(), View.player[0].getY(), item.imgList.get(i).getX(),
					item.imgList.get(i).getY(), View.player[0].getImage().getWidth(null),
					View.player[0].getImage().getHeight(null), item.imgList.get(i).getImage().getHeight(null),
					item.imgList.get(i).getImage().getWidth(null))) {
				System.out.println(item.imgList.get(i).getType());
				item.itemEffect(item.imgList.get(i).getType());
				item.initItem(i);
				bm.fadeOut();
				View.player[0].setScore(50);
				hitSound2.start();
			}
		}
		for (int i = 0; i < Enemy.imgList.size(); i++) {
			// 적이 플레이어에 닿으면 사라짐
			if (hit(View.player[0].getX(), View.player[0].getY(), Enemy.imgList.get(i).getX(),
					Enemy.imgList.get(i).getY(), View.player[0].getImage().getWidth(null),
					View.player[0].getImage().getHeight(null), Enemy.imgList.get(i).getImage().getHeight(null),
					Enemy.imgList.get(i).getImage().getWidth(null))) {
				View.player[0].setHp(-50);
				enemy.initEnemy(i);
				hitSound2.start();
			}
		}
		
	}

	private void enemyCollision() {
		for (int j = 0; j < EBullet.bullets.size(); j++) {
			if (hit(View.player[0].getX(), View.player[0].getY(), (int) EBullet.bullets.get(j).getX(), 
					(int) EBullet.bullets.get(j).getY(), View.player[0].getImage().getWidth(null), View.player[0].getImage().getHeight(null),
					EBullet.bullets.get(j).getW(), EBullet.bullets.get(j).getH())) {
				View.player[0].setHp(-30);
				bm.fadeOut();
				EBullet.bullets.remove(j);
				hitSound2.start();
			}
		}
	}

	private void bulletCollision() {
		// 총알이 아이템을 맞추면 아이템이 사라짐, 총알도 사라짐
		for (int i = 0; i < item.imgList.size(); i++) {
			for (int j = 0; j < bullet.getBullets().size(); j++) {
				if (hit((int) bullet.getBullets().get(j).getX(), (int) bullet.getBullets().get(j).getY(),
						item.imgList.get(i).getX(), item.imgList.get(i).getY(), bullet.getBullets().get(j).getW(),
						bullet.getBullets().get(j).getH(), item.imgList.get(i).getImage().getHeight(null),
						item.imgList.get(i).getImage().getWidth(null))) {
					item.initItem(i);
					System.out.println(i);
					View.player[0].setScore(50);
					item.itemEffect(item.imgList.get(i).getType());
					bullet.getBullets().remove(j);
					hitSound.start();
				}
			}
		}
		// 총알이 적을 맞추면 적이 사라짐, 총알도 사라짐
		for (int i = 0; i < Enemy.imgList.size(); i++) {
			for (int j = 0; j < bullet.getBullets().size(); j++) {
				if (hit((int) bullet.getBullets().get(j).getX(), (int) bullet.getBullets().get(j).getY(),
						Enemy.imgList.get(i).getX(), Enemy.imgList.get(i).getY(), 
						bullet.getBullets().get(j).getW(), bullet.getBullets().get(j).getH(),
						Enemy.imgList.get(i).getImage().getHeight(null), Enemy.imgList.get(i).getImage().getWidth(null))) {
					View.player[0].setScore(50);
					enemy.initEnemy(i);
					bullet.getBullets().remove(j);
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
