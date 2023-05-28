import java.awt.*;
import java.util.Random;

public class Minge extends Rectangle {
    private Random random;
    public int xVelocity, yVelocity;
    private int viteza=5;
    Minge(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();

        int randXDir = random.nextInt(2);
        if (randXDir == 0)
            //randXDir--;
        setXDir(viteza);
        else setXDir(-viteza);

        int randYDir = random.nextInt(2);
        if (randYDir == 0)
            //randYDir--;
        setYDir(viteza);
        else setYDir(-viteza);
    }
    public void setXDir(int randx) {
        xVelocity = randx;
    }
    public void setYDir(int randy) {
        yVelocity = randy;
    }
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }
}
