package team_i;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

public class View extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// �÷��̾�(����), �Ѿ�, ��� ������, ������
	static Player player[] = new Player[1];
	Bullet bullet = new Bullet(0, 0, 0, 0);
	BackMove bm = new BackMove();
	Item.test1 test1 = new Item(null, 0, 0, 0).new test1();
	Item item = new Item(null, 0, 0, 0);
	
	double dAngle;//����
	private int board[][];
	
	private Graphics bufferGraphics; //����
	private Image offscreen; // ����
	Thread th;
	
	public View() {
		player[0] = new Player(this);
		board = new int[Const.gamePan_W][Const.gamePan_H];
		th = new Thread(this);
		//���忡 ������ ��ġ ����
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		item.imgList.add(new Item(test1.item1, 0, rand.nextInt(1000), rand.nextInt(800)));
		item.imgList.add(new Item(test1.item1, 0, rand.nextInt(1000), rand.nextInt(800)));
		item.imgList.add(new Item(test1.item1, 0, rand.nextInt(1000), rand.nextInt(800)));
		
		th.start();
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
				while (true) {
					if (bullet.isPress) {
						bullet.bulletProcess();
					}
					// �Ѿ� ������
					bullet.moveBullet();
					// ������ ������
					item.moveItem();
					//��� ������
					bm.backgroundMove();
					dAngle = getAngle(player[0].point(), bullet.mouse);
					//System.out.println(bullet.mouse); //���콺 ��ġ Ȯ��
					player[0].KeyProcess();
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
		//������
		item.itemDraw(g);
		//źȯ
		bullet.BulletDraw(g);
		//�÷��̾�
		player[0].playerDraw(g);
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