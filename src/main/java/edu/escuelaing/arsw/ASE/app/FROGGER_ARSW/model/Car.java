package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

/**
 * Represents a car on a lane in the Frogger game.
 * Extends the `LaneItem` class to include specific behavior and attributes for cars.
 */
public class Car extends LaneItem {

    // Car type constants
    public static final int SEMI = 0, LIMO = 1, CAR_1 = 2, CAR_2 = 3;

    /**
     * Constructor for the Car class.
     *
     * @param speed The speed at which the car moves.
     * @param type The type of the car (e.g., SEMI, LIMO, CAR_1, CAR_2).
     * @param direction The direction in which the car is moving (left or right).
     * @param x The x-coordinate of the car.
     * @param y The y-coordinate of the car.
     */
    public Car(double speed, int type, int direction, double x, double y) {
        super(speed, type, direction, x, y);
    }

    /**
     * Gets the width of the car based on its type.
     *
     * @return The width of the car. The width depends on the type:
     *         - CAR_1: 40 units
     *         - CAR_2: 40 units
     *         - SEMI: 120 units
     *         - LIMO: 80 units
     */
    @Override
    public int getWidth() {
        if (type == CAR_1)
            return 40;
        if (type == CAR_2)
            return 40;
        if (type == SEMI)
            return 120;
        if (type == LIMO)
            return 80;
        return 0;
    }
}

