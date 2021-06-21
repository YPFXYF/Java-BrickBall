package ����ҵ;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

//���� �̳��� ������Ϸ����
public class Ball extends RectGameObject{
    private int speed = 8, radius = 17, speedx, speedy;
    private boolean begin, over, isCrossBall, isSmallBall, isBigBall;  //���Ƿ񶯣��Ƿ�over���Ƿ��Ǵ������Ƿ��С���Ƿ�����
    private Wood wood;
    private Props props; 
    Random random = new Random();
    public Ball() {
    	
    	//��һ �ڶ������������x,y���꣬���������ĸ����䳤�ȺͿ��
        super(512, 600, 34, 34);
        begin = false;
        over = false;
        center = new Point(512, 600);
        //setFilePath("images/Ball.png");
        setFilePath("images\\Ball.png");    //�������ͼ��·��
        isCrossBall = false;
    }

    public boolean getCrossBall() {
        return isCrossBall;
    }

    public void setCrossBall(boolean flag) {
        isCrossBall = flag;
    }
    
    public void setSmallBall(boolean flag) {
        isSmallBall = flag;
    }

    public void setBigBall(boolean flag) {
        isBigBall = flag;
    }
    


    public Wood getWood() {
        return wood;
    }

    public void setWood(Wood wood) {
        this.wood = wood;
    }
    
    public void setImage(Image image) {
    	this.image = image;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
        
    //����������δ����ӵ�ʱ����ø÷���
    public void reBegin() {
    	
    	//�ж��Ƿ�Ҫ�����״̬��ʼ��
    	if(over == true && Wood.getWoodNum() > 0) {
    		setPosition(512, 600);
    		center = new Point(512, 600);
    		speedx = 0;
    		speedy = 0;
    	}
    }
    @Override
    
    //ÿһ֡������ø÷���
    public void onTick() {
    	
    	//����ֹ�������̵����ϼ�ʱ������������ϻ������Ϸ���
        if (!begin) {
            if (Input.getKeyDown(KeyEvent.VK_UP)) {
                int direct = random.nextInt() % 2;
                over = false;
                begin = true;
                wood.setSpeed(10);
                if ((direct % 2) == 0) {
                    speedx = speed;
                } else speedx = -speed;
                speedy = -speed;
            }
        }
        
        //����ײ��ǽ��ʱ��������ײ����ǽ�ڽ���Ӧ�ķ����ٶ�ȡ��
        if (begin) {
            if (x <= 0) {
                speedx = Math.abs(speedx);
            }

            if (x >= 1024 - 34) {
                speedx = -Math.abs(speedx);
            }

            if (y <= 0) {
                speedy = Math.abs(speedy);
            }
            
        }
         
        // y == 600 ��ζ�Ŵ�ʱ���ڰ���
        if (y == 600) {
        	
        	//�˴���������xӦ�ð峤�йأ��峤ͬ��������
            if (Input.getKeyDown(KeyEvent.VK_LEFT) && this.x > 102)
                this.transfer(-1 * speed, 0);
            if (Input.getKeyDown(KeyEvent.VK_RIGHT) && this.x + 34 <= 922)
                this.transfer(1 * speed, 0);
        }

        //�������ڰ���ʱ��y�����ٶ�ȡ��
        if (x >= wood.x && center.x <= wood.x + wood.width && Math.abs(y - wood.y) <= radius && speedy > 0) {
            speedy = -speedy;
        }

        //����δ����ӵ�ʱ
        if (y >= 768 - radius * 2) {
            begin = false;
            over = true;
            Wood.woodNumDecrease();  //����-1
            Wood.over();             //֪ͨľ�� ��δ���ӵ� ��������ľ���λ��
            reBegin();
            return;
        }
        
        transfer(speedx, speedy);
        //ÿһ֡�ж��ǲ���Ҫ��С��
        judgeTheBall();
    }

    boolean getBegin() {
        return begin;
    }

    boolean getOver() {
        return over;
    }

    public void setSpeedx(int speedx) {
        this.speedx = speedx;
    }

    public void setSpeedy(int speedy) {
        this.speedy = speedy;
    }

    public int getSpeedx() {
        return speedx;
    }

    public int getSpeedy() {
        return speedy;
    }
    
    public void setProps(Props props) {
    	this.props = props;
    }
    
//�жϸ����Ƿ�Ҫ���򻯻���С��
public void judgeTheBall() {
	    
	    
    	if(isSmallBall == true) {
    		image = Game.images[13];               //�ı�ͼ����ͬ
    		
    		int oldRadius = radius;
    		radius = 10;
    		int newX = x -(radius - oldRadius); 
    	    int newY = y -(radius - oldRadius); 
    		setPosition(newX, newY);
    		center = new Point(newX, newY);
    		
    		isSmallBall = false;
    	}else if(isBigBall == true) {
    		image = Game.images[14];
    		
    		int oldRadius = radius;
    		radius = 25;
    		int newX = x -(radius - oldRadius); 
    	    int newY = y -(radius - oldRadius); 
    		setPosition(newX, newY);
    		center = new Point(newX, newY);
    		
    		isBigBall = false;
    	}
    	
    }
}
