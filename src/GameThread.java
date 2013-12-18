import java.util.HashMap;

public class GameThread extends Thread{
  private int points = 1;
  private HashMap<Integer, Integer> scores;

  public GameThread(){
    points = 1;
    scores = new HashMap<Integer, Integer>();
  }

  public void run(){
    while (true)
    {
      try {
        Thread.sleep (500);
        points++;
        System.out.printf("Points: %d\n", points);
      } 
      catch (InterruptedException e) {
        //pass
      }
    }
  }

  //Mutation methods
  public void addPlayer(int id) {
    scores.put(id, 0);
  }
  public void quitPlayer(int id) {
    scores.remove(id);
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
