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
	// �÷��̾�(����), �Ѿ�, ��� ������, ������, ��, �浹
	static Player player[] = new Player[1];
	static Bullet bullet = new Bullet(0, 0, 0, 0, 0, 0);
	EBullet eBullet = new EBullet(0, 0, 0, 0, 0);
	BackMove bm = new BackMove();
	Item item = new Item(null, 0, 0, 0);
	Enemy enemy = new Enemy(null, 0, 0, 0, 0);
	Collision collision = new Collision();
	Timer timer = new Timer();
	double dAngle;//����
	static int board[][];//������
	static boolean gameover = false;
	private Graphics bufferGraphics; //����
	private Image offscreen; // ����
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
		item.itemSetting();	// ������ ���� ����
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
					if (bullet.isPress) {//isPress�� True�϶� �Ѿ� �߻�
						bullet.bulletProcess();
					}
					if(Player.hp <= 0) {
						stop();
					}
					collision.collision(bm);
					enemy.enemySetting();// �� ���� ����
					eBullet.bulletProcess();
					eBullet.moveBullet();
					bullet.moveBullet();// �Ѿ� ������
					item.moveItem();// ������ ������
					enemy.moveEnemy();// �� ������
					bm.backgroundMove();//��� ������
					dAngle = getAngle(player[0].point(), bullet.getMousePointer());
					player[0].KeyProcess();// Ű ������
					repaint();
					Thread.sleep(10);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	//���� ���۸�
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		offscreen = createImage(Const.gamePan_W, Const.gamePan_H);
		bufferGraphics = offscreen.getGraphics();
		update(g);
	}
	//���� ���۸�
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		render(bufferGraphics);
		g.drawImage(offscreen, 0, 0, this);
	}
	//���������� �׸� ��
	public void render(Graphics g) {
		g.clearRect(0, 0, Const.gamePan_W, Const.gamePan_H);
		//���
		bm.background(g);
		bm.fadeOutGraphics(g);
		player[0].textDraw(g);
		player[0].EndText(g);
		//������
		item.itemDraw(g);
		//źȯ
		bullet.bulletDraw(g);
		eBullet.bulletDraw(g);
		//���콺 ������
		bullet.mouseDraw(g);
		//�÷��̾�
		player[0].playerDraw(g);
		//��
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