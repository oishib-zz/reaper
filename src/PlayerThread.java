import java.io.PrintWriter;
import java.net.Socket;


public class PlayerThread extends Thread{
	private Socket client;
	private PrintWriter out;
	private static GameThread game;
	public PlayerThread (Socket client){
		this.client=client;
		
		//add itself to game; get a new total
	}
	public static final void setGame(GameThread g){
		game = g;
	}
	public void run(){
		//listens constantly
		//if we get a quit message, close client Socket
		//manipulates game as needed
	}
}
