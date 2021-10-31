package team_i;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class EBullet {
	private double x;
	private double y;
	private double vx;
	private double vy;
	private int index;
	private int w = 40;
	private int h = 20;
	private double bulletSpeed = 4;
	private double dAngle;
	private Image image;
	static ArrayList<EBullet>bullets =new ArrayList<EBullet>(100);
	boolean isPress = false;
	Color color = Color.cyan;
	long prevtime = 0;
	
	public EBullet (int index, double x, double y, double vx, double vy, double dAngle) {
		this.index = index;
		this.dAngle = dAngle;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		try {
			image = ImageIO.read(new File("image/bullet.png"));
			image = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void bulletDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		for (EBullet b : bullets)// 총알 그리기
		{
			g2.rotate(Math.toRadians(b.dAngle), b.getCenterH(), b.getCenterW());
			
			g2.drawImage(image, (int) b.getX(), (int) b.getY(), b.getW(), b.getH(), null);
			g2.setTransform(old);
		}
	}
	public void moveBullet() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).move() == false)// 화면을 벗어나면 삭제 하기
			{
				bullets.remove(i);
				break;
			}
		}
	}

	public boolean move() {
		x += vx;
		y += vy;
		if(x < 0 || x > Const.gamePan_W || y < 0 || y > Const.gamePan_H) {
			x = 0;
			y = 0;
			return false;				
		}
		return true;
	}
		
	public void bulletProcess() {

		if (System.currentTimeMillis() - prevtime > 1000) {
			
			for (int i = 0; i < Enemy.imgList.size(); i++) {
				double x1 = Enemy.imgList.get(i).point().x;
				double y1 = Enemy.imgList.get(i).point().y;
				double x2 = View.player[0].point().x;
				double y2 = View.player[0].point().y;
				double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
				double vx = (x2 - x1) / d * (bulletSpeed+1);
				double vy = (y2 - y1) / d * (bulletSpeed+1);
				dAngle = getAngle(Enemy.imgList.get(i).point(), View.player[0].point());
				EBullet b = new EBullet(i,x1, y1, vx, vy, dAngle);
				bullets.add(b);
				
				prevtime = System.currentTimeMillis();
			}
		}
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getBulletSpeed() {
		return bulletSpeed;
	}
	public int getCenterH() {
		return (int) x + h/2;
	}
	public int getCenterW() {
		return (int) y + w/2;
	}
	public int getH() {
		return h;
	}
	public int getW() {
		return w;
	}
	
	public ArrayList<EBullet> getBullets() {
		return bullets;
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
	public void setColor(int type) {
		if(type == 1){
			this.color = Color.green;
		}
		else
			this.color = Color.MAGENTA;
	}
}
