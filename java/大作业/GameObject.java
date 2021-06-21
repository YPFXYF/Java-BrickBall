package ����ҵ;
import java.awt.*;

/*
 *�����඼��̳д���
 * ������Ϸ��Ҫ���߼�����
 * �����У����꣬��ͼ����draw(Graphics g)
 * ���������ƫ������ķ���
 * �Լ�get set��ط���
 */

public abstract class GameObject {
   
    protected int x;
    protected int y;
    protected boolean isRemoved = false;     //�Ƿ���Ҫ�����
    
    /**
     * ��Ⱦ��GameObject
     */
    public abstract void draw(Graphics g);
    
    /**
     * ����Ϸ��ÿһ֡������
     */
    public abstract void onTick();
    
    /**
     * ��������
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * �ƶ�
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
