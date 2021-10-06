package test;

import java.awt.Graphics;
import java.awt.Point;

public class Bullet {
	Point pos; //��ġ
	Point vel; //������ ��
	public Bullet(Point p, Point v) {
		pos = p;
		vel = v;
	}
	public void move() {
		pos.x += 5;
		pos.y += 5;
	}
	
	public Point getPos() {
		return pos;
	}
	
	public void draw(Graphics g) {
		g.drawOval(vel.x -pos.x, vel.y-pos.y, 5,5);
	}
}
