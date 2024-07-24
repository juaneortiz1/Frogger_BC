package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

import java.util.ArrayList;

/**
 * Represents a lane in the Frogger game.
 * Each lane contains items that move across it, such as cars or logs.
 */
public class Lane {

    // Direction constants
    public static final int LEFT = 0, RIGHT = 1;

    int y; // The y-coordinate of the lane
    int direction; // The direction in which items on the lane move
    double speed; // The speed at which items on the lane move
    ArrayList<LaneItem> laneItems = new ArrayList<LaneItem>(); // List of items on the lane

    /**
     * Constructor for the Lane class.
     *
     * @param speed The speed at which items on the lane move.
     * @param direction The direction in which items on the lane move (left or right).
     * @param y The y-coordinate of the lane.
     */
    public Lane(double speed, int direction, int y) {
        this.speed = speed;
        this.direction = direction;
        this.y = y;
    }

    /**
     * Gets the y-coordinate of the lane.
     *
     * @return The y-coordinate of the lane.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the direction in which items on the lane move.
     *
     * @return The direction (LEFT or RIGHT) in which items on the lane move.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Gets the speed at which items on the lane move.
     *
     * @return The speed of the lane items.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Gets the list of items on the lane.
     *
     * @return The list of items on the lane.
     */
    public ArrayList<LaneItem> getLaneItems() {
        return laneItems;
    }

    /**
     * Updates the position of all items on the lane based on their speed and direction.
     */
    void update() {
        for (LaneItem laneItem : laneItems) {
            laneItem.update();
        }
    }
}
