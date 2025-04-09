import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ConnectDotsGame extends JFrame {
    private ConfigPanel configPanel;
    private DrawingPanel drawingPanel;
    private ControlPanel controlPanel;

    public ConnectDotsGame() {
        setTitle("Connect the Dots Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        configPanel = new ConfigPanel(this);
        drawingPanel = new DrawingPanel();
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void createNewGame(int dotCount) {
        drawingPanel.generateDots(dotCount);
    }

    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game.ser"))) {
            out.writeObject(drawingPanel.getGameState());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("game.ser"))) {
            GameState state = (GameState) in.readObject();
            drawingPanel.setGameState(state);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();n        }
    }

    public void exportPNG() {
        drawingPanel.exportAsImage();
    }

    public static void main(String[] args) {
        new ConnectDotsGame();
    }
}