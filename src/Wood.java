import java.awt.*;
import java.awt.event.KeyEvent;

public class Wood extends RectGameObject {
    private int speed = 8;
    /**
     * 玩家操控的木板
     */
    public Wood(int x, int y, int width, int height) {
        super(x, y, width, height);
        setFilePath("images/wood..png");
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    @Override
    public void onTick() {
        if (Input.getKeyDown(KeyEvent.VK_LEFT))
            this.transfer(-1*speed, 0);
        if (Input.getKeyDown(KeyEvent.VK_RIGHT))
            this.transfer(1*speed, 0);
    }
}
