import java.awt.*;

public class Entity {

    int width;
    int length;
    float mass;
    float[] velocity;
    float[] momentum = new float[2];
    float[] position;
    final float time = (1/60f);
    final float gravity = 9.81f;

    public Entity(int widthPassed, int lengthPassed, float massPassed){
        mass = massPassed;
        width = widthPassed;
        length = lengthPassed;
        velocity = new float[] {0,0};
        position = new float[] {200,0}; //Index 0 is X index 1 is Y

    }

    public void draw(Graphics2D graphics){
        graphics.fillOval((int) position[0], (int)position[1], 100,100);

    }
    public void update(){
        //Positive is down
        //Positive is right
        velocity[1] = velocity[1] + (gravity * time);
        position[1] += velocity[1];
        momentum[0] = velocity[0] * mass;
        momentum[1] = velocity[1] * mass;
    }
    public float [] getPosition(){
        return position;
    }

    public void checkCollisions(){

    }


}