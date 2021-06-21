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
    public static Image []images = new Image[15];
    private int windowWidth;
    private int windowHeight;
    private String windowTitle;
    private Wood wood;
    private Ball ball;
    private int fps;                               //每秒更新的帧数
    private RenderThread renderThread;             //渲染线程
    private ArrayList<GameObject> gameObjects;     //储存游戏对象的数组列表
    private Graphics tempGraphics;                 //直接建立在内存中的画布(用于双缓冲)
    Props props = null;                            //(掉落的)道具类 道具对象
    RectGameObject s0, s1, s2;
    
    public Game(int windowWidth, int windowHeight, String windowTitle, int fps) {
    	
        gameObjects = new ArrayList<GameObject>();
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.windowTitle = windowTitle;
        this.fps = fps;
        
        wood = new Wood();
        gameObjects.add(wood);
        
        //s0 = new RectGameObject(32, 100, 32, 32);
        //s0.setFilePath("images/Ball.png");
        //gameObjects.add(s0);
        
        ball = new Ball();
        ball.setWood(wood);
        gameObjects.add(ball);
        wood.setBall(ball);
        
        //生成砖块
        for (int i = 1; i <= 16; i++) {
            for (int j = 1; j <= 1; j++) {
                Brick brick = new Brick((i-1)*63+1, j*32 + 100, 60, 30);
                brick.setBall(ball);
                gameObjects.add(brick);
            }
        }
              
        loadAllpicture();
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
                if(go instanceof Brick) {                      //判断该对象是否是砖类的一个实例
                	Brick aBrick = (Brick)go;                  //如果是，强制类型转换
                	if(aBrick.getProducePropsNum() == 0) {     //如果砖类产生的正在下落的数量为0 则可以产生道具，否则不可以
                		props = aBrick.produceProps();
                		props.setWood(wood);
                		props.setBall(ball);
                		ball.setProps(props);
                		wood.setProps(props);               		
                	}
                }     	
            }
        }
        
        //如果道具仍在下落 则props不为空 将其绘出
        if(props != null) {
       	    Image img2 = loadPicture(props.getFilePath());
            props.onTick();
        	props.draw(tempGraphics, img2);
         }
     
        //如果props不为空 但 props被木板接到或落到木板以下(此时props.isRemoved为true) 将props悬空(即将道具删除)
        if(props != null && props.isRemoved) {
        	props = null;
        	Brick.setProducePropsNum(0);
         }
        
        //将内存画布的内容画回窗口上
        g.drawImage(img, 0, 0, null);
        
    
    	//如果板的数量为0，游戏失败 (游戏失败后的画面待调整)
        if(Wood.getWoodNum() <= 0 ) {
            
        	g.clearRect(0, 0, windowWidth, windowHeight); 
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
        	g.drawImage(images[1], 380, 288, null);
        	
        	renderThread.over();          //通知线程 游戏失败
            System.out.println("失败");
            return; 
        	
        }
        
        //如果数组列表只剩板和球，游戏成功 (游戏成功后的画面待调整)
        if (gameObjects.size() == 2) {
               	
        	g.clearRect(0, 0, windowWidth, windowHeight);
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
            g.drawImage(images[0], 380, 288, null);
        	renderThread.victory();       //通知线程 游戏成功
            System.out.println("胜利");
            return;
      
       }
    }
    
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
     
    //为解决 drawImage调用时才加载图片而可能导致图片不显示 的问题采用下述代码，加载要调用的图片并返回该图片
    public Image loadPicture(String filePath) {
    	
     	Image img = Toolkit.getDefaultToolkit().getImage(filePath);
    	MediaTracker t = new MediaTracker(this); //this 指向当前容器
    	t.addImage(img, 0);         //第二个参数仅仅为一个id 无关紧要
    	try {
    		t.waitForAll();         //等待所有图片加载完毕 程序才会往下执行
    	} catch(Exception e) {
    		System.out.println("程序异常");
    	}
    
    	return img;
    }
    
    //加载所有图片
    public void loadAllpicture() {
 
        images[0] = loadPicture("images/victory.png");
        images[1] = loadPicture("images/game over.png");
        images[2] = loadPicture("images/0.5prors.png");
        images[3] = loadPicture("images/0.75prors.png");
        images[4] = loadPicture("images/1.5prors.png");
        images[5] = loadPicture("images/2prors.png");
        images[6] = loadPicture("images/death.png");
        images[7] = loadPicture("images/woodx0.5.png");
        images[8] = loadPicture("images/woodx0.75.png");
        images[9] = loadPicture("images/woodx1.5.png");
        images[10] = loadPicture("images/woodx2.png");
        images[11] = loadPicture("images/smallBallProps.png");
        images[12] = loadPicture("images/bigBallProps.png");
        images[13] = loadPicture("images/smallBall.png");
        images[14] = loadPicture("images/bigBall.png");
    }
}
