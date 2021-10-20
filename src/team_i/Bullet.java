package team_i;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Bullet implements MouseListener, MouseMotionListener {
	double x;
	double y;
	double vx;
	double vy;
	double bulletSpeed;
	
	ArrayList<Bullet>bullets =new ArrayList<Bullet>();
	static Point mouse = new Point(0, 0);
	boolean isPress = false;
	long prevtime = 0;
	
	public Bullet(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	public boolean move() {
		x += vx;
		y += vy;
		if(x < 0 || x > Const.gamePan_W || y < 0 || y > Const.gamePan_H) {
			return false;				
		}
		return true;
	}
	public static void mouseDraw(Graphics g) {
		g.setColor(Color.magenta);
		g.drawLine(mouse.x - 5, mouse.y, mouse.x + 5, mouse.y);
		g.drawLine(mouse.x, mouse.y - 5, mouse.x, mouse.y + 5);
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
		View.bulletProcess();
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
