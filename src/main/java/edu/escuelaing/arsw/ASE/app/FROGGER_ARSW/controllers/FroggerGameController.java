package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.controllers;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto.FroggerGameDTO;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.services.FroggerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for handling operations related to the Frogger game.
 * Provides endpoints for getting the game state, moving frogs, restarting the game,
 * creating sessions, adding frogs, getting the session ID, and deleting all frogs.
 */
@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:3000")
public class FroggerGameController {

    private final FroggerGameService froggerGameService;

    /**
     * Constructor for the controller.
     *
     * @param froggerGameService The service that handles the logic of the Frogger game.
     */
    @Autowired
    public FroggerGameController(FroggerGameService froggerGameService) {
        this.froggerGameService = froggerGameService;
    }

    /**
     * Retrieves the game state for a specific session.
     *
     * @param sessionId The ID of the game session.
     * @return The current state of the game.
     */
    @GetMapping("/state/{sessionId}")
    public FroggerGameDTO getGameState(@PathVariable int sessionId) {
        froggerGameService.updateCarPositions(sessionId);
        return froggerGameService.getGameState(sessionId);
    }

    /**
     * Moves a frog in the game.
     *
     * @param sessionId The ID of the game session.
     * @param frogId The ID of the frog to be moved.
     * @param direction The direction in which the frog will move.
     * @return HTTP response indicating whether the operation was successful.
     */
    @PostMapping("/move/{sessionId}/{frogId}/{direction}")
    public ResponseEntity<Void> moveFrog(@PathVariable int sessionId, @PathVariable int frogId, @PathVariable int direction) {
        try {
            froggerGameService.moveFrog(sessionId, frogId, direction);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Restarts the game for a specific session.
     *
     * @param sessionId The ID of the game session.
     * @return HTTP response indicating whether the operation was successful.
     */
    @PostMapping("/restart/{sessionId}")
    public ResponseEntity<Void> restartGame(@PathVariable int sessionId) {
        try {
            froggerGameService.restartGame(sessionId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Creates a new game session.
     *
     * @return The ID of the new session.
     */
    @PostMapping("/createSession")
    public ResponseEntity<Integer> createSession() {
        int sessionId = froggerGameService.createSession();
        return ResponseEntity.ok(sessionId);
    }

    /**
     * Adds a new frog to a specific session.
     *
     * @param sessionId The ID of the game session.
     * @return The ID of the new frog.
     */
    @PostMapping("/addFrog/{sessionId}")
    public ResponseEntity<Integer> addFrog(@PathVariable int sessionId) {
        int frogId = froggerGameService.addFrog(sessionId);
        return ResponseEntity.ok(frogId);
    }

    /**
     * Retrieves the current session ID.
     *
     * @return A map containing the session ID.
     */
    @GetMapping("/session")
    public ResponseEntity<Map<String, String>> getSessionId() {
        Map<String, String> response = new HashMap<>();
        response.put("sessionId", froggerGameService.getSessionId());
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes all frogs from a specific session.
     *
     * @param sessionId The ID of the game session.
     * @return HTTP response indicating whether the operation was successful.
     */
    @DeleteMapping("/deleteFrogs/{sessionId}")
    public ResponseEntity<Void> deleteFrogs(@PathVariable int sessionId) {
        try {
            froggerGameService.deleteAllFrogs(sessionId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
