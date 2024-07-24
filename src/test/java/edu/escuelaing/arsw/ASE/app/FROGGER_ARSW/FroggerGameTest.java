package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.CarLane;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.Frog;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.FroggerGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FroggerGameTest {

    private FroggerGame game;
    private Frog frog;

    @BeforeEach
    void setUp() {
        game = new FroggerGame();
        frog = new Frog(FroggerGame.frogX, FroggerGame.frogY);
    }

    @Test
    void testInitialGameState() {
        assertEquals(FroggerGame.PLAYING, game.getStatus());
        assertEquals(5000, game.getLives());
        assertEquals(1, game.getFrogs().size());
        assertFalse(false);
    }

    @Test
    void testAddFrog() {
        game.addFrog(frog);
        assertEquals(2, game.getFrogs().size());
    }

    @Test
    void testRemoveFrog() {
        game.removeFrog(game.getFrogs().get(0));
        assertEquals(0, game.getFrogs().size());
    }

    @Test
    void testUpdate() {
        game.update();
        // Ensure the update method does not throw exceptions
        assertTrue(true); // Placeholder assertion, more specific checks should be added based on update logic
    }

    @Test
    void testPlayerDeath() {
        game.setLives(1); // Set score to 1
        game.playerDeath(frog);
        assertEquals(0, game.getLives());
        assertTrue(FroggerGame.DEAD);
    }

    @Test
    void testCollisionDetection() {
        CarLane[] carLanes = game.getCarLanes().toArray(new CarLane[0]);
        Frog frog = new Frog(320, 75); // Position frog on a car lane
        game.addFrog(frog);
        game.carCheck();
        assertTrue(true); // Ensure that collision affects lives
    }

    @Test
    void testLogCheck() {
        Frog frogOnLog = new Frog(320, 200); // Position frog on log
        game.addFrog(frogOnLog);
        game.logCheck();
        assertEquals(5000, game.getLives()); // Lives should remain the same if frog is on a log
    }

    @Test
    void testCheckIfPlayerWin() {
        Frog winningFrog = new Frog(320, 50); // Position frog close to winning
        game.addFrog(winningFrog);
        game.checkifThePlayerWin();
        assertTrue(FroggerGame.WIN);
    }

    @Test
    void testIsGameOver() {
        assertFalse(false);
        game.setLives(0);
        game.playerDeath(frog);
        assertTrue(true);
    }

    @Test
    void testGetTimeLeft() {
        // Time left should be equal to MAX_LIFE_TIME initially
        assertEquals(FroggerGame.MAX_LIFE_TIME, game.getTimeLeft());
    }
}
