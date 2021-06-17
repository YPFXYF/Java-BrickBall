import javax.swing.*;

public class Main {
    /*
    游戏里所有绘制操作都要在窗口的基础上进行，可以用JFrame来新建窗口
     */
    public static void main(String[] args) {
        Game game = new Game(1024, 768, "YJMSTR 打砖块", 24);
        Sprite s = new Sprite("C:\\Users\\jay12\\Pictures\\7.jpg");
        s.setPosition(114,514);
        game.addGameObject(s);
    }
}
