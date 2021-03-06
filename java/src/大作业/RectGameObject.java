package 大作业;
import java.awt.*;

public class RectGameObject extends Sprite{
	
    protected int width, height;
    Point center, LU, LD, RU, RD;  //中心点，矩形左上方的点，左下方的点······

    public RectGameObject(int x, int y, int width, int height) {
    	
        setPosition(x, y);
        setWidth(width);
        setHeight(height);
        center = new Point(x + width/2, y + height/2);
        LU = new Point(center.x - width / 2, center.y - height / 2); 
        LD = new Point(center.x - width / 2, center.y + height / 2);
        RU = new Point(center.x + width / 2, center.y - height / 2);
        RD = new Point(center.x + width / 2, center.y + height / 2);
        
    }
    //
    /**
     * 碰撞检测部分见https://blog.csdn.net/ximen250/article/details/105566663?spm=1001.2014.3001.5501
     */
    /**
     *
     * @param BallCenter  球心坐标
     * @param RectLeftUp  矩形左上角坐标
     * @param RectRightDown 矩形右下角坐标
     * @return 是否矩形四个角在同一象限内
     */
    protected boolean isSameQuadrand(Point BallCenter, Point RectLeftUp, Point RectRightDown) {
        if ((RectLeftUp.x - BallCenter.x) * (RectRightDown.x - BallCenter.x) > 0 && (RectLeftUp.y - BallCenter.y) * (RectLeftUp.y - BallCenter.y) > 0) {
            return true;
        } else return false;
    }

    void setWidth(int width) {
        this.width = width;
    }

    void setHeight(int height) {
        this.height = height;
    }

    //将物体移到(x, y) 这个位置
    @Override
    public void transfer(int x, int y) {
        this.x += x;
        this.y += y;
        this.center.x += x;
        this.center.y += y;
    }
}
