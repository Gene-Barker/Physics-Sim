import java.awt.*;

public class Floor {
    final int width = 2000;
    int depth = 50;
    int [] position = {0, 1000};

    public Floor() {

    }
    public void draw(Graphics2D graphics){
        graphics.fillRect((int) position[0], (int)position[1], width,depth);
    }
}
