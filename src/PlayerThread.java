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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameClient implements ActionListener{
    boolean playing = true;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JFrame gameFrame;
    private JPanel gamePanel;
    private JTextField jackpot;
    private JTextField points;
    private JButton reapButton;
    public GameClient(){
   	 //will be switched to false when the user hits exit
   	 
   			 //creates socket that connects to the server at 29999
   			 try {
   				 socket = new Socket("localhost", 9001);
   			 } catch (UnknownHostException e) {} catch (IOException e) {}
   			 //out allows the client to write messages that the server will receive
   			 try {
   				 out = new PrintWriter(socket.getOutputStream(), true);
   			 } catch (IOException e) {}
   			 //in allows the client to read messages from the server
   			 try {
   				 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   			 } catch (IOException e) {}

   			 //set up JFrame
   			 gameFrame = new JFrame("reaper");
   			 gamePanel = new JPanel();
   			 gameFrame.add(gamePanel);
   			 
   			 jackpot = new JTextField ("current jackpot: not available                                        	");
   			 points = new JTextField ("your points: not available                                        	");
   			 reapButton = new JButton ("reap");
   			 reapButton.addActionListener(this);
   			 gamePanel.add (jackpot);
   			 gamePanel.add (reapButton);
   			 gamePanel.add (points);
   			 
   			 gameFrame.add(gamePanel);
   			 
   			 gameFrame.setSize(500, 200);
   			 gameFrame.setVisible(true);
   			 this.update();
    }
    public static void main(String[] args) throws IOException
    {
   	 GameClient gameC = new GameClient();
   	 while (true){
   		 gameC.update();
   		 try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 }
   	 /*out.close();
   	 in.close();
   	 socket.close();*/
	}
    //TODO: write update() that gets the player's current points and the current jackpot from the server
    //and updates the frame
    public void update(){
   	 out.println("update");

   	 //next line printed by server is current jackpot
   	 try {

   		 jackpot.setText(in.readLine());
   	 } catch (IOException e) {
   		 e.printStackTrace();
   	 }
   	 try {
   		 points.setText(in.readLine());
   	 } catch (IOException e) {
   		 e.printStackTrace();
   	 }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
   	 if (e.getSource()==reapButton){
   		 System.out.println ("clicked the reap button");
   		 out.println("reap");
   	 }
   	 //handle window closing
   	 
    }

    
}
