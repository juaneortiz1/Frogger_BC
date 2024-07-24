package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a lane specifically for cars in the Frogger game.
 * Extends the {@link Lane} class to handle car-specific lane behavior.
 */
public class CarLane extends Lane {

    /**
     * Constructor for the CarLane class.
     *
     * @param speed The speed at which cars on the lane move.
     * @param direction The direction in which cars on the lane move (left or right).
     * @param y The y-coordinate of the lane.
     */
    public CarLane(double speed, int direction, int y) {
        super(speed, direction, y);
    }

    /**
     * Gets the list of cars currently on the lane.
     *
     * @return A list of {@link Car} objects on the lane.
     */
    public List<Car> getCars() {
        List<Car> carsInLane = new ArrayList<>();
        for (LaneItem item : laneItems) {
            if (item instanceof Car) {
                carsInLane.add((Car) item);
            }
        }
        return carsInLane;
    }

    /**
     * Updates the position of all cars on the lane and handles car spawning and removal.
     */
    @Override
    void update() {
        super.update();
        int carType = (int) (Math.random() * 4);
        int length;

        // Determine the length of the car based on its type
        switch (carType) {
            case Car.SEMI: // Semi-truck
                length = 120;
                break;
            case Car.LIMO: // Limo
                length = 80;
                break;
            default: // Other cars
                length = 40;
                break;
        }

        // Handle cars moving to the right
        if (direction == RIGHT) {
            int location = -120 - (int) (Math.random() * 49) - length;
            if (laneItems.size() == 0) {
                laneItems.add(new Car(speed, (int) (Math.random() * 4), RIGHT, location, y));
            }
            for (int i = 0; i < laneItems.size(); i++) {
                // Remove cars that have moved off the screen
                if (laneItems.get(i).getDirection() == LEFT && laneItems.get(i).getX() < -20) laneItems.remove(i);
                if (laneItems.get(i).getDirection() == RIGHT && laneItems.get(i).getX() > 720) laneItems.remove(i);
            }
            // Add a new car if needed
            if ((int) laneItems.get(laneItems.size() - 1).getX() + laneItems.get(laneItems.size() - 1).getWidth() > 0) {
                Car newCar = new Car(speed, carType, RIGHT, location, y);
                newCar.setX(location - newCar.getWidth());
                laneItems.add(newCar);
            }
        }
        // Handle cars moving to the left
        else if (direction == LEFT) {
            int location = 700 + (int) (Math.random() * 49) + length; // Set location of car to spawn
            if (laneItems.size() == 0) {
                laneItems.add(new Car(speed, (int) (Math.random() * 4), LEFT, location, y));
            }
            for (int i = 0; i < laneItems.size(); i++) {
                // Remove cars that have moved off the screen
                if (laneItems.get(i).getDirection() == RIGHT && laneItems.get(i).getX() > 720) laneItems.remove(i);
            }
            // Add a new car if needed
            if ((int) laneItems.get(laneItems.size() - 1).getX() + laneItems.get(laneItems.size() - 1).getWidth() < 700) {
                laneItems.add(new Car(speed, carType, LEFT, location, y));
            }
        }
    }
}
