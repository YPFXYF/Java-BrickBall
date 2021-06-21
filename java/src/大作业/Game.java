package 大作业;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
由于做的是游戏，用Java自带控件做效率很低，所以需要新建一个类，重写JFrame的paint方法，
自己绘图
*/
public class Game extends JFrame {
	
    //private int score, score0, score1, score2;

    private int windowWidth;
    private int windowHeight;
    private String windowTitle;
    private int fps;                               //每秒更新的帧数
    private RenderThread renderThread;             //渲染线程
    private ArrayList<GameObject> gameObjects;     //储存游戏对象的数组列表
    private Graphics tempGraphics;                 //直接建立在内存中的画布(用于双缓冲)
    RectGameObject s0, s1, s2;
    
    public Game(int windowWidth, int windowHeight, String windowTitle, int fps) {
    	
        gameObjects = new ArrayList<GameObject>();
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.windowTitle = windowTitle;
        this.fps = fps;
        
        Wood wood = new Wood();
        gameObjects.add(wood);
        
        //s0 = new RectGameObject(32, 100, 32, 32);
        //s0.setFilePath("images/Ball.png");
        //gameObjects.add(s0);
        
        Ball ball = new Ball();
        ball.setWood(wood);
        gameObjects.add(ball);
        
        //生成砖块
        for (int i = 1; i <= 1; i++) {
            for (int j = 1; j <= 1; j++) {
                Brick brick = new Brick((i-1)*63+1, j*32 + 100, 60, 30);
                brick.setBall(ball);
                gameObjects.add(brick);
            }
        }
        
        createWindow();
        
        Input input = new Input();
        input.init();
        renderThread = new RenderThread(this);
        renderThread.start();
 
        this.addKeyListener(input);             //添加键盘监听类
    }

    private void createWindow() {
    	
        setSize(windowWidth, windowHeight); //继承自JFrame
        setTitle(windowTitle);
        setResizable(false);                    //设置窗口不可放大缩小
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
        Image img = this.createImage(windowWidth, windowHeight);
        tempGraphics = img.getGraphics();   //
        clear(tempGraphics);   //清除上一帧的内容
        
        //渲染所有sprite
        for (GameObject gameObject : gameObjects) {
            gameObject.onTick();
            gameObject.draw(tempGraphics);
        }
        
        //判断数组列表中的砖块是否需要清除
        for (GameObject go: gameObjects) {
            if (go.isRemoved) {
                gameObjects.remove(go);
                //if(go instanceof Brick)
                	//score += 1;
            }
        }
        
        g.drawImage(img, 0, 0, null);
        
        //为解决 drawImage调用时才加载图片而可能导致图片不显示 的问题采用下述代码，加载要调用的图片
     	Image img1 = Toolkit.getDefaultToolkit().getImage("images/victory.png");
     	Image img2 = Toolkit.getDefaultToolkit().getImage("images/game over.png");
    	MediaTracker t = new MediaTracker(this);
    	t.addImage(img1, 0);  
    	t.addImage(img2, 1);
    	try {
    		t.waitForAll();        
    	} catch(Exception e) {
    		System.out.println("程序异常");
    	}
    	//如果板的数量为0，游戏失败 (游戏失败后的画面待调整)
        if(Wood.getWoodNum() <= 0 ) {
            
        	g.clearRect(0, 0, windowWidth, windowHeight); 
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
        	g.drawImage(img2, 380, 288, null);
        	
        	renderThread.over();          //通知线程 游戏失败
            System.out.println("失败");
            return; 
        	
        }
        
        //如果数组列表只剩板和球，游戏成功 (游戏成功后的画面待调整)
        if (gameObjects.size() == 2) {
               	
        	g.clearRect(0, 0, windowWidth, windowHeight);
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
            g.drawImage(img1, 380, 288, null);
        	renderThread.victory();       //通知线程 游戏成功
            System.out.println("胜利");
            return;
        //g.drawImage(Toolkit.getDefaultToolkit().createImage("images/Ball.png"), 32, 32, null);

        //将内存画布的内容画回窗口上
       }
    }
    
    //public void paintVictory(Graphics g){
    //	Image img = 
    //}
    
    public int getGameObjectsSize() {
        return gameObjects.size();    	
    }
    
    public void clear(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, windowWidth, windowHeight);
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
        if (fps <= 0) 
        	return false;
        else {
            this.fps = fps;
            return true;
        }
    }
     
}
