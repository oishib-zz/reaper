import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class PlayerThread extends Thread{
  private Socket client;
  private PrintWriter out;
  private BufferedReader in;
  private static GameThread game;
  private int id;
  
  public PlayerThread (Socket client, int id){
    this.client=client;
    try {
      this.out = new PrintWriter(client.getOutputStream(), true);
      this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      this.id = id;
    }
    catch (IOException e) {
      //pass
    }
  }
  
  public static final void setGame(GameThread g){
    game = g;
  }

  public void run(){
    try {
      String message;
      while ((message = in.readLine()) != "quit"){
        game.reap(id);
        System.out.println("Got reap");
        System.out.printf("%d score is now: %d\n", id, game.getScore(id));
      }
      game.quitPlayer(id);
      client.close();
    }
    catch (IOException e) {
      //pass
    }
  }
}
