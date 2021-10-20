package team_i;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements KeyListener {
	private int x = 0;
	private int y = 0;
	private Image image;
	private View view;
	boolean KeyUp = false; //키보드 입력 처리를 위한 변수
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	private int gap = 3;
	private int size = 40;
	
	public Player(View view) {
		this.view = view;
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
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public Point point() {
		Point p = new Point(getCenterH(), getCenterW());
		return p;
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
			setX(5);
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
		System.out.println(getX()+" "+getY());
	}
	
	public void KeyProcess() {
		
		int pos[] = new int[2];
		// 왼쪽 벽을 벗어나지 않게함
		if (x < 0)
			x = 5;
		// 오른쪽 벽을 벗어나지 않게함
		if (x > Const.gamePan_W - (size * 2))
			x = Const.gamePan_W - (size * 2);
		// 위쪽 벽을 벗어나지 않게함
		if (y < 0)
			y = 5;
		// 아래 벽을 벗어나지 않게함
		if (y > Const.gamePan_H - ((size * 2) + size / 2))
			y = Const.gamePan_H - (size * 3);

		if(KeyUp == true) {
			pos = collisionCheck(getX(), getY(), getX(), getY() - gap);
			setY(getY() - gap);
		}
		if(KeyDown == true) {
			pos = collisionCheck(getX(), getY(), getX(), getY() + gap);
			setY(getY() + gap);
		}
		if(KeyLeft == true) {
			pos = collisionCheck(getX(), getY(), getX() - gap, getY());
			setX(getX() - gap);	
		}
		if(KeyRight == true) {
			pos = collisionCheck(getX(), getY(), getX() + gap, getY());
			setX(getX() + gap);
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
		//pos 현재 위치 저장
		pos[0] = x;
		pos[1] = y;
		
		if(x - next_x > 0)//왼쪽으로 이동
		{
			x_predict = index_x - 1;
		}
		else if(x - next_x < 0)//오른쪽으로 이동
		{
			x_predict = index_x + 1;
		}
		else //안움직임
		{
			x_predict = index_x;
		}
		if(y - next_y > 0)//위로 이동
		{
			y_predict = index_y - 1;
		}
		else if(y - next_y < 0)//아래로 이동
		{
			y_predict = index_y + 1;
		}
		else//안움직임
		{
			y_predict = index_y;
		}
		if(x_predict < 0)
			x_predict = 0;
		if(y_predict < 0)
			y_predict = 0;
		
		if( view.getBoard()[x_predict][y_predict] == 0)
		{
			pos[0] = next_x;
			pos[1] = next_y;
		}
		return pos;
	}
}