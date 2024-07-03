package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.frames.MenuFrame;


import javax.swing.*;
import java.io.IOException;

public class GameMain {

    public static void main(String[] args) throws IOException {
        MenuFrame frame = new MenuFrame("Frogger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
