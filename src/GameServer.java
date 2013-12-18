import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class GameServer {
  public static void main(String[] args) {
    //ports below 1024 are reserved for other functions
    int port = 9001;

    //For unique client identification
    int id = 0;
    
    //This will hold the connection once the server accepts a client.
    Socket clientSocket = null;
    
    try
    {
      //The ServerSocket waits for a client to connect.
      ServerSocket serverSocket = new ServerSocket(port);

      //make new game thread
      GameThread game = new GameThread();
      game.start();

      //Set the global game thread variable
      PlayerThread.setGame(game);

      while (true) //Serve forever
      {
        id++;
        (new PlayerThread(serverSocket.accept(), id)).start(); // As we accept connection, instantiate threads for them
        game.addPlayer(id);
      }
    }
    catch(IOException e)
    {
      //Exit as if thrown
      e.printStackTrace();
      System.exit(1);
    }
  }
}
