import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Input implements KeyListener {
    private static HashMap<Integer, Boolean> keys;
    public final static int KEY_COUNTS = 256;

    public void init() {
        keys = new HashMap<Integer, Boolean>(KEY_COUNTS);
        for (int i = 0; i < KEY_COUNTS; i++) {
            keys.put(i, false);
        }
    }

    /**
     * 有按键按下时设置对应状态为true，松开时改为false。
     * @param e 键盘事件
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
