package ����ҵ;
import java.awt.*;
import java.util.Random;

/**
 * ש 
 */
public class Brick extends RectGameObject {

	private static int producePropsNum = 0;//ש������������������ĵ��ߵ�����(Ϊ����ֻ��Ϊ0��1)
    protected Ball ball;
    public Brick(int x, int y, int width, int height) {
    	
    	//�� Ball�� ���ƣ�����׸��
        super(x, y, width, height);
        setFilePath("images/brick.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public void draw(Graphics g, Image img) {
        g.drawImage(img, x, y, null);
    }
    
    @Override
    public void onTick() {
        if (ball.getBegin()) {  //С���Ѿ�����
            /**
             * ���������Ϊ����ϵԭ�㣬ש����ĸ�����ͬһ�����ڣ�
             * ��ôֱ�ӽ��뾶��ƽ�����Ľǵ�Բ�ĵľ�����бȽ�
             */
            boolean flag = isSameQuadrand(ball.center, LU, RD);
            if (flag) { //��ͬһ������
                if ((LU.x-ball.center.x)*(LU.x-ball.center.x)+(LU.y-ball.center.y)*(LU.y-ball.center.y)<=17*17||(LD.x-ball.center.x)*(LD.x-ball.center.x)+(LD.y-ball.center.y)*(LD.y-ball.center.y)<=17*17||(RU.x-ball.center.x)*(RU.x-ball.center.x)+(RU.y-ball.center.y)*(RU.y-ball.center.y)<=17*17||(RD.x-ball.center.x)*(RD.x-ball.center.x)+(RD.y-ball.center.y)*(RD.y-ball.center.y)<=17*17) {
                    //��ײ
                    if (ball.getCrossBall() == false) { //������Ǵ�Խ�� �ҷ���ͬһ�����ж�
                        ball.setSpeedy(-ball.getSpeedy());  //����
                        ball.setSpeedx(-ball.getSpeedx());
                    }
                    this.remove();
                }
            } else {
                if (Math.abs(ball.center.x - this.center.x) <= 47 && Math.abs(ball.center.y - this.center.y) <= 32) {
                    //���Դ��·������Ϸ���ײ��ש��
                    int dx = Math.abs(ball.center.x - this.center.x - this.width / 2), dy = Math.abs(ball.center.y - this.center.y - this.height / 2);
                    if (ball.getCrossBall() == false) {
                        if (dx < dy) {
                            ball.setSpeedx(-ball.getSpeedx());
                        } else {
                            ball.setSpeedy(-ball.getSpeedy());
                        }
                        this.remove();
                    }
                }
            }
        }
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Ball getBall(Ball ball) {
        return ball;
    }
    
    //��ש������ʱ���Ե�����ߣ��÷����������һ�����߲����ظõ���
    public Props produceProps() {
    	
    		Random r = new Random();
    		int randomInt = r.nextInt(7);   //����[0,7)�ϵ�һ���������
    		
    		if(randomInt == 0) {
    			producePropsNum = 1;        //�������������Ϊ1 ��ͬ����׸�� 
    			Props p = new Props(center.x, center.y, 20, 70, "woodx0.5", "images/0.5props.png");
    			p.setWoodProps();           //�����õ����Ƕ�ľ�������õĵ��� ��ͬ
    			return p;
    		} else if (randomInt == 1){
    			producePropsNum = 1;
    			Props p = new Props(center.x, center.y, 20, 70, "woodx0.75", "images/0.75props.png");
    			p.setWoodProps();
    			return p;
    	    }  else if (randomInt == 2){
    			producePropsNum = 1;
    			Props p = new Props(center.x, center.y, 20, 70, "woodx1.5", "images/1.5props.png");
    			p.setWoodProps();
    			return p;
    	    } else if(randomInt == 3){
    			producePropsNum = 1;
    			Props p = new Props(center.x, center.y, 20, 70, "woodx2", "images/2props.png");
    			p.setWoodProps();
    			return p;
    	    }	else if(randomInt == 4){
    			producePropsNum = 1;
    			Props p = new Props(center.x, center.y, 24, 40, "death", "images/death.png");
    			p.setWoodProps();
    			return p;
    	    }   else if(randomInt == 5) {
    	    	producePropsNum = 1;
    			Props p = new Props(center.x, center.y, 24, 40, "smallBall", "images/smallBallProps.png");
    			p.setBallProps();
    			return p;
    	    }   else if(randomInt == 6){
    	    	producePropsNum = 1;
    			Props p = new Props(center.x, center.y, 24, 40, "bigBall", "images/bigBallProps.png");
    			p.setBallProps();
    			return p;
    	    }   else {
    	    	return null;
    	    }
    	    //�Կ������������
    }
    
    public static void setProducePropsNum(int num) {
    	producePropsNum = num;        
    }
    
    public static int getProducePropsNum() {
    	return producePropsNum;
    }
}
