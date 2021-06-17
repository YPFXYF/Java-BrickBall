import java.awt.*;

public class Ball extends RectGameObject{
    private int speed = 4;
    public Ball() {
        super(512, 600, 34, 34);
        center = new Point(512,600);
        setFilePath("images/Ball.png");
    }

    @Override
    public void draw(Graphics g) {
        setPosition(512, 600);
        g.drawImage(image, x, y, null);
    }
    @Override
    public void onTick() {

    }
}
