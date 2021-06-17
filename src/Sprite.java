import java.awt.*;

public class Sprite extends GameObject {
    private Image image;

    public Sprite(String filePath) {
        image = Toolkit.getDefaultToolkit().createImage(filePath);
        //从文件中加载图片
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void draw(Graphics g) {
        //在(x,y)绘制图片
        g.drawImage(image, x, y, null);
    }
}
