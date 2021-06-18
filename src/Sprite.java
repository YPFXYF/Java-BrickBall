import java.awt.*;

public class Sprite extends GameObject {
    protected Image image;
    String filePath;
    public Sprite() {
        this.x = 0;
        this.y = 0;     //默认(0,0)
    }

    /**
     * 设置Sprite对象显示的图像路径
     * @param s 图像文件的路径
     */
    public void setFilePath(String s) {
        filePath = s;
        //System.out.println(s);
        image = Toolkit.getDefaultToolkit().createImage(filePath);
    }
    @Override
    public void draw(Graphics g) {
        //在(x,y)绘制图片
        g.drawImage(image, x, y, null);
    }

    @Override
    public void onTick() {

    }
}
