package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.collision;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects.Frog;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects.Lane;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects.LogLane;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects.LaneItem;

import java.awt.geom.Area;

public class CollisionDetector {

    public static boolean isCollision(Frog frog, Lane[] items) {
        for (Lane item : items) {
            if (item != null) {
                for (LaneItem laneItem : item.getLaneItems()) {
                    Area intersect = new Area(laneItem.getBoundingBox());
                    intersect.intersect(new Area(frog.getBoundingBox()));
                    if (!intersect.isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isOnLog(Frog frog, LogLane[] logLanes) {
        for (LogLane logLane : logLanes) {
            if (logLane != null) {
                for (LaneItem log : logLane.getLaneItems()) {
                    Area intersect = new Area(log.getBoundingBox());
                    intersect.intersect(new Area(frog.getBoundingBox()));
                    if (!intersect.isEmpty()) {
                        // Si la rana está sobre un log, mover la rana con el log
                        if (log.getDirection() == Lane.LEFT) {
                            frog.setX((int) (frog.getX() - logLane.getSpeed()));
                        } else if (log.getDirection() == Lane.RIGHT) {
                            frog.setX((int) (frog.getX() + logLane.getSpeed()));
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
