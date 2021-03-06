package team_i;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JOptionPane;

public class View extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 플레이어(전역), 총알, 배경 움직임, 아이템, 적, 충돌
	static Player player[] = new Player[1];
	static Bullet bullet = new Bullet(0, 0, 0, 0, 0, 0);
	EBullet eBullet = new EBullet(0, 0, 0, 0, 0);
	BackMove bm = new BackMove();
	Item item = new Item(null, 0, 0, 0);
	Enemy enemy = new Enemy(null, 0, 0, 0, 0);
	Collision collision = new Collision();
	Timer timer = new Timer();
	double dAngle;//각도
	static int board[][];//게임판
	static boolean gameover = false;
	private Graphics bufferGraphics; //버퍼
	private Image offscreen; // 버퍼
	Thread th;
	public void start() {
		th = new Thread(this);
		th.start();
	}
	public void stop() {
		gameover = true;
	}
	public View() {
		player[0] = new Player(this);
		item.itemSetting();	// 아이템 랜덤 생성
		addMouseMotionListener(bullet);
		addMouseListener(bullet);
		addKeyListener(player[0]);
	}
	public int[][] getBoard(){
		return board;
	}
		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				while (gameover!=true) {
					if (bullet.isPress) {//isPress가 True일때 총알 발사
						bullet.bulletProcess();
					}
					if(Player.hp <= 0) {
						stop();
					}
					collision.collision(bm);
					enemy.enemySetting();// 적 랜덤 생성
					eBullet.bulletProcess();
					eBullet.moveBullet();
					bullet.moveBullet();// 총알 움직임
					item.moveItem();// 아이템 움직임
					enemy.moveEnemy();// 적 움직임
					bm.backgroundMove();//배경 움직임
					dAngle = getAngle(player[0].point(), bullet.getMousePointer());
					player[0].KeyProcess();// 키 움직임
					repaint();
					Thread.sleep(10);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	//더블 버퍼링
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		offscreen = createImage(Const.gamePan_W, Const.gamePan_H);
		bufferGraphics = offscreen.getGraphics();
		update(g);
	}
	//더블 버퍼링
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		render(bufferGraphics);
		g.drawImage(offscreen, 0, 0, this);
	}
	//실질적으로 그릴 것
	public void render(Graphics g) {
		g.clearRect(0, 0, Const.gamePan_W, Const.gamePan_H);
		//배경
		bm.background(g);
		bm.fadeOutGraphics(g);
		player[0].textDraw(g);
		player[0].EndText(g);
		//아이템
		item.itemDraw(g);
		//탄환
		bullet.bulletDraw(g);
		eBullet.bulletDraw(g);
		//마우스 포인터
		bullet.mouseDraw(g);
		//플레이어
		player[0].playerDraw(g);
		//적
		enemy.enemyDraw(g);
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