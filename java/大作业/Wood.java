package 大作业;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 玩家操控的木板
 */

public class Wood extends RectGameObject {
	
	private boolean isPropsed = false;       //如果木板接到道具后，为true，为木板添加道具效果，添加完成后，重新置为false 
	private static int woodNum = 3;          //板的数量
	private static boolean overed = false;   //球若未被板接到则为真
    private int speed = 8;
    private int newWidth ;           
    private int oldWidth;
    private Props props;
    private Ball ball;
    
    public Wood() {
        //super(411, 634, 203, 11);  图片像素已修改为200x11
    	super(411, 634, 200, 11);
        setFilePath("images/wood.png");      //根据路径获得板的图片，并在内存中创建图片
    }
    
 
    public void draw(Graphics g, Image img) {
		   g.drawImage(img, x, y, null);
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
        
        //如果接到道具，判断道具的
        if( isPropsed ) {
        	judgePropsEffect(props);
        	isPropsed = false;
        }
     
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
    
    public void setIsPropsed(boolean flag) {
        	isPropsed = flag;
    }
    
    public boolean getIsPropsed() {
    	return isPropsed;
    }
    
    //根据掉落的道具名字判断道具的效果
    public void judgePropsEffect(Props props) {
    	
    	if(props.getName().equals("woodx0.5")) {
    		changeWoodWidth(0.5);                       //改变板长
    	    image = Game.images[7];                     //改变板的图像 下同，不再赘述
        }else if(props.getName().equals("woodx0.75")) {
    		changeWoodWidth(0.75);
    		image = Game.images[8];
    	}else if(props.getName().equals("woodx1.5")) {
    		changeWoodWidth(1.5);
    		image = Game.images[9];
    	}else if(props.getName().equals("woodx2")) {
    		changeWoodWidth(2);
    		image = Game.images[10];
    	}else if(props.getName().equals("death")) {
    		woodNum--;
    	}else if(props.getName().equals("smallBall")) {
    		ball.setSmallBall(true);
    	}else {
    		ball.setBigBall(true);
    	}
    	
    }
    
    //将板长伸长或缩短为原来的多少倍
    public void changeWoodWidth(double widthFactor) {
    	oldWidth = width;
    	newWidth = (int)(width * widthFactor);
    	setWidth(newWidth);
    	setPosition(x-(newWidth/2 - oldWidth/2) ,y);
    }
   
    public void setProps(Props props) {
    	this.props = props;
    }
    
    public void setBall(Ball ball) {
    	this.ball = ball;
    }
}
