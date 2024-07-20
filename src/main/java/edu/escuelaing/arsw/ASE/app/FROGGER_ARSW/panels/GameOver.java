/*package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.panels;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.model.FroggerPanel;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.UI.PlaceholderTextField;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import javax.swing.*;

public class GameOver extends JPanel {
    public static PlaceholderTextField usernameTextArea;
    int index;

    public GameOver(int index) {
        this.setOpaque(false);
        this.setVisible(true);
        this.setLayout((LayoutManager) null);
        this.index = index;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Composite old = g2d.getComposite();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5F));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(old);
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", 1, 80));
        g.drawString("GAME OVER", FroggerPanel.WIDTH / 2 - 220, FroggerPanel.HEIGHT / 2 - 40);
    }

}*/