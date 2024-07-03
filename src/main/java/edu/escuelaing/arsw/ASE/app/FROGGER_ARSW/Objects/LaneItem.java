package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects;

import java.awt.*;

public class LaneItem {

    double speed, x, y;
    int direction;
    int type;
    public static Rectangle boundingBox;

    public LaneItem(double speed, int type, int direction, double x, double y) {
        this.speed = speed;
        this.type = type;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle((int) x + 3, (int) y + 5, getWidth() - 5, 29);
    }

    public double getX() {
        this.boundingBox = new Rectangle((int) x + 3, (int) this.y + 5, this.getWidth() - 5, 29);
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    int getWidth() {
        return 40;
    }

    void update() {
        if (direction == Lane.RIGHT)
            setX(x + speed);
        else if (direction == Lane.LEFT)
            setX(x - speed);
    }

    public Rectangle getBoundingBox() {
        this.boundingBox = new Rectangle((int) x + 3, (int) this.y + 5, this.getWidth() - 5, 29);
        return this.boundingBox;
    }
}

