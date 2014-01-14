import java.util.HashMap;

public class GameThread extends Thread{
  private int points = 1;
  private HashMap<Integer, Integer> scores;
  private HashMap<Integer, PlayerThread> players;

  public GameThread(){
    points = 1;
    scores = new HashMap<Integer, Integer>();
    players = new HashMap<Integer, PlayerThread>();
  }

  public void run(){
    while (true)
    {
      try {
        Thread.sleep (500);
        points++;

        // Tell all the clients
        for (PlayerThread player : players.values()) {
          player.message(scores);
        }
      } 
      catch (InterruptedException e) {
        //pass
      }
    }
  }
  public int getJackpot(){
    return points;
  }

  //Mutation methods
  public void addPlayer(int id, PlayerThread player) {
    scores.put(id, 0);
    players.put(id, player);
  }
  public void quitPlayer(int id) {
    scores.remove(id);
    players.remove(id);
  }
  public void reap(int id) {
    // Give all the points to player (id), then reset the pot
    scores.put(id, scores.get(id) + points);
    points = 1;
  }

  //Getter methods
  public HashMap<Integer, Integer> getScores() {
    return scores;
  }

  public int getScore(int id) {
    return scores.get(id);
  }
}
