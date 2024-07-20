package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto;

import java.util.List;

public class CarLaneDTO {
    private List<CarDTO> cars;
    private double speed;
    private int direction;
    private int yPosition;

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }


}
