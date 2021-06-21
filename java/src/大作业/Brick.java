package ����ҵ;
import java.awt.*;

/**
 * ש
 */
public class Brick extends RectGameObject {
	
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
}
