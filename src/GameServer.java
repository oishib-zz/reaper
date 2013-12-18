import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class GameServer {
	public static void main(String[] args) {
		//ports below 1024 are reserved for other functions
		int port = 29999;
		
		//This will write (send) the "echo" back to the client.
		PrintWriter out;
		
		//This will read the message sent from the client.
		BufferedReader in;
		
		//This will hold the connection once the server accepts a client.
		Socket clientSocket = null;
		
		//The ServerSocket waits for a client to connect.
		ServerSocket serverSocket = null;
		
		ArrayList<PlayerThread>players = new ArrayList<PlayerThread>();
		try
		{
			//make new game thread
			GameThread game=new GameThread();
			game.start();
			PlayerThread.setGame(game);
			//create the ServerSocket for the given port
			serverSocket = new ServerSocket(port);
			//try to accept the incoming connection		
			clientSocket = serverSocket.accept();
			players.add(new PlayerThread(clientSocket));
			System.out.println("Set-up and ready to read.");
			
			//setup the PrintWriter to write to the connection
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			//setup the BufferedReader to read from the connection
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String message = in.readLine();
			while (players.size()!=0)
			{
				clientSocket = serverSocket.accept();
				players.add(new PlayerThread(clientSocket));
				/*if (message.equals("click")){
					System.out.println ("trying to reset");
					game.reset();
				}
	        	out.println(game.getPoints());
	        	out.flush();
	        	message = in.readLine();*/
	        }
			//close out the in/out streams and sockets
			out.close();
	        in.close();
	        clientSocket.close();
	        serverSocket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			//force the method to end if there is a problem
			//a code of 1 indicates that it ended with an error
			System.exit(1);
		}
	}
}
