package 大作业;
import java.awt.*;
import java.util.Random;

/**
 * 砖 
 */
public class Brick extends RectGameObject {

	private static int producePropsNum = 0;//砖类所产生的仍在下落的道具的数量(为方便只能为0或1)
    protected Ball ball;
    public Brick(int x, int y, int width, int height) {
    	
    	//与 Ball类 类似，不再赘述
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
        if (ball.getBegin()) {  //小球已经发射
            /**
             * 如果以球心为坐标系原点，砖块的四个角在同一象限内，
             * 那么直接将半径的平方与四角到圆心的距离进行比较
             */
            boolean flag = isSameQuadrand(ball.center, LU, RD);
            if (flag) { //在同一象限内
                if ((LU.x-ball.center.x)*(LU.x-ball.center.x)+(LU.y-ball.center.y)*(LU.y-ball.center.y)<=17*17||(LD.x-ball.center.x)*(LD.x-ball.center.x)+(LD.y-ball.center.y)*(LD.y-ball.center.y)<=17*17||(RU.x-ball.center.x)*(RU.x-ball.center.x)+(RU.y-ball.center.y)*(RU.y-ball.center.y)<=17*17||(RD.x-ball.center.x)*(RD.x-ball.center.x)+(RD.y-ball.center.y)*(RD.y-ball.center.y)<=17*17) {
                    //碰撞
                    if (ball.getCrossBall() == false) { //如果不是穿越球 且符合同一象限判定
                        ball.setSpeedy(-ball.getSpeedy());  //反弹
                        ball.setSpeedx(-ball.getSpeedx());
                    }
                    this.remove();
                }
            } else {
                if (Math.abs(ball.center.x - this.center.x) <= 47 && Math.abs(ball.center.y - this.center.y) <= 32) {
                    //可以从下方或是上方碰撞该砖块
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
    
    //当砖被击碎时可以掉落道具，该方法随机生成一个道具并返回该道具
    public Props produceProps() {
    	
    		Random r = new Random();
    		int randomInt = r.nextInt(7);   //生成[0,7)上的一个随机整数
    		
    		if(randomInt == 0) {
    			producePropsNum = 1;        //下落道具数量置为1 下同不再赘述 
    			Props p = new Props(center.x, center.y, 20, 70, "woodx0.5", "images/0.5props.png");
    			p.setWoodProps();           //声明该道具是对木板起作用的道具 下同
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
    	    //仍可添加其他道具
    }
    
    public static void setProducePropsNum(int num) {
    	producePropsNum = num;        
    }
    
    public static int getProducePropsNum() {
    	return producePropsNum;
    }
}
