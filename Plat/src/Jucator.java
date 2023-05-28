import java.awt.*;
import java.awt.event.KeyEvent;

public class Jucator extends Rectangle {
    private int id;
    private int yVelocity;
    private final int vitezaJucator = 10;
    public Jucator(int x, int y, int width, int height, int id) {
        super(x, y, width, height);
        this.id = id;
    }
    public void move() {
        y+=yVelocity;
    }
    public void setYDir(int y) {
        yVelocity = y;
    }
    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDir(-vitezaJucator);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDir(vitezaJucator);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDir(-vitezaJucator);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDir(vitezaJucator);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDir(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDir(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDir(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDir(0);
                    move();
                }
                break;
        }
    }
    public void draw(Graphics g) {
        if (id == 1)
            g.setColor(Color.yellow);
        else
            g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}
