import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;

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

  public void message(HashMap<Integer, Integer> scores) {
    out.println(game.getScore(id));
    out.println(game.getJackpot());
    out.println("_START");
    for (Entry<Integer, Integer> score : scores.entrySet()) {
      out.println(score.getKey());
      out.println(score.getValue());
    }
    out.println("_END");
  }

  public void run(){
    try {
      String message;
      while ((message = in.readLine()) != null){
        game.reap(id);
      }
      game.quitPlayer(id);
      client.close();
    }
    catch (IOException e) {
      //pass
    }
  }
}
