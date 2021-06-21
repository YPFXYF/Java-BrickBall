package 大作业;
import java.awt.event.KeyEvent;

/**
 * 用于定时重画窗口（刷新）
 */

public class RenderThread implements Runnable {

    private Thread thread;
    //标志游戏是否退出，是否胜利，是否失败
    private static boolean exited = false;       
    private static boolean victoryed = false;
    private static boolean overed = false;
    
    private int interval;   //每次绘制间隔
    public Game game;
    
    public RenderThread(Game g) {
        game = g;
        interval = 1000 / game.getFps();//计算出隔多久重画一次
    }

    @Override
    public void run() {
    	
        System.out.println(thread.getName() + "Start rendering");
        //当有三者均为假时,执行循环
        while (!exited && !victoryed &&!overed) {
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
        if( exited == true )
        	game.exit();
        thread.interrupt();
      
    }
       
    public static void victory() {
        victoryed = true;	
    }
    
    public static void over() {
        overed = true;	
    }
    
    public void start() {
    	
        if (thread == null) {
            thread = new Thread(this, "RenderThread");
            thread.start();
        }
        
    }
    
    //如果用户按下Esc键，将exited赋值为真
    public void exit() {
        if (Input.getKeyDown(KeyEvent.VK_ESCAPE )) {
            this.exited = true;
        }
    }
}
