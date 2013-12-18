import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class PlayerThread extends Thread{
	private Socket client;
	private PrintWriter out;
	private BufferedReader in;
	private static GameThread game;
	
	public PlayerThread (Socket client){
		this.client=client;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader()
		} catch (IOException e) {
		}
		//add itself to game; get a new total
	}
	public static final void setGame(GameThread g){
		game = g;
	}
	public void run(){
		
		while (message != "quit"){
			
		}
		client.close();
		//listens constantly
		//if we get a quit message, close client Socket
		//manipulates game as needed
		
	}
}
