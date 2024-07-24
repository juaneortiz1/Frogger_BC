package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.services;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto.*;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Service class for managing the Frogger game logic.
 * Handles game creation, frog management, game state retrieval, and updates.
 */
@Service
public class FroggerGameService {

    private Map<Integer, FroggerGame> games;
    private AtomicInteger sessionIdGenerator = new AtomicInteger(-1);
    private Map<Integer, AtomicInteger> frogIdGenerators;

    /**
     * Constructor for the FroggerGameService.
     */
    public FroggerGameService() {
        this.games = new ConcurrentHashMap<>();
        this.frogIdGenerators = new ConcurrentHashMap<>();
    }

    /**
     * Creates a new game session.
     *
     * @return The session ID of the newly created game.
     */
    public int createSession() {
        int sessionId = sessionIdGenerator.incrementAndGet();
        FroggerGame newGame = new FroggerGame();
        games.put(sessionId, newGame);
        frogIdGenerators.put(sessionId, new AtomicInteger(1)); // Initialize frogId generator
        System.out.println("New game created with session ID: " + sessionId);

        return sessionId;
    }

    /**
     * Adds a new frog to the game session.
     *
     * @param sessionId The session ID of the game.
     * @return The ID of the newly added frog.
     */
    public int addFrog(int sessionId) {
        FroggerGame froggerGame = games.get(sessionId);
        if (froggerGame == null) {
            throw new IllegalArgumentException("Game not found with sessionId: " + sessionId);
        }

        // Generate a new frogId
        int frogId = frogIdGenerators.get(sessionId).incrementAndGet();
        Frog newFrog = new Frog(FroggerGame.frogX, FroggerGame.frogY);
        newFrog.setSessionId(frogId);
        froggerGame.addFrog(newFrog);

        return frogId;
    }

    /**
     * Retrieves the current state of the game.
     *
     * @param sessionId The session ID of the game.
     * @return The DTO representation of the game state.
     */
    public FroggerGameDTO getGameState(int sessionId) {
        FroggerGame froggerGame = games.get(sessionId);
        if (froggerGame == null) {
            throw new IllegalArgumentException("Game not found with sessionId: " + sessionId);
        }

        FroggerGameDTO gameDTO = new FroggerGameDTO();
        gameDTO.setFrogs(convertToFrogDTOs(froggerGame.getFrogs()));
        gameDTO.setCarLanes(convertToCarLaneDTOs(froggerGame.getCarLanes()));
        gameDTO.setLogLanes(convertToLogLaneDTOs(froggerGame.getLogLanes()));
        gameDTO.setLilyPads(convertToLilyPadDTOs(froggerGame.getLilyPadses()));
        gameDTO.setLives(froggerGame.getLives());
        gameDTO.setGameOver(froggerGame.isGameOver());
        return gameDTO;
    }

    private List<FrogDTO> convertToFrogDTOs(List<Frog> frogs) {
        return frogs.stream()
                .map(frog -> {
                    FrogDTO frogDTO = new FrogDTO();
                    frogDTO.setId(frog.getSessionId()); // Includes the ID
                    frogDTO.setX(frog.getX());
                    frogDTO.setY(frog.getY());
                    frogDTO.setDirection(frog.getDirection());
                    return frogDTO;
                })
                .collect(Collectors.toList());
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

    /**
     * Moves a frog in the specified direction.
     *
     * @param sessionId The session ID of the game.
     * @param frogId    The ID of the frog to move.
     * @param direction The direction to move the frog (-1 for reset, -2 for increment lives, otherwise move).
     */
    public void moveFrog(int sessionId, int frogId, int direction) {
        FroggerGame froggerGame = games.get(sessionId);
        if (froggerGame == null) {
            throw new IllegalArgumentException("Game not found with sessionId: " + sessionId);
        }

        Frog frog = froggerGame.getFrogs().stream()
                .filter(f -> f.getSessionId() == frogId)
                .findFirst()
                .orElse(null);

        if (frog == null) {
            throw new IllegalArgumentException("Frog not found with id: " + frogId);
        }

        if (direction == -1) {
            resetFrogPosition(sessionId, frogId);
        } else if (direction == -2) {
            incrementLives(sessionId, frogId);
        } else {
            frog.move(direction);
        }
    }

    /**
     * Resets the position of the frog.
     *
     * @param sessionId The session ID of the game.
     * @param frogId    The ID of the frog to reset.
     */
    public void resetFrogPosition(int sessionId, int frogId) {
        FroggerGame froggerGame = games.get(sessionId);
        if (froggerGame == null) {
            throw new IllegalArgumentException("Game not found with sessionId: " + sessionId);
        }

        Frog frog = froggerGame.getFrogs().stream()
                .filter(f -> f.getSessionId() == frogId)
                .findFirst()
                .orElse(null);

        if (frog == null) {
            throw new IllegalArgumentException("Frog not found with id: " + frogId);
        }

        frog.setX(FroggerGame.frogX); // Reset to initial coordinates
        frog.setY(FroggerGame.frogY);
        frog.setDirection(Frog.UP); // Initial direction, adjust as needed

        froggerGame.playerDeath(frog); // Handle player death logic
    }

    /**
     * Increments the lives of the player.
     *
     * @param sessionId The session ID of the game.
     * @param frogId    The ID of the frog to increment lives for.
     */
    public void incrementLives(int sessionId, int frogId) {
        FroggerGame froggerGame = games.get(sessionId);
        if (froggerGame == null) {
            throw new IllegalArgumentException("Game not found with sessionId: " + sessionId);
        }

        Frog frog = froggerGame.getFrogs().stream()
                .filter(f -> f.getSessionId() == frogId)
                .findFirst()
                .orElse(null);

        if (frog == null) {
            throw new IllegalArgumentException("Frog not found with id: " + frogId);
        }

        froggerGame.setLives(froggerGame.getLives() + 100);
        frog.setX(FroggerGame.frogX);
        frog.setY(FroggerGame.frogY);
        frog.setDirection(Frog.UP);
    }

    /**
     * Retrieves the current session ID as a string.
     *
     * @return The current session ID.
     */
    public String getSessionId() {
        return String.valueOf(sessionIdGenerator.get());
    }

    /**
     * Updates the positions of cars in the game.
     *
     * @param sessionId The session ID of the game.
     */
    public void updateCarPositions(int sessionId) {
        FroggerGame froggerGame = games.get(sessionId);
        if (froggerGame == null) {
            throw new IllegalArgumentException("Game not found with sessionId: " + sessionId);
        }

        List<CarLane> carLanes = froggerGame.getCarLanes();
        for (int i = 0; i < carLanes.size(); i++) {
            CarLane carLane = carLanes.get(i);
            for (Car car : carLane.getCars()) {
                if (i % 2 == 0) {
                    // Even lane, move cars to the left
                    car.setX(car.getX() - car.getSpeed());
                } else {
                    // Odd lane, move cars to the right
                    car.setX(car.getX() + car.getSpeed());
                }

                // Wrap around if the car moves out of bounds
                if (car.getX() < 0) {
                    car.setX(800); // Adjust canvas size as necessary
                } else if (car.getX() > 800) {
                    car.setX(0);
                }
            }
        }
    }

    /**
     * Restarts the game session by creating a new game instance.
     *
     * @param sessionId The session ID of the game to restart.
     */
    public void restartGame(int sessionId) {
        FroggerGame newGame = new FroggerGame();
        games.put(sessionId, newGame); // Restart the game by creating a new instance for the session
    }

    /**
     * Deletes all frogs from the game session.
     *
     * @param sessionId The session ID of the game.
     */
    public void deleteAllFrogs(int sessionId) {
        FroggerGame froggerGame = games.get(sessionId);
        if (froggerGame == null) {
            throw new IllegalArgumentException("Game not found with sessionId: " + sessionId);
        }
        froggerGame.getFrogs().clear();
    }
}
