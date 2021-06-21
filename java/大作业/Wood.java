package ����ҵ;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * ��Ҳٿص�ľ��
 */

public class Wood extends RectGameObject {
	
	private boolean isPropsed = false;       //���ľ��ӵ����ߺ�Ϊtrue��Ϊľ����ӵ���Ч���������ɺ�������Ϊfalse 
	private static int woodNum = 3;          //�������
	private static boolean overed = false;   //����δ����ӵ���Ϊ��
    private int speed = 8;
    private int newWidth ;           
    private int oldWidth;
    private Props props;
    private Ball ball;
    
    public Wood() {
        //super(411, 634, 203, 11);  ͼƬ�������޸�Ϊ200x11
    	super(411, 634, 200, 11);
        setFilePath("images/wood.png");      //����·����ð��ͼƬ�������ڴ��д���ͼƬ
    }
    
 
    public void draw(Graphics g, Image img) {
		   g.drawImage(img, x, y, null);
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
        
        //����ӵ����ߣ��жϵ��ߵ�
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
    
    //���ݵ���ĵ��������жϵ��ߵ�Ч��
    public void judgePropsEffect(Props props) {
    	
    	if(props.getName().equals("woodx0.5")) {
    		changeWoodWidth(0.5);                       //�ı�峤
    	    image = Game.images[7];                     //�ı���ͼ�� ��ͬ������׸��
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
    
    //���峤�쳤������Ϊԭ���Ķ��ٱ�
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
