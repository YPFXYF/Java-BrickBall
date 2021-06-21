package 大作业;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

//球类 继承自 矩形游戏对象
public class Ball extends RectGameObject{
    private int speed = 8, radius = 17, speedx, speedy;
    private boolean begin, over, isCrossBall;  //球是否动，是否over，是否是穿刺求
    private Wood wood;
    Random random = new Random();
    public Ball() {
    	
    	//第一 第二个参数是球的x,y坐标，第三，第四个是其长度和宽度
        super(512, 600, 34, 34);
        begin = false;
        over = false;
        center = new Point(512,600);        //球的球心
        //setFilePath("images/Ball.png");
        setFilePath("images\\Ball.png");    //设置球的图像路径
        isCrossBall = false;
    }

    public boolean getCrossBall() {
        return isCrossBall;
    }

    public void setCrossBall(boolean flag) {
        isCrossBall = flag;
    }

    public Wood getWood() {
        return wood;
    }

    public void setWood(Wood wood) {
        this.wood = wood;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    
    //当球下落且未被板接到时会调用该方法
    public void reBegin() {
    	
    	//判断是否要将球的状态初始化
    	if(over == true && Wood.getWoodNum() > 0) {
    		setPosition(512, 600);
    		center = new Point(512,600);
    		speedx = 0;
    		speedy = 0;
    	}
    }
    @Override
    
    //每一帧都会调用该方法
    public void onTick() {
    	
    	//当球静止并按键盘的向上键时，会随机向左上或者右上发射
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
        
        //当球撞到墙壁时，根据其撞到的墙壁将相应的分量速度取反
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
         
        // y == 600 意味着此时球在板上
        if (y == 600) {
        	
        	//此处待调整，x应该板长有关，板长同样待调整
            if (Input.getKeyDown(KeyEvent.VK_LEFT) && this.x > 102)
                this.transfer(-1 * speed, 0);
            if (Input.getKeyDown(KeyEvent.VK_RIGHT) && this.x + 34 <= 922)
                this.transfer(1 * speed, 0);
        }

        //当球落在板上时，y方向速度取反
        if (x >= wood.x && center.x <= wood.x + wood.width && Math.abs(y - wood.y) <= radius && speedy > 0) {
            speedy = -speedy;
        }

        //当球未被板接到时
        if (y >= 768 - radius * 2) {
            begin = false;
            over = true;
            Wood.woodNumDecrease();  //板数-1
            Wood.over();      //通知木板 球未被接到 方法重置木板的位置
            reBegin();
            return;
        }
        
        transfer(speedx, speedy);
       // System.out.println("ball x,y:"+ x + " " + y + "bar x y" + wood.x + " " + wood.y);
        
        

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
}
