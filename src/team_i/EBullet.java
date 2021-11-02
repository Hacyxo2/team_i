package team_i;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class EBullet {
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double dAngle;
	private int w = 10;
	private int h = 10;
	static int eBulletSpeed = 4;
	static ArrayList<EBullet>bullets =new ArrayList<EBullet>();
	private Image image;
	boolean isPress = false;
	static Color ebColor = Color.red;
	long prevtime = 0;
	
	public EBullet (double x, double y, double vx, double vy, double dAngle) {
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
			g2.setColor(ebColor);
			g2.rotate(Math.toRadians(b.dAngle), b.getCenterH(), b.getCenterW());
			g2.drawRect((int) b.getX(), (int) b.getY(), b.getW(), b.getH());
			g2.fillRect((int) b.getX(), (int) b.getY(), b.getW(), b.getH());
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
				double vx = (x2 - x1) / d * (eBulletSpeed+1);
				double vy = (y2 - y1) / d * (eBulletSpeed+1);
				dAngle = getAngle(new Point(), View.player[0].point());
				EBullet b = new EBullet(x1, y1, vx, vy, dAngle);
				bullets.add(b);
				prevtime = System.currentTimeMillis();
			}
		}
	}
	static void bossBulletPattern() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		double x = 500;
		double y = 400;
		long prevtime = 0;
		if (System.currentTimeMillis() - prevtime > 1000) {
			EBullet b2 = new EBullet(x, y, 3, 3, 90);
			bullets.add(b2);
			System.out.print(1);
			b2 = new EBullet(x, y, 3, 3, 0);
			bullets.add(b2);
			System.out.print(1);
			b2 = new EBullet(x, y, 3, 3, 180);
			bullets.add(b2);
			System.out.print(1);
			b2 = new EBullet(x, y, 3, 3, 270);
			bullets.add(b2);
			prevtime = System.currentTimeMillis();
			
		}
	}
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
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
}
