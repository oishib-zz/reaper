import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.*;

public class GameClient implements ActionListener{
  boolean playing = true;
  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;

  private JFrame gameFrame;
  private JPanel gamePanel;

  private JLabel jackpot;
  private JLabel points;
  private JLabel scores;

  private JButton reapButton;
  public GameClient(){
    try {
      socket = new Socket("localhost", 9001);
    } catch (UnknownHostException e) {} catch (IOException e) {}

    try {
      out = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {}

    try {
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException e) {}


    //set up JFrame
    gameFrame = new JFrame("reaper");
    gamePanel = new JPanel();
    gameFrame.add(gamePanel);

    jackpot = new JLabel ("");
    points = new JLabel ("");
    scores = new JLabel ("");
    reapButton = new JButton ("reap");
    reapButton.addActionListener(this);
    gamePanel.add (new JLabel("Current Jackpot:"));
    gamePanel.add (jackpot);
    gamePanel.add (new JLabel("Your Score:"));
    gamePanel.add (points);
    gamePanel.add (reapButton);
    gamePanel.add (scores);

    gameFrame.add(gamePanel);

    gameFrame.setSize(500, 200);
    gameFrame.setVisible(true);
  }

  public static void main(String[] args) throws IOException
  {
    GameClient gameC = new GameClient();
    while (true) {
      gameC.update();
    }
  }

  public void update() {
    try {
      points.setText(in.readLine());
      jackpot.setText(in.readLine());
      if (in.readLine().equals("_START")) {
        String line;
        String assembled = "";
        while (!(line = in.readLine()).equals("_END")) {
          assembled += line + ":";
          assembled += in.readLine();
          assembled += ",";
        }
        scores.setText(assembled);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==reapButton){
      out.println("");
    }
    //handle window closing

  }


}
