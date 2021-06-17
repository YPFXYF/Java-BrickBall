import java.awt.*;

/**
 * 砖
 */
public class Brick extends RectGameObject {
    int width, height;       //宽度和高度
    Point LU, LD, RU, RD;   //四个角坐标


    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
