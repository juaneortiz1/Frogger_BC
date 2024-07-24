package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.collision.CollisionDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the state and logic of a Frogger game.
 * Handles game updates, collision detection, and player status.
 */
public class FroggerGame {

    public static final int PLAYING = 0, PLAYER_WINS = 2, MAX_LIFE_TIME = 80;
    public static boolean DEAD = false;
    public static boolean WIN = false;
    public static final int frogX = 320, frogY = 355;
    public static final int CarLaneInitialY = 75;

    private List<Frog> frogs; // List of frogs
    private int status, score, startLifeTime;
    private boolean reachedMiddle;
    private LogLane[] logLanes;
    private CarLane[] carLanes;
    private LilyPad[] lilyPadses;

    /**
     * Initializes a new game with default settings and objects.
     */
    public FroggerGame() {
        this.frogs = new ArrayList<>();
        this.frogs.add(new Frog(frogX, frogY)); // Default initial frog
        status = FroggerGame.PLAYING;
        reachedMiddle = false;
        score = 5000;

        // Initialize lily pads
        lilyPadses = new LilyPad[4];
        lilyPadses[0] = new LilyPad(75, 30);
        lilyPadses[1] = new LilyPad(254, 30);
        lilyPadses[2] = new LilyPad(433, 30);
        lilyPadses[3] = new LilyPad(612, 30);

        // Initialize car lanes
        carLanes = new CarLane[5];
        carLanes[0] = new CarLane(1, Lane.RIGHT, CarLaneInitialY + 0 * 40);
        carLanes[1] = new CarLane(1, Lane.LEFT, CarLaneInitialY + 1 * 40);
        carLanes[2] = new CarLane(1, Lane.RIGHT, CarLaneInitialY + 2 * 40);
        carLanes[3] = new CarLane(1, Lane.RIGHT, CarLaneInitialY + 3 * 40);
        carLanes[4] = new CarLane(1, Lane.LEFT, CarLaneInitialY + 4 * 40);

        // Initialize log lanes
        logLanes = new LogLane[1];
        logLanes[0] = new LogLane(1, Lane.RIGHT, 200);

        // Run updates
        for (int t = 0; t < 1000; t++) {
            update();
        }
    }

    /**
     * Updates the game state, including moving objects and checking for collisions.
     */
    public void update() {
        // Move objects
        if (carLanes != null) {
            for (CarLane cl : carLanes) {
                if (cl != null) {
                    cl.update();
                }
            }
        }
        if (logLanes != null) {
            for (LogLane logLane : logLanes) {
                if (logLane != null) {
                    logLane.update();
                }
            }
        }

        // Check for collisions and other events
        runChecks();
    }

    /**
     * Updates the positions of vehicles in the game.
     */
    public void updateVehicles() {
        for (CarLane carLane : carLanes) {
            for (Car car : carLane.getCars()) {
                car.update();
            }
        }
        for (LogLane logLane : logLanes) {
            for (Log log : logLane.getLogs()) {
                log.update();
            }
        }
    }

    /**
     * Gets the current status of the game.
     *
     * @return The game status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets the current score (lives) of the game.
     *
     * @return The current score.
     */
    public int getLives() {
        return score;
    }

    /**
     * Sets the score (lives) of the game.
     *
     * @param score The new score.
     */
    public void setLives(int score) {
        this.score = score;
    }

    /**
     * Gets the list of frogs in the game.
     *
     * @return A list of frogs.
     */
    public List<Frog> getFrogs() {
        return frogs;
    }

    /**
     * Gets the list of log lanes in the game.
     *
     * @return A list of log lanes.
     */
    public List<LogLane> getLogLanes() {
        return Arrays.asList(logLanes);
    }

    /**
     * Gets the list of car lanes in the game.
     *
     * @return A list of car lanes.
     */
    public List<CarLane> getCarLanes() {
        return Arrays.asList(carLanes);
    }

    /**
     * Gets the list of lily pads in the game.
     *
     * @return A list of lily pads.
     */
    public List<LilyPad> getLilyPadses() {
        return Arrays.asList(lilyPadses);
    }

    /**
     * Adds a frog to the game.
     *
     * @param frog The frog to add.
     */
    public void addFrog(Frog frog) {
        frogs.add(frog);
    }

    /**
     * Removes a frog from the game.
     *
     * @param frog The frog to remove.
     */
    public void removeFrog(Frog frog) {
        frogs.remove(frog);
    }

    /**
     * Gets the time left in the game.
     *
     * @return The time left.
     */
    public int getTimeLeft() {
        return MAX_LIFE_TIME - startLifeTime;
    }

    /**
     * Handles player death. Reduces score and resets frog position or ends the game.
     *
     * @param frog The frog that died.
     */
    public void playerDeath(Frog frog) {
        score--;
        if (score > 0) {
            frog.setX(frogX);
            frog.setY(frogY);
            frog.setDirection(3); // Initial direction, adjust as needed
        } else {
            FroggerGame.DEAD = true;
        }
    }

    /**
     * Checks for collisions between frogs and cars.
     */
    public void carCheck() {
        if (carLanes != null) {
            for (Frog frog : frogs) {
                boolean collided = CollisionDetector.isCollision(frog, this.getCarLanes().toArray(new CarLane[0]));
                if (collided) {
                    playerDeath(frog); // Only call playerDeath() if collision occurs
                }
            }
        }
    }

    /**
     * Checks if frogs are on log lanes and if they fall into the water.
     */
    public void logCheck() {
        if (logLanes != null) {
            for (Frog frog : frogs) {
                boolean onLogLane = false;
                // Check if frog is on any LogLane
                for (LogLane ll : logLanes) {
                    if (ll != null && CollisionDetector.isOnLog(frog, new LogLane[]{ll})) {
                        onLogLane = true;
                        break;
                    }
                }
                // If frog is on a LogLane but not on any log, and is on water (y < 200), it dies
                if (onLogLane && !CollisionDetector.isOnLog(frog, this.getLogLanes().toArray(new LogLane[0])) && frog.getY() < 200) {
                    playerDeath(frog);
                }
            }
        }
    }

    /**
     * Checks if the player has won the game.
     */
    public void checkifThePlayerWin() {
        for (Frog frog : frogs) {
            if (frog.getY() <= 60) {
                WIN = true;
                break;
            }
        }
    }

    /**
     * Checks if the game is over (either the player has died or won).
     *
     * @return True if the game is over, otherwise false.
     */
    public boolean isGameOver() {
        return DEAD || WIN;
    }

    /**
     * Runs all checks related to the game state, including collisions and win conditions.
     */
    void runChecks() {
        carCheck();
        logCheck();
        checkifThePlayerWin();
    }
}

