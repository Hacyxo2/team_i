package test;

import java.awt.Canvas;
//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;



public class View extends Canvas implements MouseListener, MouseMotionListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Player player[] = new Player[1];
	private Block block;
	Point mouse;
	Point pos;
	double dAngle;
	ArrayList<Bullet>bullets =new ArrayList<Bullet>();
	int bulletSpeed = 5;
	long prevtime = 0;
	
	private Graphics bufferGraphics; //버퍼
	private Image offscreen; // 버퍼

	private int board[][];
	Thread th;
	
	public View() {
		player[0] = new Player(this);
		block = new Block();
		pos = new Point(player[0].getCenterH(), player[0].getCenterW());
		mouse = new Point(player[0].getX(), player[0].getY());
		board = new int[Const.gamePan_W][Const.gamePan_H];
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		for(int i = 0; i < Const.gamePan_W; i++) {
			for(int j = 0; j < Const.gamePan_H; j++) {
				board[i][j] = rand.nextInt(2);
			}
		}
		th = new Thread(this);
		th.start();
		addMouseMotionListener(this);
		addKeyListener(player[0]);
		addMouseListener(this);
		
	}
	public int[][] getBoard(){
		return board;
	}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			try {
				while(true) {
					
					player[0].KeyProcess();
					bulletProcess();
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
		Graphics2D g2 = (Graphics2D) g;
		
		AffineTransform old = g2.getTransform();
		
		g2.rotate(Math.toRadians(dAngle), player[0].getCenterH(), player[0].getCenterW());
		g2.drawImage(player[0].getImage(), player[0].getX(), player[0].getY(), this);
		g2.setTransform(old);
		for (int i = 0; i < bullets.size();i++)// 총알 그리기
		{
			bullets.get(i).draw(g2);
		}
		drawBlock(g);
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		Point p = new Point(0,0);
		p.x += bulletSpeed * Math.cos(dAngle);
		p.y += bulletSpeed * Math.sin(dAngle);//각도에 따른 총알 이동값 계산
		Bullet b = new Bullet(new Point(player[0].getCenterH(), player[0].getCenterW()), p);//총알 생성
		System.out.println("위치"+p);
		bullets.add(b);//배열에 저장
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void bulletProcess() {
		dAngle = getAngle(pos, mouse);
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
			if(bullets.get(i).getPos().x < 0 || bullets.get(i).getPos().x > 1000) {
				bullets.remove(i);
				break;
			}
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
	private final static int BLOCK = 1;
	private void drawBlock(Graphics g) {
		for(int i=0; i < Const.gamePan_W; i++)
		{
			for(int j=0; j < Const.gamePan_H; j++)
			{
				if( board[i][j] == BLOCK)
				{
					//index to postion
					block.setPosition(i*40, j*40);
					block.draw(g, this);
				}
			}
			board[0][0] = 0;
		}
	}
}

