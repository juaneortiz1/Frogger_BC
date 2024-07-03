package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.frames;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class CreditsWindow extends JFrame {

    private int width = 660;
    private int height = 550;

    public CreditsWindow(String title) {
        super(title);
        setSize(this.width, this.height);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);

        //Escape should close the window
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");
        am.put("cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JLayeredPane lpane = new JLayeredPane();
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, this.width, this.height);
    }
}