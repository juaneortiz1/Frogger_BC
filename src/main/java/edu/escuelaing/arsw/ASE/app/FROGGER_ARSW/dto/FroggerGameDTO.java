package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto;

import java.util.List;

public class FroggerGameDTO {
    private List<FrogDTO> frogs;
    private List<CarLaneDTO> carLanes;
    private List<LogLaneDTO> logLanes;
    private List<LilyPadDTO> lilyPads;
    private int score;
    private int level;
    private boolean gameOver;
    private int lives; // Atributo para almacenar el número de vidas restantes


    public List<FrogDTO> getFrogs() {
        return frogs;
    }

    public void setFrogs(List<FrogDTO> frogs) {
        this.frogs = frogs;
    }


    public List<CarLaneDTO> getCarLanes() {
        return carLanes;
    }

    public void setCarLanes(List<CarLaneDTO> carLanes) {
        this.carLanes = carLanes;
    }

    public List<LogLaneDTO> getLogLanes() {
        return logLanes;
    }

    public void setLogLanes(List<LogLaneDTO> logLanes) {
        this.logLanes = logLanes;
    }

    public List<LilyPadDTO> getLilyPads() {
        return lilyPads;
    }

    public void setLilyPads(List<LilyPadDTO> lilyPads) {
        this.lilyPads = lilyPads;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int score) {
        this.lives = score;
    }
}
