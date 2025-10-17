import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel implements Runnable{
    //Thread is used for the game loop
    Thread gameThread;
    int fps = 60;
    //List containg all the entited on screen
    Entity [] entities = new Entity[0];

    public Panel(){
        //Setting size and visablity and such
        setSize(1920, 1080);
        setVisible(true);
        entities = addEntity(entities, 2, new float[] {67,200});
        entities = addEntity(entities, 2, new float[] {200,300});
        System.out.println(entities[0]);
        System.out.println(entities[1]);

    }

    public void startGameThread(){
        //Starts the game loop
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
                //I forgot a 0 here and it messed up the whole program. Don't do that again
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                //Sleeps for as long as it needs too in order to have a consistant FPS
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

        for (int i = 0; i <entities.length; i++){entities[i].draw(graphics);}

        graphics.dispose();

        Toolkit.getDefaultToolkit().sync();

    }
    public Entity[] addEntity(Entity[] currentEntities, int massPassed, float[] positionPassed){
        //Makes a new list which is one longer then the current list
        //Could use array lists but they are stupid
        Entity[] newEntities = new Entity[currentEntities.length + 1];

        //Copy the current list to the new list
        for (int i = 0; i < currentEntities.length; i++){
            newEntities[i] = currentEntities[i];
        }

        //Add the new entity
        newEntities[newEntities.length - 1] = new Entity(massPassed, positionPassed);

        //Returns the array
        return newEntities;
    }
}
