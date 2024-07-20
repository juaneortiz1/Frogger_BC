package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.services;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto.*;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FroggerGameService {

    private FroggerGame froggerGame;

    public FroggerGameService() {
        this.froggerGame = new FroggerGame();
    }

    public FroggerGameDTO getGameState() {
        FroggerGameDTO gameDTO = new FroggerGameDTO();
        gameDTO.setFrog(convertToFrogDTO(froggerGame.getFrog()));
        gameDTO.setCarLanes(convertToCarLaneDTOs(froggerGame.getCarLanes()));
        gameDTO.setLogLanes(convertToLogLaneDTOs(froggerGame.getLogLanes()));
        gameDTO.setLilyPads(convertToLilyPadDTOs(froggerGame.getLilyPadses()));
        gameDTO.setScore(froggerGame.getStatus());
        gameDTO.setLives(froggerGame.getLives());
        gameDTO.setGameOver(froggerGame.isGameOver());
        return gameDTO;
    }

    private FrogDTO convertToFrogDTO(Frog frog) {
        FrogDTO frogDTO = new FrogDTO();
        frogDTO.setX(frog.getX());
        frogDTO.setY(frog.getY());
        frogDTO.setDirection(frog.getDirection());
        return frogDTO;
    }

    private List<CarLaneDTO> convertToCarLaneDTOs(List<CarLane> carLanes) {
        return carLanes.stream()
                .map(carLane -> {
                    CarLaneDTO carLaneDTO = new CarLaneDTO();
                    carLaneDTO.setSpeed(carLane.getSpeed());
                    carLaneDTO.setCars(carLane.getCars().stream().map(this::convertToCarDTO).collect(Collectors.toList()));
                    return carLaneDTO;
                })
                .collect(Collectors.toList());
    }

    private List<LogLaneDTO> convertToLogLaneDTOs(List<LogLane> logLanes) {
        return logLanes.stream()
                .map(logLane -> {
                    LogLaneDTO logLaneDTO = new LogLaneDTO();
                    logLaneDTO.setSpeed(logLane.getSpeed());
                    logLaneDTO.setLogs(logLane.getLogs().stream().map(this::convertToLogDTO).collect(Collectors.toList()));
                    return logLaneDTO;
                })
                .collect(Collectors.toList());
    }

    private List<LilyPadDTO> convertToLilyPadDTOs(List<LilyPad> lilyPadses) {
        return lilyPadses.stream()
                .map(lilyPad -> {
                    LilyPadDTO lilyPadDTO = new LilyPadDTO();
                    lilyPadDTO.setX(lilyPad.getX());
                    lilyPadDTO.setY(lilyPad.getY());
                    return lilyPadDTO;
                })
                .collect(Collectors.toList());
    }

    private CarDTO convertToCarDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setX(car.getX());
        carDTO.setY(car.getY());
        carDTO.setSpeed(car.getSpeed());
        carDTO.setDirection(car.getDirection());
        return carDTO;
    }

    private LogDTO convertToLogDTO(Log log) {
        LogDTO logDTO = new LogDTO();
        logDTO.setX(log.getX());
        logDTO.setY(log.getY());
        logDTO.setSpeed(log.getSpeed());
        logDTO.setDirection(log.getDirection());
        return logDTO;
    }

    public void moveFrog(int direction) {
        if (direction == -1) {
            resetFrogPosition(); // Reiniciar posición si hay colisión
        } else {
            froggerGame.getFrog().move(direction); // Movimiento normal de la rana
        }
    }

    public void resetFrogPosition() {
        froggerGame.getFrog().setX(320); // Ajusta según las coordenadas iniciales de la rana
        froggerGame.getFrog().setY(275);
        froggerGame.getFrog().setDirection(3); // Dirección inicial, ajusta según tu implementación
        froggerGame.playerDeath();
    }

    public void updateCarPositions() {
        List<CarLane> carLanes = froggerGame.getCarLanes();
        for (int i = 0; i < carLanes.size(); i++) {
            CarLane carLane = carLanes.get(i);
            for (Car car : carLane.getCars()) {
                if (i % 2 == 0) {
                    // Línea par, mover coches a la izquierda
                    car.setX(car.getX() - car.getSpeed());
                } else {
                    // Línea impar, mover coches a la derecha
                    car.setX(car.getX() + car.getSpeed());
                }

                // Volver a colocar el coche en el otro extremo si sale de los límites del canvas
                if (car.getX() < 0) {
                    car.setX(800); // Ajusta el tamaño del canvas según sea necesario
                } else if (car.getX() > 800) {
                    car.setX(0);
                }
            }
        }
    }

    public void restartGame() {
        this.froggerGame = new FroggerGame(); // Reinicia el juego creando una nueva instancia
    }
}
