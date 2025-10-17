import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel implements Runnable{
    Thread gameThread;
    int fps = 60;
    Entity [] entities = new Entity[1];

    public Panel(){
        setSize(1920, 1080);
        setVisible(true);

        entities[0] = new Entity(40,40, 2);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/fps; // Calculates time for each frame in nano seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){
            //Used to set frames per seconds



            update();

            repaint();




            try {
                double remainingTime = (nextDrawTime - System.nanoTime());
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
    public void update(){

        for (int i = 0; i < entities.length; i++) {entities[i].update();}
    }
    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        for (int i = 0; i <entities.length; i++){ entities[i].draw(graphics);}

        graphics.dispose();

        Toolkit.getDefaultToolkit().sync();

    }
}
