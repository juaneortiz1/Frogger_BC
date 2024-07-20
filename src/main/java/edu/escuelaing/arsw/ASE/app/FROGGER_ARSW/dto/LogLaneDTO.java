package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto;

import java.util.List;

public class LogLaneDTO {
    private List<LogDTO> logs;
    private double speed;
    private int direction;
    private int yPosition;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    public List<LogDTO> getDTO() {
        return logs;
    }
    public void setLogs(List<LogDTO> logs) {
        this.logs = logs;
    }
}
