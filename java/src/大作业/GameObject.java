package 大作业;
import java.awt.*;

/*
 *所有类都会继承此类
 * 这是游戏主要的逻辑处理
 * 里面有：坐标，画图方法draw(Graphics g)
 * 设置坐标和偏移坐标的方法
 * 以及get set相关方法
 */

public abstract class GameObject {
   
    protected int x;
    protected int y;
    protected boolean isRemoved = false;     //是否需要被清除
    
    /**
     * 渲染此GameObject
     */
    public abstract void draw(Graphics g);
    
    /**
     * 在游戏的每一帧被调用
     */
    public abstract void onTick();
    
    /**
     * 设置坐标
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 移动
     */
    public void transfer(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void remove() {
        isRemoved = true;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
}
