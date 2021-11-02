package team_i;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements KeyListener {
	private int x = 50;
	private int y = 400;
	private Image image;
	private View view;
	boolean KeyUp = false; //Ű���� �Է� ó���� ���� ����
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	static int pSpeed = 5;
	private int size = 30;
	static int hp;
	private int score = 0;
	Color textColor = Color.white;
	BackMove bm = new BackMove();
	public Player(View view) {
		this.view = view;
		hp = 100;
		try {
			image = ImageIO.read(new File("image/player.png"));
			image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		return image;
	}
	public int getCenterH() {
		return x + image.getHeight(view)/2;
	}
	public int getCenterW() {
		return y + image.getWidth(view)/2;
	}
	public String getHp() {
		String hp = Integer.toString(Player.hp);
		return hp;
	}
	public String getScore() {
		String score = Integer.toString(this.score);
		return score;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setImage(String imagePath) {
		try {
			this.image =ImageIO.read(new File(imagePath));
			image = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setHp(int a) {
		hp += a;
	}
	public void setScore(int a) {
		this.score += a;
	}
	public Point point() {
		Point p = new Point(getCenterH(), getCenterW());
		return p;
	}

	public void textDraw(Graphics g) {
		g.setColor(textColor);
		g.setFont(new Font("Gothic", Font.ITALIC, 30));
		g.drawString(getHp(), 20, 20);
		g.setFont(new Font("Gothic", Font.ITALIC, 30));
		g.drawString(getScore(), 900, 20);
	}
	public void EndText(Graphics g) {
		if(Player.hp <=0) {
		g.setColor(textColor);
		g.setFont(new Font("Gothic", Font.ITALIC, 55));
		g.drawString("SCORE :" + getScore(), 350, 200);
		}
	}
	public void playerDraw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		g2.rotate(Math.toRadians(view.dAngle), getCenterH(), getCenterW());
		g2.drawImage(getImage(), getX(), getY(), view);
		g2.setTransform(old);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case 87:
			KeyUp = true;
			break;
		case 83:
			KeyDown = true;
			break;
		case 65:
			KeyLeft = true;
			break;
		case 68:
			KeyRight = true;
			break;
		}
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getKeyCode()) {
		case 32:
			break;
		case 87:
			KeyUp = false;
			break;
		case 83:
			KeyDown = false;
			break;
		case 65:
			KeyLeft = false;
			break;
		case 68:
			KeyRight = false;
			break;
		}
		//System.out.println(getX()+" "+getY());
	}
	
	public void KeyProcess() {
		
		int pos[] = new int[2];
		// ���� ���� ����� �ʰ���
		if (x < 0)
			x = 5;
		// ������ ���� ����� �ʰ���
		if (x > Const.gamePan_W - (size * 2))
			x = Const.gamePan_W - (size * 2);
		// ���� ���� ����� �ʰ���
		if (y < 0)
			y = 5;
		// �Ʒ� ���� ����� �ʰ���
		if (y > Const.gamePan_H - (size * 2))
			y = Const.gamePan_H - (size * 2);

		if(KeyUp == true) {
			pos = collisionCheck(x, y,x, y - pSpeed);
			setY(y - pSpeed);
		}
		if(KeyDown == true) {
			pos = collisionCheck(x, y, x, y + pSpeed);
			setY(y + pSpeed);
		}
		if(KeyLeft == true) {
			pos = collisionCheck(x, y, x - pSpeed, y);
			setX(x - pSpeed);	
		}
		if(KeyRight == true) {
			pos = collisionCheck(x, y, x + pSpeed, y);
			setX(x + pSpeed);
		}
		pos[0] = x;
		pos[1] = y;
	}
	private int[] collisionCheck(int x, int y, int next_x, int next_y)
	{
		int pos[] = new int[2];
		int index_x = x;
		int index_y = y;
		int x_predict = 0;
		int y_predict = 0;
		//pos ���� ��ġ ����
		pos[0] = x;
		pos[1] = y;
		
		if(x - next_x > 0)//�������� �̵�
		{
			x_predict = index_x - 5;
		}
		else if(x - next_x < 0)//���������� �̵�
		{
			x_predict = index_x + 5;
		}
		else //�ȿ�����
		{
			x_predict = index_x;
		}
		if(y - next_y > 0)//���� �̵�
		{
			y_predict = index_y - 5;
		}
		else if(y - next_y < 0)//�Ʒ��� �̵�
		{
			y_predict = index_y + 5;
		}
		else//�ȿ�����
		{
			y_predict = index_y;
		}
		if(x_predict < 0)
			x_predict = 0;
		if(y_predict < 0)
			y_predict = 0;
		
		return pos;
	}
}