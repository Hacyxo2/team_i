package team_i;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class View extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Player player[] = new Player[1];
	static Bullet bullet = new Bullet(0,0,0,0);


	double dAngle;
	static int bulletSpeed = 5;
	static long prevtime = 0;
	
	//배경, 아이템
	BackMove bm = new BackMove();
	//내부 클래스 접근 방식
	Item.test1 test1 = new Item(null,0,0,0).new test1();
	Item item = new Item(null,0,0,0);
	
	
	private Graphics bufferGraphics; //버퍼
	private Image offscreen; // 버퍼

	private int board[][];
	Thread th;
	
	public View() {
		player[0] = new Player(this);
		board = new int[Const.gamePan_W][Const.gamePan_H];
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		
		item.imgList.add(new Item(test1.item1, 0, rand.nextInt(1000), rand.nextInt(800)));
		item.imgList.add(new Item(test1.item1, 0, rand.nextInt(1000), rand.nextInt(800)));
		item.imgList.add(new Item(test1.item1, 0, rand.nextInt(1000), rand.nextInt(800)));
		
		th = new Thread(this);
		th.start();
		addKeyListener(player[0]);
		addMouseMotionListener(bullet);
		addMouseListener(bullet);
	}
	public int[][] getBoard(){
		return board;
	}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			try {
				while(true) {
					//배경 2개 왼쪽으로 사라짐
					bm.back1X--;
					bm.back2X--;
					if(bullet.isPress) {
						bulletProcess();
					}
					moveBullet();
					moveItem();
					for (int i = 0;i < item.imgList.size(); i++) {
						item.imgList.get(i).setX(item.imgList.get(i).getX());
					}
					if(bm.back1X < -(bm.backImg.getWidth(null)-2)) {
						bm.back1X = bm.backImg.getWidth(null);
					}
					if(bm.back2X < -(bm.backImg.getWidth(null)-2)) {
						bm.back2X = bm.backImg.getWidth(null);
					}
					dAngle = getAngle(player[0].point(), Bullet.mouse);
					player[0].KeyProcess();
					repaint();
					Thread.sleep(10);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		offscreen = createImage(Const.gamePan_W, Const.gamePan_H);
		bufferGraphics = offscreen.getGraphics();
		update(g);
	}
	
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		render(bufferGraphics);
		g.drawImage(offscreen, 0, 0, this);
	}
	
	public void render(Graphics g) {
		g.clearRect(0, 0, Const.gamePan_W, Const.gamePan_H);
		background(g);
		Graphics2D g2 = (Graphics2D) g;
		
		AffineTransform old = g2.getTransform();
		
		g2.rotate(Math.toRadians(dAngle), player[0].getCenterH(), player[0].getCenterW());
		g2.drawImage(player[0].getImage(), player[0].getX(), player[0].getY(), this);
		g2.setTransform(old);
		for (Bullet b : bullet.bullets)// 총알 그리기
		{
			g.setColor(Color.cyan);
			g.drawRect((int)b.x, (int)b.y, 10, 5);
			g.fillRect((int)b.x, (int)b.y, 10, 5);
		}
	}
	public void background(Graphics g) {
		g.drawImage(bm.backImg, bm.back1X, 0, this);
		g.drawImage(bm.backImg2, bm.back2X, 0, this);
		for (int i = 0; i < item.imgList.size(); i++) {
			g.drawImage(item.imgList.get(i).getImage(), item.imgList.get(i).getX(), item.imgList.get(i).getY(), this);
			System.out.println(item.imgList.get(i).getX());
		}
	}
	
	public void moveBullet() {
		for (int i = 0; i < bullet.bullets.size() ; i++) {
			if (bullet.bullets.get(i).move() == false)// 화면을 벗어나면 삭제 하기
			{
				bullet.bullets.remove(i);
				break;
			}
		}
	}
	public void moveItem() {
		for (int i =0; i< item.imgList.size(); i++) {
			if (item.imgList.get(i).move() == false)// 화면을 벗어나면 삭제 하기
			{
				item.imgList.remove(i);
				break;
			}
		}
	}
	static public void bulletProcess() {
		if((System.currentTimeMillis() - prevtime > 5)) {
			double x1 = View.player[0].point().x;
			double y1 = View.player[0].point().y;
			double x2 = Bullet.mouse.x;
			double y2 = Bullet.mouse.y;
			double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
			System.out.println(d);
			double vx = (x2 - x1) / d * bulletSpeed;
			double vy = (y2 - y1) / d * bulletSpeed;
			Bullet b = new  Bullet(x1, y1, vx, vy);
			bullet.bullets.add(b);
			prevtime = System.currentTimeMillis();
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
}

