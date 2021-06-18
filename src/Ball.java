import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball extends RectGameObject{
    private int speed = 8, radius = 17, speedx, speedy;
    private boolean begin, over;
    private Wood wood;
    Random random = new Random();
    public Ball() {
        super(512, 600, 34, 34);
        begin = false;
        over = false;
        center = new Point(512,600);
        setFilePath("images/Ball.png");
    }

    public Wood getWood() {
        return wood;
    }

    public void setWood(Wood wood) {
        this.wood = wood;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    @Override
    public void onTick() {
        if (!begin) {
            if (Input.getKeyDown(KeyEvent.VK_UP)) {
                int direct = random.nextInt() % 2;
                over = false;
                begin = true;
                if ((direct % 2) == 0) {
                    speedx = speed;
                } else speedx = -speed;
                speedy = -speed;
            }
        }

        if (begin) {
            if (x <= 0 || x >= 1024 - 34) {
                speedx = -speedx;
            }

            if (y <= 0 || y >= 768 - 34) {
                speedy = -speedy;
            }
        }

        if (y == 600) { //真 实 的 滚 动 引 擎
            if (Input.getKeyDown(KeyEvent.VK_LEFT))
                this.transfer(-1 * speed, 0);
            if (Input.getKeyDown(KeyEvent.VK_RIGHT))
                this.transfer(1 * speed, 0);
        }

        if (x >= wood.x && center.x <= wood.x + wood.width && Math.abs(y - wood.y) <= radius && speedy > 0) {
            speedy = -speedy;
        }

        if (y >= 768 - radius * 2) {
            begin = false;
            over = true;
            return;
        }

        transfer(speedx, speedy);
        System.out.println("ball x,y:"+ x + " " + y + "bar x y" + wood.x + " " + wood.y);

    }

    boolean getBegin() {
        return begin;
    }

    boolean getOver() {
        return over;
    }

}
