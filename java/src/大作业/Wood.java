package 大作业;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 玩家操控的木板
 */

public class Wood extends RectGameObject {
	
	private static int woodNum = 3;          //板的数量
	private static boolean overed = false;   //球若未被板接到则为真
    private int speed = 8;
  
    public Wood() {
        //super(411, 634, 203, 11);
    	super(411, 634, 200, 11);
        setFilePath("images/wood.png");      //根据路径获得板的图片，并在内存中创建图片
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    
    //与球类似，不再赘述
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
    	
    	//通过方向键控制板
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
