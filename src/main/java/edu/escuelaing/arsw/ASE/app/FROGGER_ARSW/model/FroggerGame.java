package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.collision.CollisionDetector;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FroggerGame {

    public static final int PLAYING = 0, PLAYER_WINS = 2, MAX_LIFE_TIME = 80;
    public static boolean DEAD = false;
    public static boolean WIN = false;
    public static final int frogX = 320, frogY = 275;
    public static final int CarLaneInitialY = 75;

    int status, lives, startLifeTime;
    boolean reachedMiddle;
    Frog frog;
    LogLane[] logLanes;
    CarLane[] carLanes;
    LilyPad[] lilyPadses;

    public FroggerGame() {
        status = FroggerGame.PLAYING;
        reachedMiddle = false;
        lives = 3;
        frog = new Frog(frogX, frogY);

        //lily pads
        lilyPadses = new LilyPad[4];
        lilyPadses[0] = new LilyPad(75, 30);
        lilyPadses[1] = new LilyPad(254, 30);
        lilyPadses[2] = new LilyPad(433, 30);
        lilyPadses[3] = new LilyPad(612, 30);

        //log and car lanes
        carLanes = new CarLane[5];
        carLanes[0] = new CarLane(2, Lane.RIGHT, CarLaneInitialY + 0 * 40);
        carLanes[1] = new CarLane(2, Lane.LEFT, CarLaneInitialY + 1 * 40);
        carLanes[2] = new CarLane(2, Lane.RIGHT, CarLaneInitialY + 2 * 40);
        carLanes[3] = new CarLane(2, Lane.RIGHT, CarLaneInitialY + 3 * 40); // Asegurar que no se omita ningún índice
        carLanes[4] = new CarLane(2, Lane.LEFT, CarLaneInitialY + 4 * 40);

        logLanes = new LogLane[1];
        logLanes[0] = new LogLane(1, Lane.RIGHT, 200);

        for (int t = 0; t < 1000; t++) {
            update();
        }
    }

    void update() {
        // Mover objetos en movimiento
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

        // Verificar colisiones y otros eventos después de actualizar
        runChecks();
    }

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


    public int getStatus() {
        return status;
    }

    public int getLives() {
        return lives;
    }

    public Frog getFrog() {
        return frog;
    }

    public List<LogLane> getLogLanes() {
        return Arrays.asList(logLanes);
    }

    public List<CarLane> getCarLanes() {
        return Arrays.asList(carLanes);
    }

    public List<LilyPad> getLilyPadses() {
        return Arrays.asList(lilyPadses);
    }

    public int getTimeLeft() {
        return MAX_LIFE_TIME - startLifeTime;
    }

    public void playerDeath() {
        lives--;
        if (lives > 0) {
            frog.setX(frogX);
            frog.setY(frogY);
            frog.setDirection(3); // Dirección inicial, ajusta según tu implementación
        } else {
            FroggerGame.DEAD = true;
        }
    }

    void carCheck() {
        if (carLanes != null) {
            boolean collided = CollisionDetector.isCollision(this.getFrog(), this.getCarLanes().toArray(new CarLane[0]));
            if (collided) {
                playerDeath(); // Llama a playerDeath() solo si hay colisión
            }
        }
    }

    void logCheck() {
        if (logLanes != null) {
            boolean onLogLane = false;
            // Verificar si la rana está sobre algún LogLane
            for (LogLane ll : logLanes) {
                if (ll != null && CollisionDetector.isOnLog(this.getFrog(), new LogLane[]{ll})) {
                    onLogLane = true;
                    break;
                }
            }
            // Si la rana está en un LogLane pero no sobre ningún log, y está sobre el agua (y < 200), entonces muere
            if (onLogLane && !CollisionDetector.isOnLog(this.getFrog(), this.getLogLanes().toArray(new LogLane[0])) && frog.getY() < 200) {
                playerDeath();
            }
        }
    }

    void turtleCheck() {
        // Implementación según sea necesario
    }

    void lilyCheck() {
        // Implementación según sea necesario
    }

    void checkifThePlayerWin() {
        if (this.frog.getY() <= 60) {
            WIN = true;
        }
    }

    public boolean isGameOver() {
        return DEAD || WIN;
    }

    void runChecks() {
        carCheck();
        logCheck();
        checkifThePlayerWin();
    }
}
