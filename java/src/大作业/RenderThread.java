package ����ҵ;
import java.awt.event.KeyEvent;

/**
 * ���ڶ�ʱ�ػ����ڣ�ˢ�£�
 */

public class RenderThread implements Runnable {

    private Thread thread;
    //��־��Ϸ�Ƿ��˳����Ƿ�ʤ�����Ƿ�ʧ��
    private static boolean exited = false;       
    private static boolean victoryed = false;
    private static boolean overed = false;
    
    private int interval;   //ÿ�λ��Ƽ��
    public Game game;
    
    public RenderThread(Game g) {
        game = g;
        interval = 1000 / game.getFps();//�����������ػ�һ��
    }

    @Override
    public void run() {
    	
        System.out.println(thread.getName() + "Start rendering");
        //�������߾�Ϊ��ʱ,ִ��ѭ��
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
    
    //����û�����Esc������exited��ֵΪ��
    public void exit() {
        if (Input.getKeyDown(KeyEvent.VK_ESCAPE )) {
            this.exited = true;
        }
    }
}
