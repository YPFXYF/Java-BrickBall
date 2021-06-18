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
        if (ball.getBegin()) {  //小球已经发射
            /**
             * 如果以球心为坐标系原点，砖块的四个角在同一象限内，
             * 那么直接将半径的平方与四角到圆心的距离进行比较
             */
            boolean flag = isSameQuadrand(ball.center, LU, RD);
            if (flag) { //在同一象限内
                if ((LU.x-ball.center.x)*(LU.x-ball.center.x)+(LU.y-ball.center.y)*(LU.y-ball.center.y)<=17*17||(LD.x-ball.center.x)*(LD.x-ball.center.x)+(LD.y-ball.center.y)*(LD.y-ball.center.y)<=17*17||(RU.x-ball.center.x)*(RU.x-ball.center.x)+(RU.y-ball.center.y)*(RU.y-ball.center.y)<=17*17||(RD.x-ball.center.x)*(RD.x-ball.center.x)+(RD.y-ball.center.y)*(RD.y-ball.center.y)<=17*17) {
                    //碰撞
                    if (ball.getCrossBall() == false) { //如果不是穿越球 且符合同一象限判定
                        ball.setSpeedy(-ball.getSpeedy());  //反弹
                        ball.setSpeedx(-ball.getSpeedx());
                    }
                    this.remove();
                }
            } else {
                if (Math.abs(ball.center.x - this.center.x) <= 47 && Math.abs(ball.center.y - this.center.y) <= 32) {
                    //可以从下方或是上方碰撞该砖块
                    int dx = Math.abs(ball.center.x - this.center.x - this.width / 2), dy = Math.abs(ball.center.y - this.center.y - this.height / 2);
                    if (ball.getCrossBall() == false) {
                        if (dx < dy) {
                            ball.setSpeedx(-ball.getSpeedx());
                        } else {
                            ball.setSpeedy(-ball.getSpeedy());
                        }
                        this.remove();
                    }
                }
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
