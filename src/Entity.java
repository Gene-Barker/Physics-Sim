import java.awt.*;

public class Entity {

    int radius;
    float mass;
    float[] velocity;
    float[] momentum = new float[2];
    float[] position;
    final float time = (1/60f);
    final float gravity = 9.81f;
    final float density = 1000;

    public Entity(float massPassed, float[] passedPosition){
        mass = massPassed;
        radius = (int) Math.cbrt(((3 * mass)/4 * density * Math.PI));
        velocity = new float[] {0,0};
        position = passedPosition; //Index 0 is X index 1 is Y

    }

    public void draw(Graphics2D graphics){
        graphics.fillOval((int) position[0], (int)position[1], radius,radius);

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

    public void checkCollisions(float objectPos){

    }


}