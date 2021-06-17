import java.awt.*;
import java.awt.event.KeyEvent;

public class Wood extends RectGameObject {
    private int speed = 8;
    /**
     * 玩家操控的木板
     */
    public Wood() {
        super(411, 634, 203, 11);
        setFilePath("images/wood.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public void onTick() {
        if (Input.getKeyDown(KeyEvent.VK_LEFT))
            this.transfer(-1*speed, 0);
        if (Input.getKeyDown(KeyEvent.VK_RIGHT))
            this.transfer(1*speed, 0);
        //System.out.println("wood x ==" + x + "wood y==" + y);
    }

    @Override
    public void transfer(int x, int y) {
        super.transfer(x, y);

    }
}
