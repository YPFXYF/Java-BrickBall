import java.awt.event.KeyEvent;

public class RenderThread implements Runnable {
    /**
     * 用于定时重画窗口（刷新）
     */
    private Thread thread;
    private boolean exited = false;
    private int interval;   //每次绘制间隔
    public Game game;

    public RenderThread(Game g) {
        game = g;
        interval = 1000 / game.getFps();//计算出隔多久重画一次
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + "Start rendering");
        while (!exited) {
            exit();
            game.repaint();
            try {
                Thread.sleep(interval);
            }
            catch (Exception e) {
                System.out.println(thread.getName() + "error:" + e.toString());
                break;
            }
        }
        System.out.println(thread.getName() + "Stop rendering");
        game.exit();
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "RenderThread");
            thread.start();
        }
    }

    public void exit() {
        if (Input.getKeyDown(KeyEvent.VK_ESCAPE)) {
            this.exited = true;
        }
    }
}
