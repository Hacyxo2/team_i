package team_i;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.ImageIcon;

public class Item {
	private Image image;
	private int type;
	private int x;
	private int y;

	ArrayList<Item> imgList = new ArrayList<>(100);

	public Item(Image image, int type, int x, int y) {
		this.image = image;
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	class test1{
		ImageIcon itemIc1 = new ImageIcon("image/block.png");
		Image  item1 = itemIc1.getImage();
	}

	public void itemSetting() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		test1 test1 = new test1();
		//특정 시간 마다 생성
//		for(int i = 0; i < emdwkd.length; i++) {
//			if(count == 0 || emdwkd[i] == count) {
				imgList.add(new Item(test1.item1, rand.nextInt(2), 1200 + rand.nextInt(200), rand.nextInt(800)));
				imgList.add(new Item(test1.item1, rand.nextInt(2), 1200 + rand.nextInt(200), rand.nextInt(800)));
				imgList.add(new Item(test1.item1, rand.nextInt(2), 1200 + rand.nextInt(200), rand.nextInt(800)));
//			}
//		}
//		count++;
	}
	public void initItem(int index) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		imgList.get(index).setX(1200+rand.nextInt(200));
		imgList.get(index).setY(rand.nextInt(800));
		imgList.get(index).setType(rand.nextInt(2));
	}
	
	public void itemDraw(Graphics g) {
		for (int i = 0; i < imgList.size(); i++) {
			g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), null);
		}
	}
	public void moveItem() {
		for (int i = 0; i< imgList.size(); i++) {
			imgList.get(i).setX(imgList.get(i).getX()-2);
			if (imgList.get(i).move() == false)// 화면을 벗어나면 삭제 하기
			{
				initItem(i);
				break;
			}
		}
	}

	public boolean move() {
		if(x < -50 || y < 0 || y > Const.gamePan_H) {
			return false;				
		}
		return true;
	}

	public void itemEffect(int type) {
		if (type == 1) {
			View.player[0].setImage("image/person.png");
			View.bullet.setColor(1);
		}
		else if(type == 0){
			View.player[0].setImage("image/player.png");
			View.bullet.setColor(0);
		}
	}
	
	public Image getImage() {
		return image;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getType() {
		return type;
	}
	public void setImage(Image image) {
		this.image = image;
	} 
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setType(int type) {
		this.type = type;
	}
//	public void setEffectBm(int count) {
//		if(count==) {
//			
//		}
//		else if
//		else
//	}
}
