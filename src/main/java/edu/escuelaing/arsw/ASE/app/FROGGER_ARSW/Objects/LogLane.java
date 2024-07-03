package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects;

import java.util.ArrayList;
import java.util.List;

public class LogLane extends Lane {

    private static final int MIN_DISTANCE = 400; // Distancia mínima entre logs

    public LogLane(double speed, int direction, int y) {
        super(speed, direction, y);
        initializeLogs(); // Inicializar los logs en la creación del LogLane
    }

    private void initializeLogs() {
        // Lógica para inicializar logs en el LogLane
        for (int i = 0; i < 5; i++) { // Agregar 5 logs inicialmente
            int type = (int) (Math.random() * 3); // Tipo aleatorio de log
            int length;
            switch (type) {
                case Log.SHORT:
                    length = 80;
                    break;
                case Log.MEDIUM:
                    length = 120;
                    break;
                case Log.LONG:
                    length = 160;
                    break;
                default:
                    length = 120; // Default a medium length
                    break;
            }
            int startX = (direction == Lane.LEFT)
                    ? 700 + i * (length + MIN_DISTANCE)
                    : -120 - i * (length + MIN_DISTANCE);
            laneItems.add(new Log(speed, type, direction, startX, y));
        }
    }

    @Override
    void update() {
        super.update();

        // Lógica para actualizar la posición de los logs
        for (int i = 0; i < laneItems.size(); i++) {
            LaneItem log = laneItems.get(i);
            if (direction == LEFT) {
                log.setX(log.getX() - speed);
                if (log.getX() + log.getWidth() < 0) {
                    // Si el log sale de la pantalla, eliminarlo y crear uno nuevo
                    laneItems.remove(i);
                    int type = (int) (Math.random() * 3); // Tipo aleatorio de log
                    int length;
                    switch (type) {
                        case Log.SHORT:
                            length = 80;
                            break;
                        case Log.MEDIUM:
                            length = 120;
                            break;
                        case Log.LONG:
                            length = 160;
                            break;
                        default:
                            length = 120; // Default a medium length
                            break;
                    }
                    int newStartX = 700 + (int) (Math.random() * MIN_DISTANCE) + length;
                    laneItems.add(new Log(speed, type, direction, newStartX, y));
                }
            } else if (direction == RIGHT) {
                log.setX(log.getX() + speed);
                if (log.getX() > 700) {
                    // Si el log sale de la pantalla, eliminarlo y crear uno nuevo
                    laneItems.remove(i);
                    int type = (int) (Math.random() * 3); // Tipo aleatorio de log
                    int length;
                    switch (type) {
                        case Log.SHORT:
                            length = 80;
                            break;
                        case Log.MEDIUM:
                            length = 120;
                            break;
                        case Log.LONG:
                            length = 160;
                            break;
                        default:
                            length = 120; // Default a medium length
                            break;
                    }
                    int newStartX = -120 - (int) (Math.random() * MIN_DISTANCE) - length;
                    laneItems.add(new Log(speed, type, direction, newStartX, y));
                }
            }
        }
    }
}

