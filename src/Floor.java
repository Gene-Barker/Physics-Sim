import java.awt.*;

public class Floor {
    int width = 2000;
    int depth = 50;
    int [] position = {0, 1080};

    public Floor() {

    }
    public void draw(Graphics2D graphics){
        graphics.fillRect((int) position[0], (int)position[1], width,depth);
    }
    public boolean checkCollisions(float[] entityPos, float [] entitySize){
        //Subroutine to check collisions with entites
        boolean collided = false;

        if ((entityPos[1] + entitySize[1] > position[1]) && ((position[0] < entityPos[0]) && (entityPos[0] < width))){
            collided = true;
        }

        return collided;
    }
}
