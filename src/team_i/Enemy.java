
package team_i;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class Enemy {
	private Image image;
	private int type;
	private int x;
	private int y;
	private int alpha;
	private double dAngle;
	static ArrayList<Enemy> imgList = new ArrayList<>(100);
	View view;
	private long prevtime = 0;
	public Enemy(Image image, int type, int x, int y, int alpha, double dAngle) {
		this.dAngle = dAngle;
		this.image = image;
		this.type = type;
		this.alpha = alpha;
		this.x = x;
		this.y = y;
	}
	
	class test1{
		ImageIcon enemyIc1 = new ImageIcon("image/Enemy.png");
		Image enemy1 = enemyIc1.getImage();
	}

	public void enemySetting() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		test1 test1 = new test1();
		//특정 시간 마다 생성
			imgList.add(new Enemy(test1.enemy1, 0, 1200 + rand.nextInt(200), rand.nextInt(800), 255, dAngle));
			imgList.add(new Enemy(test1.enemy1, 0, 1200 + rand.nextInt(200), rand.nextInt(800), 255, dAngle));
			imgList.add(new Enemy(test1.enemy1, 0, 1200 + rand.nextInt(200), rand.nextInt(800), 255, dAngle));
	}
	public void initEnemy(int index) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		imgList.get(index).setX(1200+rand.nextInt(200));
		imgList.get(index).setY(rand.nextInt(800));
		imgList.get(index).setType(rand.nextInt(2));
	}
	
	public void enemyDraw(Graphics g) {
		for (int i = 0; i < imgList.size(); i++) {
			g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), null);
	
		}
	}
	public void moveEnemy() {
		for (int i = 0; i< imgList.size(); i++) {
			imgList.get(i).setX(imgList.get(i).getX()-3);
			if (imgList.get(i).move() == false)// 화면을 벗어나면 삭제 하기
			{
				initEnemy(i);
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
//	public Image setImage(int type) {
//		
//	}
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
	public int getAlpha() {
		return alpha;
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
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	
}