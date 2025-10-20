import java.awt.*;

public class Entity {

    int radius;
    float mass;
    float[] velocity;
    float[] momentum = new float[2];
    float[] position;
    final float time = (1/60f);

    //All of these variables are nesscessery for calculating drag
    final float gravity = 9.81f;
    final float density = 1000;
    final float frictionCoefficient = 0.47f;
    final float restitutionCoefficient = 0.87f;
    float[] force;
    float airDensity = 0.0009f;
    float[] drag = new float[2];
    float area;

    public Entity(float massPassed, float[] passedPosition){
        mass = massPassed;
        radius = (int) Math.cbrt(((3 * mass)/4 * density * Math.PI));
        velocity = new float[] {0,0};
        position = passedPosition; //Index 0 is X index 1 is Y
        area = (float) ( Math.PI * Math.pow(radius, 2));

    }

    public void draw(Graphics2D graphics){
        graphics.fillOval((int) position[0], (int)position[1], radius,radius);

    }
    public void update(Floor floor, Entity[] entitiesList){
        //Positive is down
        //Positive is right
        for (int i = 0; i < entitiesList.length; i++){
            checkCollisions(entitiesList[i].getPosition(), entitiesList[i].getRadius());
        }
        force = new float[] {0,0};
        drag[0] = (float) (0.5 * airDensity * Math.pow(velocity[0], 2) * frictionCoefficient * area);
        drag[1] = (float) (0.5 * airDensity * Math.pow(velocity[1], 2) * frictionCoefficient * area);
        force[1] = (gravity * mass) - drag[1];
        if (velocity[0] != 0){
            if (velocity[0] < 0){velocity[0] += drag[0];}
        }
            if (velocity[0] > 0){velocity[0] -= drag[0];}
        velocity[1] = velocity[1] + ((force[1] / mass) * time);
        position[1] += velocity[1];
        momentum[0] = velocity[0] * mass;
        momentum[1] = velocity[1] * mass;

        if (floor.checkCollisions(position, new float[] {radius,radius})){
            if (velocity[1] > 0) {
                momentum[1] = -momentum[1] * restitutionCoefficient;
                velocity[1] = momentum[1] / mass;
            }
            if ((position[1] + radius > floor.position[1])){
                this.position[1] = floor.position[1] - this.radius;
                System.out.println("fhfjdfsdfs");
            }
        }


    }
    public float [] getPosition(){
        return position;
    }

    public float getRadius(){
        return radius;
    }

    public boolean checkCollisions(float[] objectPos, float objectRadius){
        boolean collided = false;
        //If the positions are the exact same we can assume that they are the same object
        if (this.position == objectPos){
            collided = false;
        }
        //|| means or
        else if ((objectPos[0] <= (this.position[0] + this.radius)) ||  (objectPos[1] <= (this.position[1] + this.radius))) {
            collided = true;
        }
        //This code is so buns
        else if ((this.position[0] <= (objectPos[0] + objectRadius)) || (this.position[1] <= (objectPos[1] + objectRadius))) {
            collided = true;
        }

        return collided;
    }


}