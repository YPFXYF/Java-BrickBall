import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
由于做的是游戏，用Java自带控件做效率很低，所以需要新建一个类，重写JFrame的paint方法，
自己绘图
*/
public class Game extends JFrame {
    private int score, score0, score1, score2;
    private int width;
    private int height;
    private int fps;
    private RenderThread render;
    private String windowTitle;
    private ArrayList<GameObject> gameObjects;
    private Graphics tempGraphics;
    RectGameObject s0, s1, s2;
    public Game(int windowWidth, int windowHeight, String title, int fps) {
        gameObjects = new ArrayList<GameObject>();
        width = windowWidth;
        height = windowHeight;
        this.fps = fps;
        Wood wood = new Wood();
        gameObjects.add(wood);
        //s0 = new RectGameObject(32, 100, 32, 32);
        //s0.setFilePath("images/Ball.png");
        //gameObjects.add(s0);
        Ball ball = new Ball();
        ball.setWood(wood);
        gameObjects.add(ball);
        for (int i = 1; i <= 16; i++) {
            for (int j = 1; j <= 3; j++) {
                Brick brick = new Brick((i-1)*63+1, j*32 + 100, 60, 30);
                brick.setBall(ball);
                gameObjects.add(brick);
            }
        }
        windowTitle = title;
        createWindow();
        render = new RenderThread(this);
        render.start();
        Input input = new Input();
        input.init();
        this.addKeyListener(input);
    }

    private void createWindow() {
        setSize(width, height); //继承自JFrame
        setTitle(windowTitle);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
    //重写paint方法
    @Override
    public void paint(Graphics g) { //使用双缓冲解决闪烁问题
        //在内存里搞一个和窗口大小一致的图
        Image img = this.createImage(width, height);
        tempGraphics = img.getGraphics();   //
        clear(tempGraphics);   //清除上一帧的内容
        //渲染所有sprite
        for (GameObject gameObject : gameObjects) {
            gameObject.onTick();
            gameObject.draw(tempGraphics);
        }

        for (GameObject go: gameObjects) {
            if (go.isremoved) {
                gameObjects.remove(go);
            }
        }
        //g.drawImage(Toolkit.getDefaultToolkit().createImage("images/Ball.png"), 32, 32, null);

        //将内存画布的内容画回窗口上
        g.drawImage(img, 0, 0, null);

        if (gameObjects.size() == 2) {
            System.out.println("胜利");
            return;
        }

    }

    public void clear(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
    }

    public void exit() {    //退出游戏
        System.exit(1);
    }

    public int getFps() {
        return fps;
    }
    /**
    * 设置fps,返回值表示是否设置成功
     *
    */
    public Boolean setFps(int fps) {
        if (fps <= 0) return false;
        else {
            this.fps = fps;
            return true;
        }
    }

}
