import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private final int gameWidth = 1000;
    private final int gameHeight = 700;
    private final Dimension gameDimensiune = new Dimension(gameWidth, gameHeight);
    private final int jucatorWidth = 25;
    private final int jucatorHeight = 125;
    private final int diametruMinge = 25;

    private int scor1 = 0, scor2 = 0;

    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Jucator jucator1;
    private Jucator jucator2;
    private Minge minge;

    public GamePanel() {
        newJucator();
        newMinge();
        this.setFocusable(true);
        this.addKeyListener(new Al());
        this.setPreferredSize(gameDimensiune);

        gameThread = new Thread(this);
        gameThread.start();
    }
    protected void newMinge() {
        minge = new Minge((gameWidth / 2) - (diametruMinge / 2), (gameHeight / 2) - (diametruMinge / 2), diametruMinge, diametruMinge);
    }
    protected void newJucator() {
        jucator1 = new Jucator(0, (gameHeight / 2) - (jucatorHeight / 2), jucatorWidth, jucatorHeight, 1);
        jucator2 = new Jucator(gameWidth - jucatorWidth, (gameHeight / 2) - (jucatorHeight / 2), jucatorWidth, jucatorHeight, 2);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    private void draw(Graphics g) {
        jucator1.draw(g);
        jucator2.draw(g);
        minge.draw(g);
    }
    protected void move() {
        jucator1.move();
        jucator2.move();
        minge.move();
    }
    private void checkCollision() {
        if (minge.y <= 0) {
            minge.setYDir(-minge.yVelocity);
        }
        if (minge.y >= gameHeight - diametruMinge) {
            minge.setYDir(-minge.yVelocity);
        }

        if (minge.intersects(jucator1)) {
            minge.xVelocity = Math.abs(minge.xVelocity);
            /*Daca vrei sa maresti dificultatea jocului*/
//            {
//                minge.xVelocity++;
//            if (minge.yVelocity > 0)
//                minge.yVelocity++;
//            else
//                minge.yVelocity--;
//            }
            minge.setXDir(minge.xVelocity);
            minge.setYDir(minge.yVelocity);
        }
        if (minge.intersects(jucator2)) {
            minge.xVelocity = Math.abs(minge.xVelocity);
            /*Daca vrei sa maresti dificultatea jocului*/
//            {
//                minge.xVelocity++;
//                if (minge.yVelocity > 0)
//                    minge.yVelocity++;
//                else
//                    minge.yVelocity--;
//            }
            minge.setXDir(-minge.xVelocity);
            minge.setYDir(minge.yVelocity);
        }

        if (jucator1.y <= 0)
            jucator1.y = 0;
        if (jucator1.y >= (gameHeight - jucatorHeight))
            jucator1.y = gameHeight - jucatorHeight;
        if (jucator2.y <= 0)
            jucator2.y = 0;
        if (jucator2.y >= (gameHeight - jucatorHeight))
            jucator2.y = gameHeight - jucatorHeight;

        if (minge.x <= 0) {
            newJucator();
            newMinge();
            System.out.println("Player 2:" + ++scor2);
        }
        if (minge.x >= gameWidth - diametruMinge) {
            newJucator();
            newMinge();
            System.out.println("Player 1:" + ++scor1);
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class Al extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            jucator1.keyPressed(e);
            jucator2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            jucator1.keyReleased(e);
            jucator2.keyReleased(e);
        }

    }
}
