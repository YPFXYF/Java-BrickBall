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
    public static Image []images = new Image[15];
    private int windowWidth;
    private int windowHeight;
    private String windowTitle;
    private Wood wood;
    private Ball ball;
    private int fps;                               //ÿ����µ�֡��
    private RenderThread renderThread;             //��Ⱦ�߳�
    private ArrayList<GameObject> gameObjects;     //������Ϸ����������б�
    private Graphics tempGraphics;                 //ֱ�ӽ������ڴ��еĻ���(����˫����)
    Props props = null;                            //(�����)������ ���߶���
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
        
        //����ש��
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
                if(go instanceof Brick) {                      //�жϸö����Ƿ���ש���һ��ʵ��
                	Brick aBrick = (Brick)go;                  //����ǣ�ǿ������ת��
                	if(aBrick.getProducePropsNum() == 0) {     //���ש��������������������Ϊ0 ����Բ������ߣ����򲻿���
                		props = aBrick.produceProps();
                		props.setWood(wood);
                		props.setBall(ball);
                		ball.setProps(props);
                		wood.setProps(props);               		
                	}
                }     	
            }
        }
        
        //��������������� ��props��Ϊ�� ������
        if(props != null) {
       	    Image img2 = loadPicture(props.getFilePath());
            props.onTick();
        	props.draw(tempGraphics, img2);
         }
     
        //���props��Ϊ�� �� props��ľ��ӵ����䵽ľ������(��ʱprops.isRemovedΪtrue) ��props����(��������ɾ��)
        if(props != null && props.isRemoved) {
        	props = null;
        	Brick.setProducePropsNum(0);
         }
        
        //���ڴ滭�������ݻ��ش�����
        g.drawImage(img, 0, 0, null);
        
    
    	//����������Ϊ0����Ϸʧ�� (��Ϸʧ�ܺ�Ļ��������)
        if(Wood.getWoodNum() <= 0 ) {
            
        	g.clearRect(0, 0, windowWidth, windowHeight); 
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
        	g.drawImage(images[1], 380, 288, null);
        	
        	renderThread.over();          //֪ͨ�߳� ��Ϸʧ��
            System.out.println("ʧ��");
            return; 
        	
        }
        
        //��������б�ֻʣ�������Ϸ�ɹ� (��Ϸ�ɹ���Ļ��������)
        if (gameObjects.size() == 2) {
               	
        	g.clearRect(0, 0, windowWidth, windowHeight);
        	g.setColor(Color.WHITE);
        	g.fillRect(0, 0, windowWidth, windowHeight);
            g.drawImage(images[0], 380, 288, null);
        	renderThread.victory();       //֪ͨ�߳� ��Ϸ�ɹ�
            System.out.println("ʤ��");
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
     
    //Ϊ��� drawImage����ʱ�ż���ͼƬ�����ܵ���ͼƬ����ʾ ����������������룬����Ҫ���õ�ͼƬ�����ظ�ͼƬ
    public Image loadPicture(String filePath) {
    	
     	Image img = Toolkit.getDefaultToolkit().getImage(filePath);
    	MediaTracker t = new MediaTracker(this); //this ָ��ǰ����
    	t.addImage(img, 0);         //�ڶ�����������Ϊһ��id �޹ؽ�Ҫ
    	try {
    		t.waitForAll();         //�ȴ�����ͼƬ������� ����Ż�����ִ��
    	} catch(Exception e) {
    		System.out.println("�����쳣");
    	}
    
    	return img;
    }
    
    //��������ͼƬ
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
