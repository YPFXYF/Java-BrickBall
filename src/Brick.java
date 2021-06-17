import java.awt.*;

/**
 * 砖
 */
public class Brick extends RectGameObject {
    protected Ball ball;
    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
        setFilePath("images/brick.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public void onTick() {
        if (ball.getBegin()) {
            boolean flag = isSameQuadrand(ball.center, LU, RD);
            if (flag) {

            }
        }
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Ball getBall(Ball ball) {
        return ball;
    }
}
