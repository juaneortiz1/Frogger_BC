package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.collision.CollisionDetector;

public class FroggerGame {

    public static final int PLAYING = 0, PLAYER_WINS = 2, MAX_LIFE_TIME = 80;
    public static boolean DEAD = false;
    public static boolean WIN = false;
    public static final int frogX = 320, frogY = 275;
    public static final int CarLaneInitialY = 75;

    int status, lives, startLifeTime;
    boolean reachedMiddle;
    Frog player;
    LogLane[] logLanes;
    CarLane[] carLanes;
    LilyPad[] lilyPadses;

    public FroggerGame() {
        status = FroggerGame.PLAYING;
        reachedMiddle = false;
        lives = 3;
        player = new Frog(frogX, frogY);

        //lilly pads------------------------
        lilyPadses = new LilyPad[4];
        lilyPadses[0] = new LilyPad(75, 30);
        lilyPadses[1] = new LilyPad(254, 30);
        lilyPadses[2] = new LilyPad(433, 30);
        lilyPadses[3] = new LilyPad(612, 30);

        //log and car lanes -------------
        carLanes = new CarLane[5];
        logLanes = new LogLane[1];
        logLanes[0] = new LogLane(1, Lane.RIGHT, 200);

        carLanes[0] = new CarLane(3, Lane.RIGHT, CarLaneInitialY + 0 * 40);
        carLanes[1] = new CarLane(3, Lane.LEFT, CarLaneInitialY + 1 * 40);
// carLanes[2] = null; // Si no quieres incluir un CarLane en esta posición
        carLanes[2] = new CarLane(3, Lane.RIGHT, CarLaneInitialY + 2 * 40);
        carLanes[4] = new CarLane(3, Lane.LEFT, CarLaneInitialY + 4 * 40);





        /*
        todo Sets the lifeTimer
        todo Creates all the lanes
        */

        for (int t = 0; t < 1000; t++) //calls update on all lanes before loading game
            update();
    }

    void update() {
        // Mover objetos en movimiento
        for (CarLane cl : carLanes) {
            if (cl != null) {
                cl.update();
            }
        }
        for (LogLane logLane : logLanes) {
            if (logLane != null) {
                logLane.update();
            }
        }

        // Verificar colisiones y otros eventos después de actualizar
        runChecks();
    }

    public int getStatus() {
        return status;
    }

    public int getLives() {
        return lives;
    }

    public Frog getPlayer() {
        return player;
    }

    public LogLane[] getLogLanes() {
        return logLanes;

    }

    public CarLane[] getCarLanes() {
        return carLanes;

    }

    public LilyPad[] getLilyPadses() {
        return lilyPadses;

    }

    public int getTimeLeft() {
        return MAX_LIFE_TIME - startLifeTime;
    }

    void playerDeath() {
        lives--;
        if (lives > 0) {
            player.setX(frogX);
            player.setY(frogY);
        }
        else {
            DEAD = true;
        }
    }

    void carCheck() {
        if (CollisionDetector.isCollision(this.getPlayer(), this.getCarLanes())) {
            playerDeath();
        }
    }


    void logCheck() {
        boolean onLogLane = false;

        // Verificar si la rana está sobre algún LogLane
        for (LogLane ll : logLanes) {
            if (ll != null && CollisionDetector.isOnLog(this.getPlayer(), new LogLane[] { ll })) {
                onLogLane = true;
                break;
            }
        }

        // Si la rana está en un LogLane pero no sobre ningún log, y está sobre el agua (y < 200), entonces muere
        if (onLogLane && !CollisionDetector.isOnLog(this.getPlayer(), this.getLogLanes()) && player.getY() < 200) {
            playerDeath();
        }


    }














    void turtleCheck() {
    }

    void lilyCheck() {

    }

    void checkifThePlayerWin(){
        if (this.player.getY() <= 60){
            WIN = true;
        }
    }

    void runChecks() {
        carCheck();
        logCheck();
        checkifThePlayerWin();

    }




}
