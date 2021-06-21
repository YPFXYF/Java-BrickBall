package 大作业;

import java.awt.*;
import javax.swing.*;

public class Sprite extends GameObject {
	
    protected Image image;  //image类存储图片
    String filePath;        //图片的文件路径
    
    public Sprite() {
        this.x = 0;
        this.y = 0;     //默认(0,0)
    }

    /**
     * 设置Sprite对象显示的图像路径
     * @param s 图像文件的路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
        //System.out.println(s);
        image = Toolkit.getDefaultToolkit().createImage(filePath);
    }
    
    public String getFilePath() {
    	return filePath;
    }
    
    @Override
    public void draw(Graphics g) {
    	
        //在(x,y)绘制图片
        g.drawImage(image, x, y, null);
    }
    
    @Override
    public void onTick() {

    }
    
    public Image getImage() {
    	return image;
    }
    
}
