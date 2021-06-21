package ����ҵ;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * ��Ҳٿص�ľ��
 */

public class Wood extends RectGameObject {
	
	private static int woodNum = 3;          //�������
	private static boolean overed = false;   //����δ����ӵ���Ϊ��
    private int speed = 8;
  
    public Wood() {
        //super(411, 634, 203, 11);
    	super(411, 634, 200, 11);
        setFilePath("images/wood.png");      //����·����ð��ͼƬ�������ڴ��д���ͼƬ
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    
    //�������ƣ�����׸��
    public void rebegin() {
    	if(overed == true && woodNum >=0) {
    		setPosition(411, 634);
    		overed = false;
    		speed = 8;
    	}
    	
    }
    
    public static void over() {
    	overed = true;
    }
    
    @Override
    public void onTick() {
    	
    	rebegin();
    	
    	//ͨ����������ư�
        if (Input.getKeyDown(KeyEvent.VK_LEFT)) {
            if (this.x > 0) {
                this.transfer(-1 * speed, 0);
            }
        }
        if (Input.getKeyDown(KeyEvent.VK_RIGHT)) {
            if (this.x + width < 1024) {
                this.transfer(1 * speed, 0);
            }
        }
        //System.out.println("wood x ==" + x + "wood y==" + y);
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    public static int getWoodNum() {
    	return woodNum;
    }
    
    public static void woodNumDecrease() {
    	woodNum --;
    }
}
