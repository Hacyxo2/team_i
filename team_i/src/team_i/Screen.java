package team_i;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Screen extends Canvas implements KeyListener {


	
	Car1 car[] = new Car1[2];
	
	private static final long serialVersionUID = 1L;
	
	public Screen() {
		car[0] = new Car1(0, 0, "image/car.png");
		
			
		addKeyListener(this);
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(car[0].getImage(), car[0].getX(), car[0].getY(), this);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int gap = 10;
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			car[0].setY(car[0].getY() - gap);
			break;
		case KeyEvent.VK_DOWN:
			car[0].setY(car[0].getY() + gap);
			break;
		case KeyEvent.VK_LEFT:
			car[0].setX(car[0].getX() - gap);
			break;
		case KeyEvent.VK_RIGHT:
			car[0].setX(car[0].getX() + gap);
			break;
		}
		System.out.println(car[0].getX() +" "+ car[0].getY());
		repaint();
	}

}

