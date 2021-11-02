
package team_i;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy {
	private Image image;
	private int type;
	private int x;
	private int y;
	static int nomal = 3;
	static int hard = 5;
	private double dAngle;
	static ArrayList<Enemy> imgList = new ArrayList<>(10);
	View view;
	private long prevtime = 0;
	public Enemy(Image image, int type, int x, int y, double dAngle) {
		this.dAngle = dAngle;
		this.image = image;
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	class test1{
		ImageIcon enemyIc1 = new ImageIcon("image/Enemy.png");
		Image enemy1 = enemyIc1.getImage();
		ImageIcon enemyIc2 = new ImageIcon("image/car.png");
		Image enemy2 = enemyIc1.getImage();
	}

	public void enemySetting() {
		appearTiming();
	}
	
	public void enemyDraw(Graphics g) {
		test1 test1 = new test1();
		for (int i = 0; i < imgList.size(); i++) {
			if(imgList.get(i).type == 0)
				g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), null);
			else if(imgList.get(i).type == 1) {
				imgList.get(i).setImage(test1.enemy2);
				g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), null);
			}
		}
	}
	public void moveEnemy() {
		for (int i = 0; i< imgList.size(); i++) {
			imgList.get(i).setX(imgList.get(i).getX()-3);
			if (imgList.get(i).move() == false)// 화면을 벗어나면 삭제 하기
			{
				imgList.remove(i);
				break;
			}
		}
	}
	public boolean move() {
		if(x < -50 || y < 0 || y > Const.gamePan_H) {
			return false;
		}
		return true;
	}

	public void appearTiming() {
		boolean a = false;
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		test1 test1 = new test1();
		if (Timer.count < 28 && Timer.count % 3 == 0 && nomal != 0) {
			for (int i = 0; i <= nomal; i++) {
				imgList.add(new Enemy(test1.enemy1, 0, 1100, rand.nextInt(800), dAngle));
				nomal--;
			}
		}
		else if (30 < Timer.count || Timer.count <= 88 && Timer.count % 3 == 0 && hard != 0) {
			for (int i = 0; i <= hard; i++) {
				imgList.add(new Enemy(test1.enemy1, 1, 1100, rand.nextInt(800), dAngle));
				hard--;
			}
		}
		else {
		
			}
	}
	public double getAngle(Point start, Point end) {
		double dx = end.x - start.x;
		double dy = end.y - start.y;
		
		if(dy < 0) {
			return Math.atan2(dy, dx)*(180.0 / Math.PI)+ 360;
		}
		else
			return Math.atan2(dy, dx) * (180.0 / Math.PI);
	}
	public Image getImage() {
		return image;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public int getType() {
		return type;
	}
	public int getH() {
		return y + image.getHeight(view)/2;
	}
	public int getW() {
		return x + image.getWidth(view)/2;
	}
	public Point point() {
		Point p = new Point(getW(), getH());

		return p;
	}
	public double enemydAngle(Point x) {
		dAngle = getAngle(x, View.player[0].point());
		return dAngle;
	}
	public void setImage(Image image) {
		this.image = image;
	} 
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setType(int type) {
		this.type = type;
	}
}