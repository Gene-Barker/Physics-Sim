import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Panel extends JPanel implements Runnable{
    //Thread is used for the game loop
    Thread gameThread;
    int fps = 60;
    //List containg all the entited on screen
    Entity [] entities = new Entity[0];

    Floor floor;

    //Listens to the mouse
    MouseListener mouseListener;

    public Panel(){
        //Setting size and visablity and such
        setSize(1920, 1080);
        setVisible(true);

        floor = new Floor();

        mouseListener = new MouseListener() {
            //Setting up all events for the mouse being pressed
            @Override
            public void mouseClicked(MouseEvent e) {
                //When the mouse is clicked, a new sphere is created
                entities = addEntity(entities, 50, new float[] {e.getX(), e.getY()});
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        this.addMouseListener(mouseListener);

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

        for (int i = 0; i < entities.length; i++) {entities[i].update(floor);}
    }
    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        for (int i = 0; i <entities.length; i++){entities[i].draw(graphics);}

        floor.draw(graphics);

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
