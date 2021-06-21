package 大作业;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//道具类
public class Props extends RectGameObject{
	
    Brick brick;                         //与道具相关联的砖
	private String name;                      
	private int speed = 4;              
	private Wood wood;                
	private Ball ball;
	private boolean isWoodProps = false; //如果该道具对板起作用，则为真
	private boolean isBallProps = false; //如果该道具对球起作用，则为真
	
	public Props(int x, int y, int width,int height, String name, String filePath) {
		super(x, y, width, height);
		this.name = name;
		setFilePath(filePath);
	}
	//

	public void draw(Graphics g) {
		   g.drawImage(image, x, y, null);
	}
	
	public void draw(Graphics g, Image img) {
		   g.drawImage(img, x, y, null);
	}
	
	public void onTick() {	
		
		//以speed的速度竖直下落
		transfer(0, speed);		
		
		//如果被板接到，那么 板接到道具 为真  并且把该道具移除
		 if (x >= wood.x && center.x <= wood.x + wood.width && Math.abs(y - wood.y) <= height/2 ) {
			 wood.setIsPropsed(true);
	         this.remove();
	     }
		 
		 //如果道具落到木板下面 移除
		 if( y >= 768 - height) {
			 this.remove();
		 }
	}
	
    public Wood getWood() {
        return wood;
    }

    public void setWood(Wood wood) {
        this.wood = wood;
    }
    
    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
    
    
    //该道具对木板起作用时调用该方法
    public void setWoodProps() {
    	isWoodProps = true;
    }
    
    //该道具对球起作用时调用该方法
    public void setBallProps() {
    	isBallProps = true;
    }
    
    public boolean getIsWoodProps() {
    	return isWoodProps;
    }
    
    public boolean getIsBallProps() {
    	return isBallProps;
    }
    
    public String getName() {
    	return name;
    }
}
