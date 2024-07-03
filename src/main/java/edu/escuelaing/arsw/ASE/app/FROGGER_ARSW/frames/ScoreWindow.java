package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.frames;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.UI.CustomizedButtonUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ScoreWindow extends JFrame {
    public int width = 660;
    public int height = 600;

    public ScoreWindow(String title) {
        super(title);
        setSize(width, height);
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
        lpane.setBounds(0, 0, width, height);
        ScorePanel cp = new ScorePanel();
        cp.setBounds(0, 0, width, height);
        add(cp);
        lpane.add(cp, new Integer(0), 0);
    }

    public static class ScorePanel extends JPanel implements ActionListener {

        Image froggerLogo;
        Image frogavatar;
        Image[] allImages;
        private JButton back;

        public ScorePanel() {
            setOpaque(false);
            setLayout(null);

            try {
                froggerLogo = ImageIO.read(new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/frog_logo.jpg")).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                frogavatar = ImageIO.read(new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/frog_avatar.jpg")).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                allImages = new Image[5];
                for (int i = 0; i < 5; i++) {
                    Image img = frogavatar;
                    allImages[i] = img;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            ArrayList<String> allScores = new ArrayList<>();
            ArrayList<String> allNames = new ArrayList<>();

            try {
                FileReader fr = new FileReader("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/files/scores.txt");
                BufferedReader br = new BufferedReader(fr);
                FileReader frNames = new FileReader("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/files/names.txt");
                BufferedReader brNames = new BufferedReader(frNames);
                for (int i = 0; i < 5; i++) {
                    allScores.add(br.readLine());
                }

                for (int i = 0; i < 5; i++) {
                    allNames.add(brNames.readLine());
                }

                frNames.close();
                brNames.close();
                br.close();
                fr.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            ArrayList<JLabel> lables = new ArrayList<>();
            lables.add(new JLabel(allNames.get(0), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(1), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(2), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(3), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(4), JLabel.LEFT));

            lables.add(new JLabel(allScores.get(0), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(1), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(2), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(3), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(4), JLabel.LEFT));


            for (int i = 0; i < lables.size(); i++) {
                JLabel jl = lables.get(i);
                int X = 140;
                int Y = (198 + i * 70);
                jl.setFont(new Font("Snap ITC", Font.BOLD, 25));
                jl.setForeground(new Color(251, 102, 8));
                if (i > 4) {
                    X = 400;
                    Y = (198 + (i - 5) * 70);
                    jl.setFont(new Font("Snap ITC", Font.PLAIN, 24));
                    jl.setForeground(new Color(70, 70, 70));
                }


                jl.setBounds(0, 0, 400, 22);
                jl.setLocation(X, Y);

                add(jl);
            }


            back = new JButton("Back");
            back.addActionListener(this);
            back.setBorder(BorderFactory.createEmptyBorder());
            back.setUI(new CustomizedButtonUI(new Color(251, 102, 8),
                    Color.black, new Color(45, 45, 45),
                    new Font("Snap ITC", Font.PLAIN, 25), new Color(183, 233, 98)));
            back.setBounds(0, 0, 120, 40);
            back.setLocation(640 / 2 - 120 / 2, 550);
            add(back);

        }

        public void actionPerformed(ActionEvent action) {
            if (action.getSource() == back) {
                SwingUtilities.getWindowAncestor(this).dispose();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(froggerLogo, (getWidth() - froggerLogo.getWidth(this)) / 2 - 135, 25, this);

            for (int i = 0; i < 5; i++) {
                g2.drawImage(allImages[i], 60, (180 + i * 70), 50, 50, this);
            }

        }

    }
}
