package ����ҵ;

import java.awt.*;
import javax.swing.*;

public class Sprite extends GameObject {
	
    protected Image image;  //image��洢ͼƬ
    String filePath;        //ͼƬ���ļ�·��
    
    public Sprite() {
        this.x = 0;
        this.y = 0;     //Ĭ��(0,0)
    }

    /**
     * ����Sprite������ʾ��ͼ��·��
     * @param s ͼ���ļ���·��
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
    	
        //��(x,y)����ͼƬ
        g.drawImage(image, x, y, null);
    }
    
    @Override
    public void onTick() {

    }
    
    public Image getImage() {
    	return image;
    }
    
}
