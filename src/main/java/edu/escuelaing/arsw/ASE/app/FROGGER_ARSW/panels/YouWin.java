package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.panels;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects.FroggerPanel;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.UI.PlaceholderTextField;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.UI.CustomizedButtonUI;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.frames.ScoreWindow;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class YouWin extends JPanel implements ActionListener {
    public static PlaceholderTextField usernameTextArea;
    JButton ok;
    List<String> allNames = new ArrayList<>();
    int index;

    public YouWin(boolean showUserInput, int index) {
        this.setOpaque(false);
        this.setVisible(true);
        this.setLayout((LayoutManager) null);
        this.index = index;

        if (showUserInput) {
            usernameTextArea = new PlaceholderTextField();
            usernameTextArea.setBounds(0, 0, 260, 35);
            usernameTextArea.setFont(new Font("Consolas", 1, 30));
            usernameTextArea.setLocation(FroggerPanel.WIDTH / 2 - usernameTextArea.getWidth() / 2, FroggerPanel.HEIGHT / 2 - usernameTextArea.getHeight() / 2 + 40);
            usernameTextArea.setBackground(new Color(158, 255, 117
            ));
            usernameTextArea.setForeground(Color.white);
            usernameTextArea.setBorder(BorderFactory.createEmptyBorder());
            usernameTextArea.setPlaceholder("Enter your name");
            usernameTextArea.setHorizontalAlignment(0);
            usernameTextArea.setAlignmentY(0.0F);
            this.add(usernameTextArea);


            this.ok = new JButton("OK");
            this.ok.addActionListener(this);
            this.ok.setBorder(BorderFactory.createEmptyBorder());
            this.ok.setUI(new CustomizedButtonUI(new Color(255, 255, 255), new Color(100, 100, 100), new Color(150, 150, 150), new Font("Snap ITC", Font.PLAIN, 25), Color.BLACK));
            this.ok.setBounds(0, 0, 100, 40);
            this.ok.setLocation(FroggerPanel.WIDTH / 2 - this.ok.getWidth() / 2, FroggerPanel.HEIGHT / 2 - this.ok.getHeight() / 2 + 150);
            this.add(this.ok);
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Composite old = g2d.getComposite();
        g2d.setColor(Color.white);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5F));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(old);
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", 1, 80));
        g.drawString("YOU WIN", FroggerPanel.WIDTH / 2 - 180, FroggerPanel.HEIGHT / 2 - 40);
    }

    public void actionPerformed(ActionEvent action) {
        String name = usernameTextArea.getText();
        if ("".equals(name)) {
            name = "No name";
        }

        try {
            FileReader fReader = new FileReader("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/files/names.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            for (int i = 0; i < 5; i++) {
                this.allNames.add(bReader.readLine());
            }
            this.allNames.add(index, name);
            bReader.close();
            fReader.close();

            FileWriter fileWriter = new FileWriter("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/files/names.txt");
            PrintWriter write = new PrintWriter(fileWriter);
            for (int i = 0; i < 5; i++) {
                write.printf("%s%n", this.allNames.get(i));
            }
            fileWriter.close();
            write.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        SwingUtilities.getWindowAncestor(this).dispose();
        ScoreWindow sWindow = new ScoreWindow("Scores");
        sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sWindow.setVisible(true);
    }
}