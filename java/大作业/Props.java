package ����ҵ;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//������
public class Props extends RectGameObject{
	
    Brick brick;                         //������������ש
	private String name;                      
	private int speed = 4;              
	private Wood wood;                
	private Ball ball;
	private boolean isWoodProps = false; //����õ��߶԰������ã���Ϊ��
	private boolean isBallProps = false; //����õ��߶��������ã���Ϊ��
	
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
		
		//��speed���ٶ���ֱ����
		transfer(0, speed);		
		
		//�������ӵ�����ô ��ӵ����� Ϊ��  ���ҰѸõ����Ƴ�
		 if (x >= wood.x && center.x <= wood.x + wood.width && Math.abs(y - wood.y) <= height/2 ) {
			 wood.setIsPropsed(true);
	         this.remove();
	     }
		 
		 //��������䵽ľ������ �Ƴ�
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
    
    
    //�õ��߶�ľ��������ʱ���ø÷���
    public void setWoodProps() {
    	isWoodProps = true;
    }
    
    //�õ��߶���������ʱ���ø÷���
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
