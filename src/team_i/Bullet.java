package team_i;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Bullet implements MouseListener, MouseMotionListener {
	private double x;
	private double y;
	private double vx;
	private double vy;
	private int w = 5;
	private int h = 5;
	private int damge = 10;
	private double dAngle;
	static int bulletSpeed = 10;
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private Point mouse = new Point(0, 0);
	boolean isPress = false;
	Color color = Color.cyan;
	long prevtime = 0;
	
	public Bullet(double x, double y, double vx, double vy, double dAngle, int damge) {
		this.dAngle = dAngle;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.damge = damge;
	}
	public void bulletDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		for (Bullet b : bullets)// 총알 그리기
		{
			g2.rotate(Math.toRadians(b.dAngle), b.getX(), b.getY());
			g2.setColor(color);
			g2.drawRect((int) b.getX(), (int) b.getY(), b.getW(), b.getH());
			g2.fillRect((int) b.getX(), (int) b.getY(), b.getW(), b.getH());
			g2.setTransform(old);
		}
	}
	public void mouseDraw(Graphics g) {
		g.setColor(color);
		g.drawLine(mouse.x - 5, mouse.y, mouse.x + 5, mouse.y);
		g.drawLine(mouse.x, mouse.y - 5, mouse.x, mouse.y + 5);
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
		if(System.currentTimeMillis() - prevtime > 300) {
			double x1 = View.player[0].point().x;
			double y1 = View.player[0].point().y;
			double x2 = getMousePointer().x;
			double y2 = getMousePointer().y;
			double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
			double vx = (x2 - x1) / d * bulletSpeed;
			double vy = (y2 - y1) / d * bulletSpeed;
			dAngle = getAngle(View.player[0].point(), getMousePointer());
			Bullet b = new Bullet(x1, y1, vx, vy, dAngle, damge);
			bullets.add(b);
			prevtime = System.currentTimeMillis();
		}
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public Point getMousePointer() {
		return mouse;
	}
	public int getH() {
		return h;
	}
	public int getW() {
		return w;
	}
	public ArrayList<Bullet> getBullets() {
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
	public void setDamge(int damge) {
		this.damge = damge;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mouse = e.getPoint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouse = e.getPoint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		isPress = true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isPress = false;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
