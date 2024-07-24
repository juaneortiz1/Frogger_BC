package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

import java.awt.*;

/**
 * Represents an item on a lane in the Frogger game.
 * It defines properties and behaviors of lane items such as vehicles.
 */
public class LaneItem {

    double speed, x, y;
    int direction;
    int type;
    public static Rectangle boundingBox;

    /**
     * Constructor for the LaneItem class.
     *
     * @param speed The speed at which the lane item moves.
     * @param type The type of the lane item (e.g., vehicle type).
     * @param direction The direction in which the lane item is moving (left or right).
     * @param x The x-coordinate of the lane item.
     * @param y The y-coordinate of the lane item.
     */
    public LaneItem(double speed, int type, int direction, double x, double y) {
        this.speed = speed;
        this.type = type;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle((int) x + 3, (int) y + 5, getWidth() - 5, 29);
    }

    /**
     * Gets the x-coordinate of the lane item.
     *
     * @return The x-coordinate.
     */
    public double getX() {
        this.boundingBox = new Rectangle((int) x + 3, (int) this.y + 5, this.getWidth() - 5, 29);
        return x;
    }

    /**
     * Sets the x-coordinate of the lane item.
     *
     * @param x The new x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the lane item.
     *
     * @return The y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the direction in which the lane item is moving.
     *
     * @return The direction (left or right).
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Gets the type of the lane item.
     *
     * @return The type.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the speed of the lane item.
     *
     * @return The speed.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Gets the width of the lane item.
     *
     * @return The width.
     */
    int getWidth() {
        return 40;
    }

    /**
     * Updates the position of the lane item based on its speed and direction.
     */
    void update() {
        if (direction == Lane.RIGHT)
            setX(x + speed);
        else if (direction == Lane.LEFT)
            setX(x - speed);
    }

    /**
     * Gets the bounding box of the lane item.
     *
     * @return The bounding box as a Rectangle object.
     */
    public Rectangle getBoundingBox() {
        this.boundingBox = new Rectangle((int) x + 3, (int) this.y + 5, this.getWidth() - 5, 29);
        return this.boundingBox;
    }
}
