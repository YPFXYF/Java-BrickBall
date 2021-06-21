package ����ҵ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

//���̼�����
public class Input implements KeyListener {
	
    private static HashMap<Integer, Boolean> keys;  //��HashMap�洢 ������ֵ ���� ��Ӧ��״̬
    public final static int KEY_COUNTS = 256;
    
    public void init() {
        keys = new HashMap<Integer, Boolean>(KEY_COUNTS); //����256������HashMap
        for (int i = 0; i < KEY_COUNTS; i++) {            
            keys.put(i, false);
        }
    }

    /**
     * �а�������ʱ���ö�Ӧ״̬Ϊtrue���ɿ�ʱ��Ϊfalse��
     * @param e �����¼�
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys.put(e.getKeyCode(), false);
    }

    public static boolean getKeyDown(int keyCode) {
        return keys.get(keyCode);
    }
}
