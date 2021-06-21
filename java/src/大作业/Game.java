package ����ҵ;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
������������Ϸ����Java�Դ��ؼ���Ч�ʺܵͣ�������Ҫ�½�һ���࣬��дJFrame��paint������
�Լ���ͼ
*/
public class Game extends JFrame {
	
    //private int score, score0, score1, score2;

    private int windowWidth;
    private int windowHeight;
    private String windowTitle;
    private int fps;                               //ÿ����µ�֡��
    private RenderThread renderThread;             //��Ⱦ�߳�
    private ArrayList<GameObject> gameObjects;     //������Ϸ����������б�
    private Graphics tempGraphics;                 //ֱ�ӽ������ڴ��еĻ���(����˫����)
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
        
        //����ש��
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
 
        this.addKeyListener(input);             //��Ӽ��̼�����
    }

    private void createWindow() {
    	
        setSize(windowWidth, windowHeight); //�̳���JFrame
        setTitle(windowTitle);
        setResizable(false);                    //���ô��ڲ��ɷŴ���С
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
    
    //��дpaint����
    @Override
    public void paint(Graphics g) { //ʹ��˫��������˸����
    	
        //���ڴ����һ���ʹ��ڴ�Сһ�µ�ͼ
        Image img = this.createImage(windowWidth, windowHeight);
        tempGraphics = img.getGraphics();   //
        clear(tempGraphics);   //�����һ֡������
        
        //��Ⱦ����sprite
        for (GameObject gameObject : gameObjects) {
            gameObject.onTick();
            gameObject.draw(tempGraphics);
        }
        
        //�ж������б��е�ש���Ƿ���Ҫ���
        for (GameObject go: gameObjects) {
            if (go.isRemoved) {
                gameObjects.remove(go);
                //if(go instanceof Brick)
                	//score += 1;
            }
        }
        
        g.drawImage(img, 0, 0, null);
        
        //Ϊ��� drawImage����ʱ�ż���ͼƬ�����ܵ���ͼƬ����ʾ ����������������룬����Ҫ���õ�ͼƬ
     	Image img1 = Toolkit.getDefaultToolkit().getImage("images/victory.png");
     	Image img2 = Toolkit.getDefaultToolkit().getImage("images/game over.png");
    	MediaTracker t = new MediaTracker(this);
    	t.addImage(img1, 0);  
    	t.addImage(img2, 1);
    	try {
    		t.waitForAll();        
    	} catch(Exception e) {
    		System.out.println("�����쳣");
    	}
    	//����������Ϊ0����Ϸʧ�� (��Ϸʧ�ܺ�Ļ��������)
        if(Wood.getWoodNum() <= 0 ) {
            
        	g.clearRect(0, 0, windowWidth, windowHeight); 
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
        	g.drawImage(img2, 380, 288, null);
        	
        	renderThread.over();          //֪ͨ�߳� ��Ϸʧ��
            System.out.println("ʧ��");
            return; 
        	
        }
        
        //��������б�ֻʣ�������Ϸ�ɹ� (��Ϸ�ɹ���Ļ��������)
        if (gameObjects.size() == 2) {
               	
        	g.clearRect(0, 0, windowWidth, windowHeight);
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
            g.drawImage(img1, 380, 288, null);
        	renderThread.victory();       //֪ͨ�߳� ��Ϸ�ɹ�
            System.out.println("ʤ��");
            return;
        //g.drawImage(Toolkit.getDefaultToolkit().createImage("images/Ball.png"), 32, 32, null);

        //���ڴ滭�������ݻ��ش�����
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

    public void exit() {    //�˳���Ϸ
        System.exit(1);
    }

    public int getFps() {
        return fps;
    }
    
    /**
    * ����fps,����ֵ��ʾ�Ƿ����óɹ�
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
