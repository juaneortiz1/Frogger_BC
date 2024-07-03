package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.Objects;

import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.collision.CollisionDetector;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.panels.GameOver;
import edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.panels.YouWin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class FroggerPanel extends JPanel implements KeyListener, Runnable {

    public static int HEIGHT = 450;
    public static int WIDTH = 700;
    private CollisionDetector checkForCollision;

    BufferedImage car1_Left, car1_Right, car2_Left, car2_Right, limo_Left, limo_Right, semi_Left, semi_Right, frogUp, frogDown,
            frogLeft, frogRight, hsTurtle, hmTurtle, hlTurtle, sTurtle, mTurtle, lTurtle, sLog, mLog, lLog, lilyPad, frogLife;
    FroggerGame game;

    //BufferedImage buffer;
    int updatesPerSecond;
    int framesPerSecond; //todo used?


    public FroggerPanel() {
        setSize(WIDTH, HEIGHT);
        this.checkForCollision = new CollisionDetector();

        reset();
        Thread pThread;

        try {
            pThread = new Thread(this);
            pThread.start();
        } catch (Exception e) {
            System.err.println("Error creating thread.");
            e.printStackTrace();
            System.exit(-2);
        }

        try {
            car1_Left = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Car1-Left.png")));
            car1_Right = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Car1-Right.png")));
            car2_Left = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Car2-Left.png")));
            car2_Right = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Car2-Right.png")));
            limo_Left = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Limo-Left.png")));
            limo_Right = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Limo-Right.png")));
            semi_Left = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Semi-Left.png")));
            semi_Right = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/Semi-Right.png")));
            frogUp = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/FrogUp.png")));
            frogDown = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/FrogDown.png")));
            frogLeft = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/FrogLeft.png")));
            frogRight = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/FrogRight.png")));
            hsTurtle = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/HS-Turtle.png")));
            hmTurtle = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/HM-Turtle.png")));
            hlTurtle = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/HL-Turtle.png")));
            sTurtle = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/S-Turtle.png")));
            mTurtle = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/M-Turtle.png")));
            lTurtle = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/L-Turtle.png")));
            sLog = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/S-Log.png")));
            mLog = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/M-Log.png")));
            lLog = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/L-Log.png")));
            lilyPad = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/lilyPad.png")));
            frogLife = ImageIO.read((new File("src/main/java/edu/escuelaing/arsw/ASE/app/FROGGER_ARSW/textures/FrogLife.png")));


        } catch (Exception e) {
            System.err.println("Error Loading Images: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1); //if loading fails, end the program.
        }
        addKeyListener(this);

    }

    @Deprecated
    public void keyReleased(KeyEvent e) {
        //unused
    }

    @Deprecated
    public void keyPressed(KeyEvent e) {
        //unused

    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            update();
            repaint();
            try {
                if (game.DEAD) {
                    GameOver gameOver;
                    gameOver = new GameOver(0); //
                    gameOver.setBounds(0, 0, WIDTH, HEIGHT);
                    this.getParent().getParent().add(gameOver, new Integer(2), 0);
                    Thread.sleep(1000000000000000000L);
                }
                if (game.WIN) {
                    YouWin youWin;
                    youWin = new YouWin(true, 0); //
                    youWin.setBounds(0, 0, WIDTH, HEIGHT);
                    this.getParent().getParent().add(youWin, new Integer(2), 0);
                    Thread.sleep(1000000000000000000L);
                }
                Thread.sleep(35); //todo correct times per second?

            } catch (Exception e) {
                System.err.println("Error Sleeping.");
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                if ((game.getPlayer().getY() - 40) > 30)
                    game.getPlayer().setY(game.getPlayer().getY() - 40);
                game.getPlayer().setDirection(Frog.UP);
                break;
            case 's':
                if ((game.getPlayer().getY() + 40) < getHeight() - 100)
                    game.getPlayer().setY(game.getPlayer().getY() + 40);
                game.getPlayer().setDirection(Frog.DOWN);
                break;
            case 'a':
                if ((game.getPlayer().getX() - 30) > 0)
                    game.getPlayer().setX(game.getPlayer().getX() - 40);
                game.getPlayer().setDirection(Frog.LEFT);
                break;
            case 'd':
                if ((game.getPlayer().getX() + 40) < getWidth() - 30)
                    game.getPlayer().setX(game.getPlayer().getX() + 40);
                game.getPlayer().setDirection(Frog.RIGHT);
                break;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, getWidth(), getHeight()); // fill the background

        // Fill water on upper part of map
        g.setColor(Color.BLUE);
        g.fillRect(0, 65, getWidth(), 190);

        // Small water inlets for lilypads
        g.setColor(Color.BLUE);
        g.fillRect(60, 20, 70, 50);
        g.fillRect(240, 20, 70, 50);
        g.fillRect(420, 20, 70, 50);
        g.fillRect(600, 20, 70, 50);

        // White lines of the road
        g.setColor(Color.white);
        g.drawLine(0, 75, getWidth(), 75);
        g.drawLine(0, 275, getWidth(), 275);

        // Road
        g.setColor(Color.GRAY);
        g.fillRect(0, 76, getWidth(), 199);



        // Bottom black bar
        g.setColor(Color.BLACK);
        g.fillRect(0, getHeight() - 100, getWidth(), 300);

        // Yellow lines on road
        g.setColor(Color.yellow);
        for (int y = 116; y < 264; y += 39) {
            for (int x = 10; x < getWidth() - 10; x += 90) {
                g.fillRect(x, y, 60, 4);
            }
        }

        // Lilypads
        g.setColor(Color.BLUE);
        for (int i = 75; i <= 615; i += 179) {
            g.drawImage(lilyPad, i, 30, null);
        }

        // Text
        g.setColor(Color.darkGray);
        g.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        g.drawString("Lives:", 10, getHeight() - 15);
        g.drawString("Time Left:", 300, getHeight() - 15);

        // Lives
        g.setColor(Color.RED);
        for (int i = 0; i < game.getLives(); i++) {
            g.drawString("♥", 130 + i * 30, getHeight() - 15);
        }

        // Time left
        int timeLeft = game.getTimeLeft();
        if (timeLeft >= 60)
            g.setColor(Color.green);
        else if (timeLeft >= 40)
            g.setColor(Color.orange);
        else
            g.setColor(Color.RED);

        g.fillRect(500, getHeight() - 40, (timeLeft * 2) + 10, 20); // draw timer based on time left
        g.drawRect(500, getHeight() - 40, 170, 20); // timer outline

        // Draw frog
        switch (game.getPlayer().getDirection()) {
            case Frog.UP:
                g.drawImage(frogUp, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.DOWN:
                g.drawImage(frogDown, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.LEFT:
                g.drawImage(frogLeft, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.RIGHT:
                g.drawImage(frogRight, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
        }

        // Moving objects
        // Cars
        for (CarLane cl : game.getCarLanes()) {
            if (cl != null) {
                for (LaneItem car : cl.getLaneItems()) {
                    if (car != null) {
                        switch (car.getType()) {
                            case Car.CAR_1:
                                if (car.getDirection() == Lane.RIGHT)
                                    g.drawImage(car1_Right, (int) car.getX(), (int) car.getY(), null);
                                else if (car.getDirection() == Lane.LEFT)
                                    g.drawImage(car1_Left, (int) car.getX(), (int) car.getY(), null);
                                break;
                            case Car.CAR_2:
                                if (car.getDirection() == Lane.RIGHT)
                                    g.drawImage(car2_Right, (int) car.getX(), (int) car.getY(), null);
                                else if (car.getDirection() == Lane.LEFT)
                                    g.drawImage(car1_Left, (int) car.getX(), (int) car.getY(), null);
                                break;
                            case Car.LIMO:
                                if (car.getDirection() == Lane.LEFT)
                                    g.drawImage(limo_Left, (int) car.getX(), (int) car.getY(), null);
                                else if (car.getDirection() == Lane.RIGHT)
                                    g.drawImage(limo_Right, (int) car.getX(), (int) car.getY(), null);
                                break;
                            case Car.SEMI:
                                if (car.getDirection() == Lane.LEFT)
                                    g.drawImage(semi_Left, (int) car.getX(), (int) car.getY(), null);
                                else if (car.getDirection() == Lane.RIGHT)
                                    g.drawImage(semi_Right, (int) car.getX(), (int) car.getY(), null);
                                break;
                        }
                    }
                }
            }
        }


        // Logs
        g.setColor(Color.BLUE);
        for (LogLane ll : game.getLogLanes()) {
            g.fillRect(-5, ll.getY(), getWidth(), 35);
        }

        // Dibujar logs
        for (LogLane ll : game.getLogLanes()) {
            for (LaneItem log : ll.getLaneItems()) {
                switch (log.getType()) {
                    case Log.SHORT:
                        if (log.getDirection() == Lane.RIGHT)
                            g.drawImage(sLog, (int) log.getX(), (int) log.getY(), null);
                        else if (log.getDirection() == Lane.LEFT)
                            g.drawImage(sLog, (int) log.getX(), (int) log.getY(), null);
                        break;
                    case Log.MEDIUM:
                        if (log.getDirection() == Lane.RIGHT)
                            g.drawImage(mLog, (int) log.getX(), (int) log.getY(), null);
                        else if (log.getDirection() == Lane.LEFT)
                            g.drawImage(mLog, (int) log.getX(), (int) log.getY(), null);
                        break;
                    case Log.LONG:
                        if (log.getDirection() == Lane.RIGHT)
                            g.drawImage(lLog, (int) log.getX(), (int) log.getY(), null);
                        else if (log.getDirection() == Lane.LEFT)
                            g.drawImage(lLog, (int) log.getX(), (int) log.getY(), null);
                        break;
                }
            }
        }

        // Dibujar la rana después de los logs
        switch (game.getPlayer().getDirection()) {
            case Frog.UP:
                g.drawImage(frogUp, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.DOWN:
                g.drawImage(frogDown, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.LEFT:
                g.drawImage(frogLeft, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Frog.RIGHT:
                g.drawImage(frogRight, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
        }
    }




    void update() {
        game.update();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    void reset() {
        this.game = new FroggerGame();
    }


}
