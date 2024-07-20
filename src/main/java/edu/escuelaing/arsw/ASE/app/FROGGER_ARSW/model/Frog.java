package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model;

import java.awt.*;

public class Frog {

    public static final int LEFT = 0, RIGHT = 1, DOWN = 2, UP = 3, WIDTH = 40;
    private Rectangle boundingBox;
    private int x, y, direction;


    public Frog(int x, int y) {
        direction = UP;
        this.x = x;
        this.y = y;
        this.boundingBox = new Rectangle(this.x + 7, this.y + 9, 24, 24);
    }

    public void move(int direction) {
        this.direction = direction;
        switch (direction) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                break;
        }
    }

    private void moveLeft() {
        x -= WIDTH; // Adjust according to your game's logic
        updateBoundingBox();
    }

    private void moveRight() {
        x += WIDTH; // Adjust according to your game's logic
        updateBoundingBox();
    }

    private void moveUp() {
        y -= WIDTH; // Adjust according to your game's logic
        updateBoundingBox();
    }

    private void moveDown() {
        y += WIDTH; // Adjust according to your game's logic
        updateBoundingBox();
    }

    private void updateBoundingBox() {
        this.boundingBox = new Rectangle(this.x + 7, this.y + 9, 24, 24);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        updateBoundingBox();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        updateBoundingBox();
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }
}

