package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.frames.MenuFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.io.IOException;

@SpringBootApplication
public class FroggerArswApplication {

	public static void main(String[] args) {
		SpringApplication.run(FroggerArswApplication.class, args);
		try {
			runGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void runGame() throws IOException {
		SwingUtilities.invokeLater(() -> {
            MenuFrame frame = new MenuFrame("Frogger");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
	}
}

