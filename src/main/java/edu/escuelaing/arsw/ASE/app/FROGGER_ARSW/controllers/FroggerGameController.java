package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.controllers;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.dto.FroggerGameDTO;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.services.FroggerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class FroggerGameController {

    private final FroggerGameService froggerGameService;

    @Autowired
    public FroggerGameController(FroggerGameService froggerGameService) {
        this.froggerGameService = froggerGameService;
    }

    @GetMapping("/state")
    public FroggerGameDTO getGameState() {
        froggerGameService.updateCarPositions(); // Actualizar las posiciones de los coches antes de devolver el estado del juego
        return froggerGameService.getGameState();
    }

    @PostMapping("/move/{direction}")
    public void moveFrog(@PathVariable int direction) {
        froggerGameService.moveFrog(direction);
    }

    @PostMapping("/restart")
    public void restartGame() {
        froggerGameService.restartGame();
    }
}

